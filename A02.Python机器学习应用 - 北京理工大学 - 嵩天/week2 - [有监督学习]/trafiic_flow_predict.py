import numpy as np
import pandas as pd
from sklearn.linear_model import Ridge#加载岭回归方法
from sklearn import cross_validation
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures #用于创建多项式特征，如ab、 a2、 b2

data=pd.read_csv('traffic flow.csv')
#绘制车流量信息
plt.plot(data['TRAFFIC_COUNT'])
plt.show()

X=data[data.columns[1:5]]#属性数据
y=data['TRAFFIC_COUNT']#车流量数据（即是要预测的数据）
poly=PolynomialFeatures(5)#测试后5是效果较好的一个参数
#X为创建的多项式特征
X=poly.fit_transform(X)
#将所有数据划分为训练集和测试集，test_size表示测试集的比例，random_state是随机数种子
train_set_X, test_set_X , train_set_y, test_set_y = cross_validation.train_test_split(X,y,test_size=0.3,random_state=0)
#创建岭回归实例
clf=Ridge(alpha=1.0,fit_intercept = True)
#调用fit函数使用训练集训练回归器
clf.fit(train_set_X,train_set_y)
#利用测试集计算回归曲线的拟合优度，clf.score返回值为0.7375
#拟合优度，用于评价拟合好坏，最大为1，无最小值，当对所有输入都输出同一个值时，拟合优度为0。
clf.score(test_set_X,test_set_y)

start=200 #接下来我们画一段200到300范围内的拟合曲线
end=300
y_pre=clf.predict(X) #是调用predict函数的拟合值
time=np.arange(start,end)
plt.plot(time,y[start:end],'b', label="real")
plt.plot(time,y_pre[start:end],'r', label='predict')
#展示真实数据（蓝色）以及拟合的曲线（红色）
plt.legend(loc='upper left') #设置图例的位置
plt.show()