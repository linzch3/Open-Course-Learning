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
