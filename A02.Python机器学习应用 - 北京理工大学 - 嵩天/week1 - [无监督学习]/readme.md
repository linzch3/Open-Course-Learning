<!-- TOC -->

- [本周课程导学](#本周课程导学)
    - [1.无监督学习简介](#1无监督学习简介)
- [单元1：聚类](#单元1聚类)
    - [1.1.K-means方法及应用](#11k-means方法及应用)
    - [1.2.K-means的应用](#12k-means的应用)
    - [1.3.Sklearn 中KMeans算法的改进](#13sklearn-中kmeans算法的改进)
    - [1.4.DBSCAN方法及应用](#14dbscan方法及应用)
- [单元2：降维](#单元2降维)
    - [2.1.主成分分析及其应用](#21主成分分析及其应用)
    - [2.2.sklearn中主成分分析](#22sklearn中主成分分析)
    - [2.3.NMF方法及其应用](#23nmf方法及其应用)
    - [2.4.sklearn中非负矩阵分解](#24sklearn中非负矩阵分解)
- [单元3：基于聚类的整图分割实例](#单元3基于聚类的整图分割实例)

<!-- /TOC -->
## 本周课程导学
### 1.无监督学习简介
利用**无标签的**数据学习数据的分布或数据与数据之间的关系被称作无监督学习。

• 有监督学习和无监督学习的最大区别在于数据是否有标签

• 无监督学习最常应用的场景是聚类(clustering)和降维(DimensionReduction)

聚类和分类都是无监督学习的典型任务，任务之间存在关联，比如某些高纬数据的分类可以通过降维处理更好的获得，另外学界研究也表明代表性的分类算法如k-means与降维算法如NMF之间存在等价性。

**-> 聚类**

聚类(clustering)，就是根据数据的“相似性”将数据分为多类的过程。

评估两个不同样本之间的“相似性” ，通常使用的方法就是**计算两个样本之间的“距离”**。使用不同的方法计算样本间的距离会关系到聚类结果的好坏。

**--> 一些常用的距离概念（4个）以及计算方法**

![这里写图片描述](http://img.blog.csdn.net/20170725105700592?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170725105712408?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170725105724630?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170725105736354?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**-->关于sklearn**

sklearn中的聚类算法包含在**sklearn.cluster**这个模块中，如：K-Means，近邻传播算法，DBSCAN等。

以同样的数据集应用于不同的算法，可能会得到不同的结果，算法所耗费的时间也不尽相同，这是由算法的特性决定的。

sklearn.cluster模块提供的各聚类算法函数**可以使用不同的数据形式作为输入**:

**标准数据**输入格式:  **[样本个数，特征个数]** 定义的矩阵形式。

**相似性矩阵**输入格式：即由 **[样本数目，样本数目]** 定义的矩阵形式，矩阵中的每一个元素为两个样本的相似度，如DBSCAN， AffinityPropagation(近邻传播算法)接受这种输入。如果以余弦相似度为例，则对角线元素全为1. 矩阵中每个元素的取值范围为[0,1]。

![这里写图片描述](http://img.blog.csdn.net/20170725110236903?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**->降维**
降维，就是在保证数据所具有的代表性特性或者分布的情况下，将高维数据转化为低维数据的过程，如**数据的可视化、精简数据**

**-->关于sklearn**

 降维是机器学习领域的一个重要研究内容，有很多被工业界和学术界接受的典型算法，截止到目前sklearn库提供7种降维算法。

降维过程也可以被理解为对数据集的组成成份进行分解（decomposition）的过程，因此sklearn为降维模块命名为decomposition, 在对降维算法调用需要使用**sklearn.decomposition**模块。

![这里写图片描述](http://img.blog.csdn.net/20170725110543683?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 单元1：聚类

### 1.1.K-means方法及应用

算法原理：

k-means算法以k为参数，把n个对象分成k个簇，使簇内具有较高的相似度，而簇间的相似度较低。

其处理过程如下：

1.随机选择k个点作为初始的聚类中心；

2.对于剩下的点，根据其与聚类中心的距离，将其归入最近的簇

3.对每个簇，计算所有点的均值作为新的聚类中心

4.重复2、 3直到聚类中心不再发生改变

kmeans处理流程举例：

![这里写图片描述](http://img.blog.csdn.net/20170725145704170?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.2.K-means的应用

**数据介绍：**

现有1999年全国31个省份城镇居民家庭平均每人全年消费性支出的八个主要变量数据，这八个变量分别是：食品、 衣着、 家庭设备用品及服务、 医疗保健、 交通和通讯、 娱乐教育文化服务、 居住以及杂项商品和服务。 利用已有数据，对31个省份进行聚类。

**实验目的：**

通过聚类，了解1999年各个省份的消费水平在国内的情况。

**技术路线：**

sklearn.cluster.Kmeans

调用KMeans方法所需参数：

• n_clusters：用于指定聚类中心的个数

• init：初始聚类中心的初始化方法

• max_iter：最大的迭代次数

• 一般调用时只用给出**n_clusters**即可， init 默认是k-means++，max_iter默认是300

**数据实例：**

![这里写图片描述](http://img.blog.csdn.net/20170725145936128?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**数据文件（city.txt）：**

```python
城市名称、食品、 衣着、 家庭设备用品及服务、 医疗保健、 交通和通讯、 娱乐教育文化服务、 居住以及杂项商品和服务
```

```python
北京,2959.19,730.79,749.41,513.34,467.87,1141.82,478.42,457.64
天津,2459.77,495.47,697.33,302.87,284.19,735.97,570.84,305.08
河北,1495.63,515.90,362.37,285.32,272.95,540.58,364.91,188.63
山西,1406.33,477.77,290.15,208.57,201.50,414.72,281.84,212.10
内蒙古,1303.97,524.29,254.83,192.17,249.81,463.09,287.87,192.96
辽宁,1730.84,553.90,246.91,279.81,239.18,445.20,330.24,163.86
吉林,1561.86,492.42,200.49,218.36,220.69,459.62,360.48,147.76
黑龙江,1410.11,510.71,211.88,277.11,224.65,376.82,317.61,152.85
上海,3712.31,550.74,893.37,346.93,527.00,1034.98,720.33,462.03
江苏,2207.58,449.37,572.40,211.92,302.09,585.23,429.77,252.54
浙江,2629.16,557.32,689.73,435.69,514.66,795.87,575.76,323.36
安徽,1844.78,430.29,271.28,126.33,250.56,513.18,314.00,151.39
福建,2709.46,428.11,334.12,160.77,405.14,461.67,535.13,232.29
江西,1563.78,303.65,233.81,107.90,209.70,393.99,509.39,160.12
山东,1675.75,613.32,550.71,219.79,272.59,599.43,371.62,211.84
河南,1427.65,431.79,288.55,208.14,217.00,337.76,421.31,165.32
湖南,1942.23,512.27,401.39,206.06,321.29,697.22,492.60,226.45
湖北,1783.43,511.88,282.84,201.01,237.60,617.74,523.52,182.52
广东,3055.17,353.23,564.56,356.27,811.88,873.06,1082.82,420.81
广西,2033.87,300.82,338.65,157.78,329.06,621.74,587.02,218.27
海南,2057.86,186.44,202.72,171.79,329.65,477.17,312.93,279.19
重庆,2303.29,589.99,516.21,236.55,403.92,730.05,438.41,225.80
四川,1974.28,507.76,344.79,203.21,240.24,575.10,430.36,223.46
贵州,1673.82,437.75,461.61,153.32,254.66,445.59,346.11,191.48
云南,2194.25,537.01,369.07,249.54,290.84,561.91,407.70,330.95
西藏,2646.61,839.70,204.44,209.11,379.30,371.04,269.59,389.33
陕西,1472.95,390.89,447.95,259.51,230.61,490.90,469.10,191.34
甘肃,1525.57,472.98,328.90,219.86,206.65,449.69,249.66,228.19
青海,1654.69,437.77,258.78,303.00,244.93,479.53,288.56,236.51
宁夏,1375.46,480.89,273.84,317.32,251.08,424.75,228.73,195.93
新疆,1608.82,536.05,432.46,235.82,250.28,541.30,344.85,214.40
```

实验代码：

```python
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
```

输出：

```py
label
 [1 3 0 0 0 0 0 0 1 2 3 2 3 0 0 0 2 2 1 2 2 3 2 0 2 3 0 0 0 0 0]
km.cluster_centers_
 [[ 1525.81533333   478.672        322.88266667   232.4          236.41866667
    457.53133333   344.81866667   190.21933333]
 [ 3242.22333333   544.92         735.78         405.51333333   602.25
   1016.62         760.52333333   446.82666667]
 [ 2004.785        429.48         347.8925       190.955        287.66625
    581.16125      437.2375       233.09625   ]
 [ 2549.658        582.118        488.366        268.998        397.442
    618.92         477.946        295.172     ]]
expenses
 [ 3788.758       7754.65666667  4512.27375     5678.62      ]


Expenses:3788.76
['河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江西', '山东', '河南', '贵州', '陕西', '甘肃', '青海', '宁夏', '新疆']
Expenses:7754.66
['北京', '上海', '广东']
Expenses:4512.27
['江苏', '安徽', '湖南', '湖北', '广西', '海南', '四川', '云南']
Expenses:5678.62
['天津', '浙江', '福建', '重庆', '西藏']
```

注：

1.文本文件时.read、.readlines、.readline这三个函数的使用区别：
| 函数| 使用情况 | 
| ------------- |:-------------:| 
|read|每次读取整个文件，它通常用于将文件内容放到一个字符串变量中|
|readlines| 一次读取整个文件保存在一个list中，list中的每个元素为文件的每一行数据（字符串类型）|
|readline| 每次只读取一行，通常比 .readlines() 慢得多。 仅当没有足够内存可以一次读取整个文件时，才应该使用 .readline()|

### 1.3.Sklearn 中KMeans算法的改进
计算两条数据相似性时，Sklearn 的K-Means默认用的是欧式距离。 虽然还有余弦相似度，马氏距离等多种方法，但没有设定计算距离方法的参数。

阅读k_means_.py源码，可以发现在计算向量和簇中心的距离时，使用的是欧式距离（euclidean_distances）。

![这里写图片描述](http://img.blog.csdn.net/20170725154339383?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

可以考虑修改euclidean_distances这个函数为[ scipy.spatial.distance.cdist][1] 函数。

scipy.spatial.distance.cdist函数使用方法：

![这里写图片描述](http://img.blog.csdn.net/20170725154736011?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

该函数中的metric参数可供选择不同的距离计算方法。

[1]:https://docs.scipy.org/doc/scipy/reference/generated/scipy.spatial.distance.cdist.html#scipy.spatial.distance.cdist

### 1.4.DBSCAN方法及应用

**->基本介绍**

DBSCAN算法是一种基于密度的聚类算法：

• 聚类的时候**不需要预先指定簇的个数**

• 最终的**簇的个数不定**

**->算法原理**

DBSCAN算法将数据点分为三类：

• 核心点：在半径Eps内含有超过MinPts数目的点

• 边界点：在半径Eps内点的数量小于MinPts，但是落在核心点的邻域内

• 噪音点：既不是核心点也不是边界点的点

![这里写图片描述](http://img.blog.csdn.net/20170725155525887?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

DBSCAN算法流程：

1.将所有点标记为核心点、边界点或噪声点；

2.删除噪声点；

3.为距离在Eps之内的所有核心点之间赋予一条边；

4.每组连通的核心点形成一个簇；

5.将每个边界点指派到一个与之关联的核心点的簇中（哪一个核心点的半径范围之内）。

**->算法举例**

举例：有如下13个样本点，使用DBSCAN进行聚类：

![这里写图片描述](http://img.blog.csdn.net/20170725155814311?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

step1:
![这里写图片描述](http://img.blog.csdn.net/20170725155940074?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

step2:
![这里写图片描述](http://img.blog.csdn.net/20170725155950824?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

step3:
![这里写图片描述](http://img.blog.csdn.net/20170725160000298?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**->应用**

**数据介绍：**

现有大学校园网的日志数据，290条大学生的校园网使用情况数据，数据包括**用户ID，设备的MAC地址，IP地址，开始上网时间，停止上网时间，上网时长，校园网套餐**等。 利用已有数据，分析学生上网的模式。

**实验目的：**

通过DBSCAN聚类，分析学生上网时间和上网时长的模式。

**技术路线：**

sklearn.cluster.DBSCAN

调用DBSCAN方法所需参数：

eps: 两个样本被看作邻居节点的最大距离

min_samples: 簇的样本数

metric：距离计算方式

例：sklearn.cluster.DBSCAN(eps=0.5, min_samples=5, metric='euclidean')

**数据实例：**

![这里写图片描述](http://img.blog.csdn.net/20170725160338726?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**实现过程：**

![这里写图片描述](http://img.blog.csdn.net/20170725160514410?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**实现代码：**

**----->对上网开始时间进行聚类**
```py
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
```

输出：

```python
Labels:
 [ 0 -1  0  1 -1  1  0  1  2 -1  1  0  1  1  3 -1 -1  3 -1  1  1 -1  1  3  4
 -1  1  1  2  0  2  2 -1  0  1  0  0  0  1  3 -1  0  1  1  0  0  2 -1  1  3
  1 -1  3 -1  3  0  1  1  2  3  3 -1 -1 -1  0  1  2  1 -1  3  1  1  2  3  0
  1 -1  2  0  0  3  2  0  1 -1  1  3 -1  4  2 -1 -1  0 -1  3 -1  0  2  1 -1
 -1  2  1  1  2  0  2  1  1  3  3  0  1  2  0  1  0 -1  1  1  3 -1  2  1  3
  1  1  1  2 -1  5 -1  1  3 -1  0  1  0  0  1 -1 -1 -1  2  2  0  1  1  3  0
  0  0  1  4  4 -1 -1 -1 -1  4 -1  4  4 -1  4 -1  1  2  2  3  0  1  0 -1  1
  0  0  1 -1 -1  0  2  1  0  2 -1  1  1 -1 -1  0  1  1 -1  3  1  1 -1  1  1
  0  0 -1  0 -1  0  0  2 -1  1 -1  1  0 -1  2  1  3  1  1 -1  1  0  0 -1  0
  0  3  2  0  0  5 -1  3  2 -1  5  4  4  4 -1  5  5 -1  4  0  4  4  4  5  4
  4  5  5  0  5  4 -1  4  5  5  5  1  5  5  0  5  4  4 -1  4  4  5  4  0  5
  4 -1  0  5  5  5 -1  4  5  5  5  5  4  4]
Noise raito: 22.15%
Estimated number of clusters: 6
Silhouette Coefficient: 0.710
number of data in Cluster 0 is : 55
number of data in Cluster 1 is : 65
number of data in Cluster 2 is : 28
number of data in Cluster 3 is : 25
number of data in Cluster 4 is : 28
number of data in Cluster 5 is : 24
```
![这里写图片描述](http://img.blog.csdn.net/20170725171216345?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
可观察到：上网时间大多聚集在22：00和23：00

**----->对上网时长进行聚类**
修改上面代码中的：

```python
X=real_X[:,0:1]#只得到上网（开始）时间
 
#调用DBSCAN方法进行训练，labels为每个数据的簇标签
db=DBSCAN(eps=0.01,min_samples=20).fit(X)
```
为：

```python
X=real_X[:,1:]#只得到上网时长
 
#调用DBSCAN方法进行训练，labels为每个数据的簇标签
db=DBSCAN(eps=0.14,min_samples=10).fit(X)
```

运行后可得到：

```python
Labels:
 [-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1]
Noise raito: 100.00%
Estimated number of clusters: 0
Traceback (most recent call last):

  File "<ipython-input-19-8bd78ce26aeb>", line 1, in <module>
    runfile('E:/Code/_testing/01-3.py', wdir='E:/Code/_testing')

  File "D:\Program Files\Anaconda3\lib\site-packages\spyder\utils\site\sitecustomize.py", line 866, in runfile
    execfile(filename, namespace)

  File "D:\Program Files\Anaconda3\lib\site-packages\spyder\utils\site\sitecustomize.py", line 102, in execfile
    exec(compile(f.read(), filename, 'exec'), namespace)

  File "E:/Code/_testing/01-3.py", line 53, in <module>
    print("Silhouette Coefficient: %0.3f"% metrics.silhouette_score(X, labels))#聚类效果评价指标

  File "D:\Program Files\Anaconda3\lib\site-packages\sklearn\metrics\cluster\unsupervised.py", line 100, in silhouette_score
    return np.mean(silhouette_samples(X, labels, metric=metric, **kwds))

  File "D:\Program Files\Anaconda3\lib\site-packages\sklearn\metrics\cluster\unsupervised.py", line 166, in silhouette_samples
    check_number_of_labels(len(le.classes_), X.shape[0])

  File "D:\Program Files\Anaconda3\lib\site-packages\sklearn\metrics\cluster\unsupervised.py", line 20, in check_number_of_labels
    "to n_samples - 1 (inclusive)" % n_labels)

ValueError: Number of labels is 1. Valid values are 2 to n_samples - 1 (inclusive)
```

这就值得注意了，所有数据都被划分为噪声数据，以至于没有一个核心点。

为此，绘制出原始数据的直方图分布，可看到原始数据是不适合用于聚类分析的，因此我们这里使用对数变换来解决该类问题：

![这里写图片描述](http://img.blog.csdn.net/20170725172657888?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

因此，需要修改

```python
X=real_X[:,1:]#只得到上网时长
```

为：

```python
X=np.log(1+real_X[:,1:])#只得到上网时长，这里+1是为了防止为0的情况
```

全部代码：

```python
# -*- coding: utf-8 -*-
'''
use_DBSCAN_in_Campus_log_data_B.py
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
 
X=np.log(1+real_X[:,1:])#只得到上网时长，这里+1是为了防止为0的情况
 
#调用DBSCAN方法进行训练，labels为每个数据的簇标签
db=DBSCAN(eps=0.14,min_samples=10).fit(X)
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
```

输出：

```py
Labels:
 [ 0  1  0  4  1  2  0  2  0  3 -1  0 -1 -1  0  3  1  0  3  2  2  1  2  0  1
  1 -1 -1  0  0  0  0  1  0 -1  0  0  0  2  0  1  0 -1 -1  0  0  0  3  2  0
 -1  1  0  1  0  0 -1  2  0  0  0  1  3  3  0  2  0 -1  3  0  0  2  0  0  0
  2  1 -1  0  0  0  0  0  0  1 -1  0  3  1  0  1  1  0  1  0  1  0  0 -1  1
  1  0  0  2  0  0  0  2  2  0  0  0 -1  0  0  4  0  1  2 -1  0  1  0  2  0
 -1 -1 -1  0  1  1  3 -1  0  1  0  2  0  0  2  1  1  0  0  0  0  4 -1  0  0
  0  0  2  0  0  0  0 -1  2  0  0  0  0  4  0  0 -1  0  2  0  0 -1  0  1  4
  0  0 -1  1  1  0  0  2  0  0  3 -1 -1 -1  1  0  0  2  1  0 -1 -1  3  2  2
  0  0  3  0  1  0  0  0  3  2  0 -1  0  1 -1 -1  0  2  2  1  4  0  0  1  0
  2  0  0  0  0  1  1  0  0  1  0  4 -1 -1  0  0  0 -1 -1  1 -1  4 -1  0  2
  2 -1  2  1  2 -1  0 -1  0  2  2  1 -1  0  1  2 -1 -1  1 -1  2 -1 -1  1  4
  2  3  1  0  4  0  0  4  2  4  0  0  2 -1]
Noise raito: 16.96%
Estimated number of clusters: 5
Silhouette Coefficient: 0.227
number of data in Cluster 0 is : 128
number of data in Cluster 1 is : 46
number of data in Cluster 2 is : 40
number of data in Cluster 3 is : 14
number of data in Cluster 4 is : 12
```
![这里写图片描述](http://img.blog.csdn.net/20170725173135627?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

简单分析：
简单从聚类数目来看，时长聚类效果不如时间的聚类效果明显。

## 单元2：降维

### 2.1.主成分分析及其应用

**->介绍**
 主成分分析（Principal Component Analysis，PCA）是**最常用的一种降维**方法，通常用于**高维数据集的探索与可视化**，还可以用作**数据压缩和预处理**等。

 PCA可以把具有相关性的高维变量合成为线性无关的低维变量，称为主成分。主成分能够尽可能保留原始数据的信息。

**->基本前提概念**

在介绍PCA的原理之前需要回顾涉及到的相关术语：

• 方差：

是各个样本和样本均值的差的平方和的均值，用来度量一组数据的分散程度。

![这里写图片描述](http://img.blog.csdn.net/20170725191231732?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

• 协方差：

用于度量两个变量之间的线性相关性程度，若两个变量的协方差为0，则可认为二者线性无关。

![这里写图片描述](http://img.blog.csdn.net/20170725191339483?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

• 协方差矩阵

协方差矩阵则是由变量的协方差值构成的矩阵（对称阵）。

• 特征向量和特征值

矩阵的特征向量是描述数据集结构的非零向量，并满足如下公式：![这里写图片描述](http://img.blog.csdn.net/20170725191428274?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)，A是方阵， v->是特征向量，lamda是特征值。

**->算法原理**

矩阵的主成分就是其协方差矩阵对应的特征向量，按照对应的特征值大小进行排序，最大的特征值就是第一主成分，其次是第二主成分，以此类推。

![这里写图片描述](http://img.blog.csdn.net/20170725191623742?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 2.2.sklearn中主成分分析

在sklearn库中，可以使用sklearn.decomposition.PCA加载PCA进行降维，主要参数有：

• n_components：指定主成分的个数，即降维后数据的维度

• svd_solver ：设置特征值分解的方法，默认为‘auto’,其他可选有‘full’, ‘arpack’, ‘randomized’ 。

**->实例编写：**

目标：

已知鸢尾花数据是4维的，共**三类样本**。使用PCA实现对鸢尾花数据进行降维，实现在二维平面上的可视化。

实现代码：
```python
# -*- coding: utf-8 -*-
'''use_PCA_in_iris.py'''
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.datasets import load_iris
 
data = load_iris()
y = data.target #使用y表示数据集中的标签
X = data.data  #使用X表示数据集中的属性数据
pca = PCA(n_components=2) #加载PCA算法，设置降维后主成分数目为2
reduced_X = pca.fit_transform(X) #对原始数据进行降维，保存在reduced_X中
 
red_x, red_y = [], [] #第一类数据点
blue_x, blue_y = [], [] #第二类数据点
green_x, green_y = [], [] #第三类数据点
 
#按照鸢尾花的类别将降维后的数据点保存在不同的列表中。

for i in range(len(reduced_X)):
    if y[i] == 0:
        red_x.append(reduced_X[i][0])
        red_y.append(reduced_X[i][1])
    elif y[i] == 1:
        blue_x.append(reduced_X[i][0])
        blue_y.append(reduced_X[i][1])
    else:
        green_x.append(reduced_X[i][0])
        green_y.append(reduced_X[i][1])
 
#降维后数据点的可视化
plt.scatter(red_x, red_y, c='r', marker='x')
plt.scatter(blue_x, blue_y, c='b', marker='D')
plt.scatter(green_x, green_y, c='g', marker='.')
plt.show()

```

输出结果：

![这里写图片描述](http://img.blog.csdn.net/20170725192525018?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

结果分析：

可以看出，降维后的数据仍能够清晰地分成三类。这样不仅能削减数据的维度，降低分类任务的工作量，还能保证分类的质量。

### 2.3.NMF方法及其应用

非负矩阵分解（Non-negative Matrix Factorization ，NMF）是在矩阵中所有元素均为非负数约束条件之下的矩阵分解方法。

基本思想：给定一个非负矩阵V，NMF能够找到一个非负矩阵W和一个非负矩阵H，使得矩阵W和H的乘积近似等于矩阵V中的值。

![这里写图片描述](http://img.blog.csdn.net/20170725193313914?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

• W矩阵：基础图像矩阵，相当于从原矩阵V中抽取出来的特征

• H矩阵：系数矩阵。

对应关系如下所示：

![这里写图片描述](http://img.blog.csdn.net/20170725193406713?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下图摘自NMF作者的论文，左侧为W矩阵，可以看出从原始图像中抽取出来的特征，中间的是H矩阵。 可以发现乘积结果与原结果是很像的。

![这里写图片描述](http://img.blog.csdn.net/20170725193532526?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


矩阵分解优化目标：**最小化W矩阵H矩阵的乘积和原始矩阵之间的差别**，目标函数如下：

![这里写图片描述](http://img.blog.csdn.net/20170725193639253?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

基于KL散度的优化目标，损失函数如下：

![这里写图片描述](http://img.blog.csdn.net/20170725193706179?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

公式推导见：[这里][2]

[2]:http://blog.csdn.net/acdreamers/article/details/44663421/

### 2.4.sklearn中非负矩阵分解

在sklearn库中，可以使用sklearn.decomposition.NMF加载NMF算法，主要参数有：

• n_components：用于指定分解后矩阵的单个维度k；

• init：W矩阵和H矩阵的初始化方式，默认为‘nndsvdar’。

**->实例编写**

目标：

已知Olivetti人脸数据共400个，每个数据是64*64大小。由于NMF分解得到的W矩阵相当于从原始矩阵中提取的特征，那么就可以使用NMF对400个人脸数据进行特征提取。

![这里写图片描述](http://img.blog.csdn.net/20170725194034113?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

思路：通过设置k的大小，设置提取的特征的数目。在本实验中设置k=6，随后将提取的特征以图像的形式展示出来。

实现代码：

```python
# -*- coding: utf-8 -*-
'''use_NMF_in_olivetti_faces.py'''
from numpy.random import RandomState #加载RandomState用于创建随机种子
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_olivetti_faces #加载Olivetti人脸数据集导入函数
from sklearn import decomposition
 
 
n_row, n_col = 2, 3 #设置图像展示时的排列情况
n_components = n_row * n_col
image_shape = (64, 64) #设置人脸数据图片的大小
 
 
###############################################################################
##加载数据，并打乱顺序
dataset = fetch_olivetti_faces(shuffle=True, random_state=RandomState(0))
faces = dataset.data
 
###############################################################################
def plot_gallery(title, images, n_col=n_col, n_row=n_row):
    plt.figure(figsize=(2. * n_col, 2.26 * n_row)) #创建图片，并指定图片大小（英寸）
    plt.suptitle(title, size=16)#设置标题及字号大小
 
    for i, comp in enumerate(images):
        plt.subplot(n_row, n_col, i + 1)
        vmax = max(comp.max(), -comp.min())
        
        #对数值归一化，并以灰度图形式显示
        plt.imshow(comp.reshape(image_shape), cmap=plt.cm.gray,
                   interpolation='nearest', vmin=-vmax, vmax=vmax)
        #去除子图的坐标轴标签
        plt.xticks(())
        plt.yticks(())
        
    #对子图位置及间隔调整
    plt.subplots_adjust(0.01, 0.05, 0.99, 0.94, 0.04, 0.)
 
     
plot_gallery("First centered Olivetti faces", faces[:n_components])
###############################################################################
#创建特征提取的对象：NMF、PCA
estimators = [
    ('Eigenfaces - PCA using randomized SVD',
         decomposition.PCA(n_components=6,whiten=True)),
 
    ('Non-negative components - NMF',
         decomposition.NMF(n_components=6, init='nndsvda', tol=5e-3))
]
 
###############################################################################
#降维后数据点的可视化
for name, estimator in estimators:
    print("Extracting the top %d %s..." % (n_components, name))
    print(faces.shape)
    estimator.fit(faces)#调用PCA或NMF提取特征
    components_ = estimator.components_#获取提取的特征
    plot_gallery(name, components_[:n_components])#按照固定格式进行排列
 
plt.show()
```

输出：

![这里写图片描述](http://img.blog.csdn.net/20170725195644142?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170725195654446?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170725195706535?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## 单元3：基于聚类的整图分割实例

**基本介绍：**

图像分割：利用图像的灰度、颜色、纹理、形状等特征，把图像分成若干个互不重叠的区域，并使这些特征在同一区域内呈现相似性，在不同的区域之间存在明显的差异性。 然后就可以将分割的图像中具有独特性质的区域提取出来用于不同的研究。

图像分割技术已在实际生活中得到广泛的应用。例如：在机车检验领域，可以应用到轮毂裂纹图像的分割，及时发现裂纹，保证行车安全；在生物医学工程方面，对肝脏CT图像进行分割，为临床治疗和病理学研究提供帮助。

**图像分割常用方法：**

1. 阈值分割：对图像灰度值进行度量，设置不同类别的阈值，达到分割的目的。

2. 边缘分割：对图像边缘进行检测，即检测图像中灰度值发生跳变的地方，则为一片区域的边缘。

3. 直方图法：对图像的颜色建立直方图，而直方图的波峰波谷能够表示一块区域的颜色值的范围，来达到分割的目的。

4. 特定理论：基于聚类分析、小波变换等理论完成图像分割。

**实例：**

目标：利用K-means聚类算法对图像像素点颜色进行聚类实现简单的图像分割

输出：同一聚类中的点使用相同颜色标记，不同聚类颜色不同

技术路线：sklearn.cluster.KMeans

说明：本实例中的数据可以是任意大小的图片，为了使效果更佳直观，可以采用区分度比较明显的图片。

选择图片：

![这里写图片描述](http://img.blog.csdn.net/20170725201304305?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实现代码：

```python
# -*- coding: utf-8 -*-
'''use_kmeans_in_Image_segmentation.py'''
import numpy as np
import PIL.Image as image
from sklearn.cluster import KMeans
 
def loadData(filePath):
    f = open(filePath,'rb')#以二进制形式读取文件
    data = []
    img = image.open(f)
    m,n = img.size
    #将每个像素点的RGB归一化并存入data
    for i in range(m):
        for j in range(n):
            x,y,z,w = img.getpixel((i,j))
            data.append([x/256.0,y/256.0,z/256.0])
    f.close()
    return np.mat(data),m,n
 
imgData,row,col = loadData('bull.png')
#聚类获取每个像素点的类别
label = KMeans(n_clusters=4).fit_predict(imgData)
label = label.reshape([row,col])

#创建一张新的灰度图保存聚类后的结果
pic_new = image.new("L", (row, col))

#根据所属类别向图片中添加灰度值
for i in range(row):
    for j in range(col):
        pic_new.putpixel((i,j), int(256/(label[i][j]+1)))
    
#以JPEG形式保存图像
pic_new.save("result-bull-4.jpg", "JPEG")

```


输出：

![这里写图片描述](http://img.blog.csdn.net/20170725201248959?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)