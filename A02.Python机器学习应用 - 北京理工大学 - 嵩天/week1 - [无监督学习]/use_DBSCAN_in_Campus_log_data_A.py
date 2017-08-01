# -*- coding: utf-8 -*-
'''
use_DBSCAN_in_Campus_log_data_A.py
对上网开始时间进行聚类
'''
import numpy as np
from sklearn.cluster import DBSCAN
from sklearn import metrics#计算方法
import matplotlib.pyplot as plt
 
 
mac2id=dict()
onlinetimes=[]
f=open('Campus_log_data.txt',encoding='utf-8')
for line in f:
    items = line.strip().split(",")
    #mac地址
    mac=items[2]
    #上网时长
    onlinetime=int(items[6])
    #时间格式举例：2014-07-20 22:44:18.540000000
    starttime=int(items[4].split(' ')[1].split(':')[0])#只保留时间的小时位
    
    #保证onlinetime中对应一个mac地址有一个唯一的记录
    if mac not in mac2id:
        mac2id[mac]=len(onlinetimes)
        onlinetimes.append((starttime,onlinetime))
    else:
        onlinetimes[mac2id[mac]]=(starttime,onlinetime)

real_X=np.array(onlinetimes).reshape((-1,2)) #-1代表行数由程序自行根据列数和总数据信息推算出
 
X=real_X[:,0:1]#只得到上网（开始）时间
 
#调用DBSCAN方法进行训练，labels为每个数据的簇标签
db=DBSCAN(eps=0.01,min_samples=20).fit(X)
labels = db.labels_#返回的数据的簇标签，噪声数据标签为-1
print('Labels:\n',labels)

#计算标签为-1的数据（即噪声数据)的比例
raito=len(labels[labels[:] == -1]) / len(labels)
print('Noise raito:',format(raito, '.2%'))
 
#计算簇的个数
n_clusters_ = len(set(labels)) - (1 if -1 in labels else 0)
print('Estimated number of clusters: %d' % n_clusters_)

#评价聚类效果:轮廓系数si，原理可参考：http://blog.csdn.net/xueyingxue001/article/details/51966932
'''
            si接近1，则说明样本i聚类合理；
            si接近-1，则说明样本i更应该分类到另外的簇；
            若si 近似为0，则说明样本i在两个簇的边界上。
'''
print("Silhouette Coefficient: %0.3f"% metrics.silhouette_score(X, labels))#聚类效果评价指标
 
#打印各簇标号以及各簇内数据
for i in range(n_clusters_):
    print('number of data in Cluster %s is : %s'%(i,len(X[labels==i])))
    #print(list(X[labels == i].flatten()))

    
#绘制直方图分析
plt.hist(X,24)