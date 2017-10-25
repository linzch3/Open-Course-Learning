class PLA_pocket:
    
    def __init__(self, w0, theta=0, method='F1'):
        '''初始化变量'''
        self.w = np.array([theta]+w0, dtype='float64')
        self.w_best = self.w.copy()
        self.method = method   #用于指定更新最优 w 的评估指标
    
    def __addOne2Samples(self, dataSet):
        '''给每一个样本前加一个常数1'''
        ones = np.ones(len(dataSet))
        dataSet = np.array(dataSet)
        return np.column_stack((ones, dataSet))
    
    def __apply2TrainSet(self, trainSet):
        '''根据当前的 w 对训练集进行划分，在计算评估值时调用'''
        outputLabel = np.zeros(trainSet.shape[0])
        for index, sample in enumerate(trainSet):
            outputLabel[index] = np.sign(np.dot(sample, self.w))
        return outputLabel
    
    def __calcEvaluation(self, trainSet, label):
        '''计算当前 w 的用指定指标评估的效果'''
        #得到当前 w 对训练集进行划分结果
        outputLabel_ = self.__apply2TrainSet(trainSet)
        #得到划分的评估效果
        evaluateAns = evaluation(outputLabel_, label, False)
        #返回指定评估结果
        return evaluateAns[self.method]
    
    def fit(self, trainSet, label, maxRunTimes=100):
        '''根据给定的训练集和标签训练PLA的参数 w '''
        label = np.array(label, dtype='float64')
        #给每一个样本前加一个常数1
        trainSet = self.__addOne2Samples(trainSet)
        maxEvaluatioin = -0.1
        cnt = 1
        while cnt <= maxRunTimes:
            cnt += 1
            #遍历所有样本
            for index, sample in enumerate(trainSet):
                #更新预测错误的样本
                if np.sign(np.dot(sample, self.w)) != label[index]:
                    self.w += label[index]*sample
                    #若检查是否更新最优的 w 
                    currentEvaluatioin = self.__calcEvaluation(trainSet, label)
                    if currentEvaluatioin > maxEvaluatioin:
                        self.w_best = self.w.copy()
                        maxEvaluatioin = currentEvaluatioin
                        
    def apply(self, otherSet):
        '''根据已训练出最优的 w 对其他数据集进行划分'''
        otherSet = self.__addOne2Samples(otherSet)
        outputLabel = np.zeros(otherSet.shape[0])
        for index, sample in enumerate(otherSet):
            outputLabel[index] = np.sign(np.dot(sample, self.w_best))
        return outputLabel
    
    def getW(self):
        return self.w_best

############测试程序###################
a = [[-4, -1], [0, 3]] # trainSet
b = [1,-1]             # label
c = [1, 1]             # w0
d = 1                  # theta
e = [[-2, 3]]          # otherSet

p = PLA_pocket(w0=c, theta=d)    # 得到PLA类
p.fit(a, b, maxRunTimes=10)      # 给定的训练集和标签，训练PLA的参数 w
print("最终训练得到的参数 w 为：", p.getW())  
print("对测试数据集 %s 划分的结果为： %s " %(e, p.apply(e)))
############测试程序###################