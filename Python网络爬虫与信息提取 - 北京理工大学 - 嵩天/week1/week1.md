<!-- TOC -->

- [【第〇周】网络爬虫之前奏](#第〇周网络爬虫之前奏)
    - [网络爬虫”课程内容导学](#网络爬虫课程内容导学)
- [【第一周】网络爬虫之规则](#第一周网络爬虫之规则)
    - [1.Requests库入门](#1requests库入门)
        - [get方法的使用](#get方法的使用)
        - [爬取网页的通用代码框架](#爬取网页的通用代码框架)
        - [HTTP协议和requests库方法](#http协议和requests库方法)
        - [requests库主要方法解析](#requests库主要方法解析)
        - [单元小结](#单元小结)
        - [讨论](#讨论)
    - [2.网络爬虫的“盗亦有道”](#2网络爬虫的盗亦有道)
        - [网络爬虫引发的问题](#网络爬虫引发的问题)
        - [robot协议](#robot协议)
        - [robot协议的遵守方式](#robot协议的遵守方式)
    - [requests库网络爬虫实战（5个实例）](#requests库网络爬虫实战5个实例)
        - [1.京东商品信息的爬取：](#1京东商品信息的爬取)
        - [2.亚马逊商品页面的爬取：](#2亚马逊商品页面的爬取)
        - [3.百度/360搜索关键词提交](#3百度360搜索关键词提交)
        - [4.网络图片的爬取和存储](#4网络图片的爬取和存储)
        - [5.IP地址归属地查询](#5ip地址归属地查询)

<!-- /TOC -->

##【第〇周】网络爬虫之前奏

**课程推荐阅读文章：**[关于反爬虫，看这一篇就够了]['link']

['link']:https://segmentfault.com/a/1190000005840672

### 网络爬虫”课程内容导学
![这里写图片描述](http://img.blog.csdn.net/20170307095353277?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307095404633?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
##【第一周】网络爬虫之规则
### 1.Requests库入门
Requests库英文文档：[Requests: HTTP for Humans](Requests:%20HTTP%20for%20Humans)
Requests库中文文档：[Requests: 让 HTTP 服务人类](http://cn.python-requests.org/zh_CN/latest/)

注意：中文文档的内容要稍微比英文文档的更新得慢一些，参考时需要关注两种文档对应的Requests库版本。（对于比较简单的使用方法，我们看中文的就行了）

Requests库的7个主要方法：
![这里写图片描述](http://img.blog.csdn.net/20170307100648609?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：这7的方法的后面6个都是由requests.request()函数封装的。

#### get方法的使用
对于代码`r=requests.get(url)`，r为函数返回的Response 对象，该对象包含爬虫返回的内容。

Response 对象的属性：
![这里写图片描述](http://img.blog.csdn.net/20170307101616580?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

这5个属性的使用流程：
![这里写图片描述](http://img.blog.csdn.net/20170307101710607?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

例子：

```python
>>> import requests
>>> r=requests.get('http://www.baidu.com')
>>> r.status_code
200
>>> r.text
'<!DOCTYPE html>\r\n<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=http://s1.bdstatic.com/r/www/cache/bdorz/baidu.min.css><title>ç\x99¾åº¦ä¸\x80ä¸\x8bï¼\x8cä½\xa0å°±ç\x9f¥é\x81\x93</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> <input type=hidden name=ie value=utf-8> <input type=hidden name=f value=8> <input type=hidden name=rsv_bp value=1> <input type=hidden name=rsv_idx value=1> <input type=hidden name=tn value=baidu><span class="bg s_ipt_wr"><input id=kw name=wd class=s_ipt value maxlength=255 autocomplete=off autofocus></span><span class="bg s_btn_wr"><input type=submit id=su value=ç\x99¾åº¦ä¸\x80ä¸\x8b class="bg s_btn"></span> </form> </div> </div> <div id=u1> <a href=http://news.baidu.com name=tj_trnews class=mnav>æ\x96°é\x97»</a> <a href=http://www.hao123.com name=tj_trhao123 class=mnav>hao123</a> <a href=http://map.baidu.com name=tj_trmap class=mnav>å\x9c°å\x9b¾</a> <a href=http://v.baidu.com name=tj_trvideo class=mnav>è§\x86é¢\x91</a> <a href=http://tieba.baidu.com name=tj_trtieba class=mnav>è´´å\x90§</a> <noscript> <a href=http://www.baidu.com/bdorz/login.gif?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2f%3fbdorz_come%3d1 name=tj_login class=lb>ç\x99»å½\x95</a> </noscript> <script>document.write(\'<a href="http://www.baidu.com/bdorz/login.gif?login&tpl=mn&u=\'+ encodeURIComponent(window.location.href+ (window.location.search === "" ? "?" : "&")+ "bdorz_come=1")+ \'" name="tj_login" class="lb">ç\x99»å½\x95</a>\');</script> <a href=//www.baidu.com/more/ name=tj_briicon class=bri style="display: block;">æ\x9b´å¤\x9aäº§å\x93\x81</a> </div> </div> </div> <div id=ftCon> <div id=ftConw> <p id=lh> <a href=http://home.baidu.com>å\x85³äº\x8eç\x99¾åº¦</a> <a href=http://ir.baidu.com>About Baidu</a> </p> <p id=cp>&copy;2017&nbsp;Baidu&nbsp;<a href=http://www.baidu.com/duty/>ä½¿ç\x94¨ç\x99¾åº¦å\x89\x8då¿\x85è¯»</a>&nbsp; <a href=http://jianyi.baidu.com/ class=cp-feedback>æ\x84\x8fè§\x81å\x8f\x8dé¦\x88</a>&nbsp;äº¬ICPè¯\x81030173å\x8f·&nbsp; <img src=//www.baidu.com/img/gs.gif> </p> </div> </div> </div> </body> </html>\r\n'
```
可以看到，r.text里面的东西都是乱码。查看编码方式：
```python
>>> r.encoding
'ISO-8859-1'
>>> r.apparent_encoding
'utf-8'
```
可以这样理解编码：网络上的资源都有编码，没有适当的编码，我们就看不懂里面的内容。

将编码方式改为：utf-8，可以发现，r.text已经显示为我们可读的内容了。
```python
>>> r.encoding='utf-8'
>>> r.text
'<!DOCTYPE html>\r\n<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=http://s1.bdstatic.com/r/www/cache/bdorz/baidu.min.css><title>百度一下，你就知道</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> <input type=hidden name=ie value=utf-8> <input type=hidden name=f value=8> <input type=hidden name=rsv_bp value=1> <input type=hidden name=rsv_idx value=1> <input type=hidden name=tn value=baidu><span class="bg s_ipt_wr"><input id=kw name=wd class=s_ipt value maxlength=255 autocomplete=off autofocus></span><span class="bg s_btn_wr"><input type=submit id=su value=百度一下 class="bg s_btn"></span> </form> </div> </div> <div id=u1> <a href=http://news.baidu.com name=tj_trnews class=mnav>新闻</a> <a href=http://www.hao123.com name=tj_trhao123 class=mnav>hao123</a> <a href=http://map.baidu.com name=tj_trmap class=mnav>地图</a> <a href=http://v.baidu.com name=tj_trvideo class=mnav>视频</a> <a href=http://tieba.baidu.com name=tj_trtieba class=mnav>贴吧</a> <noscript> <a href=http://www.baidu.com/bdorz/login.gif?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2f%3fbdorz_come%3d1 name=tj_login class=lb>登录</a> </noscript> <script>document.write(\'<a href="http://www.baidu.com/bdorz/login.gif?login&tpl=mn&u=\'+ encodeURIComponent(window.location.href+ (window.location.search === "" ? "?" : "&")+ "bdorz_come=1")+ \'" name="tj_login" class="lb">登录</a>\');</script> <a href=//www.baidu.com/more/ name=tj_briicon class=bri style="display: block;">更多产品</a> </div> </div> </div> <div id=ftCon> <div id=ftConw> <p id=lh> <a href=http://home.baidu.com>关于百度</a> <a href=http://ir.baidu.com>About Baidu</a> </p> <p id=cp>&copy;2017&nbsp;Baidu&nbsp;<a href=http://www.baidu.com/duty/>使用百度前必读</a>&nbsp; <a href=http://jianyi.baidu.com/ class=cp-feedback>意见反馈</a>&nbsp;京ICP证030173号&nbsp; <img src=//www.baidu.com/img/gs.gif> </p> </div> </div> </div> </body> </html>\r\n'

```

关于r.encoding和r.apparent_encoding的区别：

![这里写图片描述](http://img.blog.csdn.net/20170307101920516?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

如何理解“如果header中不存在charset”：通常我们得到的网页都是由某个角落的一个服务器“发出”的，而有些服务器对网页的编码有要求，其所用的编码就会保存在网页header中的charset，而如果没有charset，那么Respond对象的编码就默认为ISO-8859-1。

从上面的那张图片也可以看到，r.apparent_encoding是根据网页内容“实实在在”地分析出来的编码方式，因此其比r.encoding显得更为靠谱。

#### 爬取网页的通用代码框架
在使用get方法获得网页的时候，由于网络连接可能不稳定，所以在使用该方法时需要编写“异常处理”的代码。

requests库常用的异常：

![这里写图片描述](http://img.blog.csdn.net/20170307111425470?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

requests库中的raise_for_status函数可以帮助我们处理常用的异常：

![这里写图片描述](http://img.blog.csdn.net/20170307111823597?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

通用代码框架：

![这里写图片描述](http://img.blog.csdn.net/20170307112439552?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

测试：
![这里写图片描述](http://img.blog.csdn.net/20170307112829835?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### HTTP协议和requests库方法
重新看到Requests库的7个主要方法，后面6个一一对应HTTP的相关方法。

![这里写图片描述](http://img.blog.csdn.net/20170307100648609?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面接介绍HTTP协议：

![这里写图片描述](http://img.blog.csdn.net/20170307113843000?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：
1.“请求与响应”：用户端发出请求，服务器做出响应。
2.“无状态”：用户端这次发出的请求和下一次发出的没有联系
3.“应用层协议”：该协议工作在HTTP协议之上

常用URL格式：

![这里写图片描述](http://img.blog.csdn.net/20170307114248975?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

HTTP协议对资源的操作：

![这里写图片描述](http://img.blog.csdn.net/20170307121139920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
可以把网络上的数据的所在位置是：云端，我们可通过get、head操作从云端获取数据（可以理解为文件的读操作），可通过put、post、patch、delete操作对数据进行修改（可以理解为文件的写操作）

举例：

![这里写图片描述](http://img.blog.csdn.net/20170307121726683?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170307121738038?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170307121747121?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170307121755558?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### requests库主要方法解析
这里将对requests库的几个主要方法的使用方法进行介绍。

**对于request函数：**

![这里写图片描述](http://img.blog.csdn.net/20170307123108714?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307123230066?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

kwargs的13个参数如下：

![0](http://img.blog.csdn.net/20170307124209385?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307123359316?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![4](http://img.blog.csdn.net/20170307123548938?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307123606564?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307123616348?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：模拟浏览器或者手机访问网页的方法就是通过headers参数实现的

![这里写图片描述](http://img.blog.csdn.net/20170307123753380?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307123804847?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：使用file参数可以向服务器提交文件

![timeout](http://img.blog.csdn.net/20170307123924022?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：当服务器的响应时间大于timeout值时，request函数会返回超时异常。

![这里写图片描述](http://img.blog.csdn.net/20170307123937241?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：使用这个参数可以有效地隐藏我们真正的IP地址，可有效地防止爬虫的逆追踪

![这里写图片描述](http://img.blog.csdn.net/20170307124114055?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**对于get函数**

![这里写图片描述](http://img.blog.csdn.net/20170307124612791?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
这里的kwargs和上面的request函数的是一样的。

前面提高，get、head、post、patch、delete、put这几个函数都是由request函数定义的，对于进行需要用到的参数，为了方便使用，这些新定义的函数就将这些参数“显式化”了。

**对于head函数**

![这里写图片描述](http://img.blog.csdn.net/20170307124756651?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**对于post函数**

![这里写图片描述](http://img.blog.csdn.net/20170307124812486?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**对于patch函数**

![这里写图片描述](http://img.blog.csdn.net/20170307124822940?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**对于delete函数**

![这里写图片描述](http://img.blog.csdn.net/20170307124832096?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


#### 单元小结

**看了这么多的使用方法，实际上get方法就是最常用的。对于某些比较复杂的工程，head方法的使用仅次于get，因此重点掌握这两个方法即可。**

#### 讨论
尽管Requests库功能很友好、开发简单（其实除了import外只需一行主要代码），但其性能与专业爬虫相比还是有一定差距的。请编写一个小程序，“任意”找个url，测试一下成功爬取100次网页的时间。（某些网站对于连续爬取页面将采取屏蔽IP的策略，所以，要避开这类网站。）
请回复代码，并给出url及在自己机器上的运行时间。

代码：

```python
import requests
import time
 
def getHTMLText(url):
    try:
        r = requests.get(url)
        r.raise_for_status()
        r.encoding=r.apparent_encoding
        return r.text
    except:
        print("Failed")
 
if __name__ == "__main__":
    url = "https://www.baidu.com"
     
    startTime = time.time()
    for i in range(100):
        getHTMLText(url)
    endTime = time.time()

    print("the total time is %fs" %(endTime-startTime))
```
输出结果：

```python
the total time is 14.201600s
```

### 2.网络爬虫的“盗亦有道”
#### 网络爬虫引发的问题

首先看网络爬虫的尺寸分类：
![这里写图片描述](http://img.blog.csdn.net/20170307160545572?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307160559494?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307160824089?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307161118355?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### robot协议

![这里写图片描述](http://img.blog.csdn.net/20170307162026231?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

例子：

![这里写图片描述](http://img.blog.csdn.net/20170307162257203?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

其他例子：

![这里写图片描述](http://img.blog.csdn.net/20170307162311438?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：若一个网站没有robots协议，那么可认为该网站可被爬虫无限制地爬取。

#### robot协议的遵守方式

![这里写图片描述](http://img.blog.csdn.net/20170307162757644?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307162808769?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

而若我们的爬虫程序获取网站信息的频率和人类的相似的话，比如一小时获取一次，这个时候可以不遵守该协议，毕竟对服务器没有造成过度的压力。


### requests库网络爬虫实战（5个实例）
#### 1.京东商品信息的爬取：

![这里写图片描述](http://img.blog.csdn.net/20170307163505335?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### 2.亚马逊商品页面的爬取：

![这里写图片描述](http://img.blog.csdn.net/20170307164110323?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
与上一个例子不同的是：这里需要修改headers以让程序模拟浏览器获取数据。

**注意：这里的调试方法可以参考，可输出r.request.headers查看headers**

#### 3.百度/360搜索关键词提交
搜索引擎关键词提交接口如下，可以发现只要替换了keyword这个关键词就可以实现关键词的提交了。

![这里写图片描述](http://img.blog.csdn.net/20170307164402746?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170307165104671?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
 
 ![这里写图片描述](http://img.blog.csdn.net/20170307165116087?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
 
#### 4.网络图片的爬取和存储

![这里写图片描述](http://img.blog.csdn.net/20170307165351934?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

例子：

![这里写图片描述](http://img.blog.csdn.net/20170307165402579?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

简单版本：

![这里写图片描述](http://img.blog.csdn.net/20170307165829331?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

详细版本（相比上面的代码，这份代码显得更加稳定）：

![这里写图片描述](http://img.blog.csdn.net/20170307165841721?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
**注：这份代码的with...as....语句中的f.close()可以不用加上，该语句会进行相关的文件关闭的处理的。**

#### 5.IP地址归属地查询

首先，寻找可查询IP地址的接口，发现在www.ip138.com上有这个接口。通过测试，可发现ip地址查询的接口（下图下方的链接）：类似于上一个例子，只要替换ipaddress这个关键词就可以实现ip地址的查询了。

![这里写图片描述](http://img.blog.csdn.net/20170307170356583?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面是测试交互界面和对应代码：

![这里写图片描述](http://img.blog.csdn.net/20170307170818601?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170307170847080?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注：这里输出respond对象的内容的时候，**都是取r.text的一部分的内容的**，而若直接输出r.text的所有内容，可能会引起对应IDE的失效（或者 输出负担过大）。

而通过这个例子，也可以发现，对于一些可通过用户输入的信息来进行相应的相应的响应的网站，都可以找到其对应的API，从而可用爬虫程序进行自动的信息填入和自动地根据信息来进行相应的处理。比如，对于这一个例子，其API就是：

![这里写图片描述](http://img.blog.csdn.net/20170307171541683?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

而这也是“以爬虫视角看待网络内容”的表现方式之一。

代码：

```python
# -*- coding:utf-8 -*-
import requests

def getHTMLText(url):
    try:
        kv = {'ip': '120.236.174.140'}
        r = requests.get(url,params=kv)
        r.raise_for_status()
        r.encoding = r.apparent_encoding
        print(r.url)
        return r.text
    except:
        print("Failed!")


url = "http://www.ip138.com/ips138.asp"
print(getHTMLText(url))
```