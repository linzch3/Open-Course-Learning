# -*- coding: utf-8 -*-
import scrapy
import re


class StocksSpider(scrapy.Spider):
    name = "stocks"
    #从动东方财富网开始爬取
    start_urls = ['http://quote.eastmoney.com/stocklist.html']

    def parse(self, response):
        #获取a标签
        for href in response.css('a::attr(href)').extract():
            try:
                #得到股票代号
                stock = re.findall(r"[s][hz]\d{6}", href)[0]
                #根据股票代号在得到要在百度股票上查询的url
                url = 'https://gupiao.baidu.com/stock/' + stock + '.html'
                yield scrapy.Request(url, callback=self.parse_stock)
            except:
                continue

    def parse_stock(self, response):
        #单支股票信息
        infoDict = {}
        #得到tock-bets区域的信息
        stockInfo = response.css('.stock-bets')
        #得到bets-name区域的信息
        name = stockInfo.css('.bets-name').extract()[0]
        #得到键值中的键 所在的html代码块
        keyList = stockInfo.css('dt').extract()
        #得到键值中的值 所在的html代码块
        valueList = stockInfo.css('dd').extract()
        for i in range(len(keyList)):
            #得到键
            key = re.findall(r'>.*</dt>', keyList[i])[0][1:-5]
            #得到值
            try:
                val = re.findall(r'\d+\.?.*</dd>', valueList[i])[0][0:-5]
            except:
                val = '--'
            #用key-val键值对更新infoDict
            infoDict[key]=val
        #更新infoDict
        infoDict.update(
            {'股票名称': re.findall('\s.*\(',name)[0].split()[0] + \
             re.findall('\>.*\<', name)[0][1:-1]})
        yield infoDict
