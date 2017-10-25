class PLA_origin:
    
    def __init__(self, w0, theta=0):
        '''初始化变量'''
        self.w = np.array([theta]+w0, dtype='float64')
    
    def __addOne2Samples(self, dataSet):
        '''给每一个样本前加一个常数1'''
        ones = np.ones(len(dataSet))
        dataSet = np.array(dataSet)
        return np.column_stack((ones, dataSet))
    
    def fit(self, trainSet, label, maxRunTimes=100):
        '''根据给定的训练集和标签训练PLA的参数 w '''
        label = np.array(label, dtype='float64')
        #给每一个样本前加一个常数1
        trainSet = self.__addOne2Samples(trainSet)
        cnt = 1
        while cnt <= maxRunTimes:
            cnt += 1
            #遍历所有样本
            for index, sample in enumerate(trainSet):
                #更新预测错误的样本
                if np.sign(np.dot(sample, self.w)) != label[index]:
                    self.w += label[index]*sample
                    
    def apply(self, otherSet):
        '''根据已训练出的 w 对其他数据集进行划分'''
        otherSet = self.__addOne2Samples(otherSet)
        outputLabel = np.zeros(otherSet.shape[0])
        for index, sample in enumerate(otherSet):
            outputLabel[index] = np.sign(np.dot(sample, self.w))
        return outputLabel
    
    def getW(self):
        return self.w

############测试程序###################
a = [[-4, -1], [0, 3]] # trainSet
b = [1,-1]             # label
c = [1, 1]             # w0
d = 1                  # theta
e = [[-2, 3]]          # otherSet

p = PLA_origin(w0=c, theta=d) # 得到PLA类
p.fit(a, b, maxRunTimes=10)      # 给定的训练集和标签，训练PLA的参数 w
print("最终训练得到的参数 w 为：", p.getW())  
print("对测试数据集 %s 划分的结果为： %s " %(e, p.apply(e)))
############测试程序###################