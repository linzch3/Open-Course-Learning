import pandas as pd
import numpy as np
from sklearn import svm
from sklearn import cross_validation
 
#pandas.read_csv（数据源, encoding=编码格式为utf-8， parse_dates=第0列解析为日期， index_col=用作行索引的列编号）
data=pd.read_csv('stock/000777.csv',encoding='utf-8',parse_dates=[0],index_col=0)
#DataFrame.sort_index(axis=0 (按0列排), ascending=True（升序）, inplace=False（排序后是否覆盖原数据））
#data 按照时间升序排列
data.sort_index(0,ascending=True,inplace=True)
 

dayfeature=150#选取150天的数据
featurenum=5*dayfeature#选取5列数据作为特征：收盘价 最高价 最低价 开盘价 成交量
sampleNum=data.shape[0]
'''
data.shape[0]-dayfeature意思是因为我们要用150天数据做训练，对于条目为200条的数据，只有50条数
据是有前150天的数据来训练的，所以训练集的大小就是200-150， 对于每一条数据，他的特征是前150
天的所有特征数据，即150*5， +1是将当天的开盘价引入作为一条特征数据
'''
x=np.zeros((sampleNum-dayfeature,featurenum+1))#记录150天的5个特征值
y=np.zeros((sampleNum-dayfeature))#记录涨或者跌

#对于特征数据
for i in range(0,sampleNum-dayfeature):
    #每条数据的特征为该数据前面的150天的所有特征数据+当天的开盘价
    x[i,0:featurenum]=np.array(data[i:i+dayfeature][['收盘价','最高价','最低价','开盘价','成交量']]).reshape((1,featurenum))#转换成行向量
    x[i,featurenum]=data.ix[i+dayfeature]['开盘价']
#对于标签数据
for i in range(0,sampleNum-dayfeature):
    #如果当天收盘价高于开盘价，y[i]=1代表涨，0代表跌
    if data.ix[i+dayfeature]['收盘价']>=data.ix[i+dayfeature]['开盘价']:
        y[i]=1
    else:
        y[i]=0          
 
 #调用svm函数，并设置kernel参数，默认是rbf，其它：‘linear’‘poly’‘sigmoid’
clf=svm.SVC(kernel='rbf')
result = []
#5次交叉验证
for i in range(5):
    #x和y的验证集和测试集，切分80-20%的测试集
    x_train, x_test, y_train, y_test = cross_validation.train_test_split(x, y, test_size = 0.2)
    #训练
    clf.fit(x_train, y_train)
    #训练集与预测结果进行对比
    result.append(np.mean(y_test == clf.predict(x_test)))
print("svm classifier accuacy:")
print(result)