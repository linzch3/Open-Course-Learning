# -*- coding: utf-8 -*-
'''use_kmeans_in_citys.py'''
import numpy as np
from sklearn.cluster import KMeans
 
 
def loadData(filePath):
    '''
	将文本文件中的数据保存到retData、retCityName这两个变量中并返回
    '''
    fr = open(filePath,'r+',encoding='utf-8')#读写打开一个文本文件
    lines = fr.readlines()
    retData = []            #用来存储城市的各项消费信息
    retCityName = []        #用来存储城市名称
    for line in lines:
        items = line.strip().split(",")
        retCityName.append(items[0])
        retData.append([float(items[i]) for i in range(1,len(items))])
    return retData,retCityName
 
     
if __name__ == '__main__':
    data,cityName = loadData('city.txt')
    km = KMeans(n_clusters=4)#聚类中心为4
    label = km.fit_predict(data)#计算簇中心以及为簇分配序号,label为每行数据对应分配到的序列
    print('label\n',label)
    expenses = np.sum(km.cluster_centers_,axis=1)#按行求和
    print('km.cluster_centers_\n',km.cluster_centers_)
    print('expenses\n',expenses,'\n\n')
    CityCluster = [[],[],[],[]]
    #将在同一个簇的城市保存在同一个list中
    for i in range(len(cityName)):
        CityCluster[label[i]].append(cityName[i])
    #输出各个簇的平均消费数和对应的城市名称
    for i in range(len(CityCluster)):
        print("Expenses:%.2f" % expenses[i])
        print(CityCluster[i])