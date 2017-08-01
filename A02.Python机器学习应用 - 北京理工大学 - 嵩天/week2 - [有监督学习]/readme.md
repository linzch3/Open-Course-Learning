<!-- TOC -->

- [本周课程导学](#本周课程导学)
    - [监督学习的目标](#监督学习的目标)
    - [分类学习](#分类学习)
    - [sklearn中的分类算法](#sklearn中的分类算法)
    - [回归分析](#回归分析)
    - [sklearn中的回归算法](#sklearn中的回归算法)
- [单元4：分类](#单元4分类)
    - [4.0.交叉验证](#40交叉验证)
    - [4.1.K近邻分类器(KNN)](#41k近邻分类器knn)
    - [4.2.sklearn中的K近邻分类器](#42sklearn中的k近邻分类器)
    - [4.3.决策树](#43决策树)
    - [4.4.sklearn中的决策树](#44sklearn中的决策树)
    - [4.5.朴素贝叶斯](#45朴素贝叶斯)
    - [4.6.sklearn中的朴素贝叶斯](#46sklearn中的朴素贝叶斯)
    - [4.7.人体运动状态预测-实例分析](#47人体运动状态预测-实例分析)
    - [4.8.上证指数涨跌预测-实例分析](#48上证指数涨跌预测-实例分析)
- [单元5：回归](#单元5回归)
    - [5.1.线性回归](#51线性回归)
    - [5.2.房价与房屋尺寸关系的线性拟合](#52房价与房屋尺寸关系的线性拟合)
    - [5.3.多项式回归](#53多项式回归)
    - [5.4.房价与房屋尺寸关系的非线性拟合](#54房价与房屋尺寸关系的非线性拟合)
    - [5.5.岭回归](#55岭回归)
    - [5.6.交通流量预测实例](#56交通流量预测实例)
- [单元6：手写数字识别实例分析](#单元6手写数字识别实例分析)
    - [6.1.实例介绍](#61实例介绍)
    - [6.2.利用全连接的神经网络实现手写识别的任务](#62利用全连接的神经网络实现手写识别的任务)
    - [6.3.利用KNN实现手写识别的任务](#63利用knn实现手写识别的任务)

<!-- /TOC -->
## 本周课程导学
### 监督学习的目标
利用一组带有标签的数据，学习从输入到输出的映射，然后将这种映射关系应用到未知数据上，达到分类或回归的目的。

 - 分类：当输出是离散的，学习任务为分类任务。
 
 - 回归：当输出是连续的，学习任务为回归任务。

### 分类学习

![这里写图片描述](http://img.blog.csdn.net/20170726100922887?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

• 输入：一组有标签的训练数据(也称观察和评估)，标签表明了这些数据（观察）的所署类别。

• 输出：分类模型根据这些训练数据，训练自己的模型参数，学习出一个适合这组数据的分类器，当有新数据（非训练数据）需要进行类别判断，就可以将这组新数据作为输入送给学好的分类器进行判断。

• 训练集(training set): 顾名思义用来训练模型的已标注数据，用来建立模型，发现规律。

• 测试集(testing set): 也是已标注数据，通常做法是将标注隐藏，输送给训练好的模型，通过结果与真实标注进行对比，评估模型的学习能力。

训练集/测试集的划分方法：根据已有标注数据，随机选出一部分数据（70%）数据作为训练数据，余下的作为测试数据，此外还有交叉验证法，自助法用来评估分类模型。

**->评价标准**

精确率：

是针对我们预测结果而言的，（以二分类为例）它表示的是**预测为正的样本中有多少是真正的正样本**。那么预测为正就有两种可能了，一种就是把正类预测为正类(TP)，另一种就是把负类预测为正类(FP)，也就是：P=TP/(TP+FP)

召回率：

是针对我们原来的样本而言的，它表示的是**样本中的正例有多少被预测正确**。那也有两种可能，一种是把原来的正类预测成正类(TP)，另一种就是把原来的正类预测为负类(TN)，也就是：P=TP/(TP+TN)

举例：
假设我们手上有60个正样本，40个负样本，我们要找出所有的正样本，分类算法查找出50个，其中只有40个是真正的正样本。

TP: 将正类预测为正类数 40；
TN: 将正类预测为负类数 20；
FP: 将负类预测为正类数 10；
FN: 将负类预测为负类数 30。

准确率（accuracy）= (TP+FN)/(TP+FN+FP+TN) =(40+30)/100=70%
精确率（precision）=TP/(TP+FP)=40/(50)=80%
召回率（recall）=TP/(TP+TN)=40/60=66.7%

->应用

 金融：贷款是否批准进行评估

 医疗诊断：判断一个肿瘤是恶性还是良性

 欺诈检测：判断一笔银行的交易是否涉嫌欺诈

 网页分类：判断网页的所属类别，财经或者是娱乐？

### sklearn中的分类算法

与聚类算法被统一封装在sklearn.cluster模块不同，sklearn库中的分类算法并未被统一封装在一个子模块中，因此对分类算法的import方式各有不同。

Sklearn提供的分类函数包括：

• k近邻（knn）

• 朴素贝叶斯（naivebayes）

• 支持向量机（svm）

• 决策树 （decision tree）

• 神经网络模型（Neural networks）等

• 这其中有线性分类器，也有非线性分类器。

### 回归分析

![这里写图片描述](http://img.blog.csdn.net/20170726100941545?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

回归：

统计学分析数据的方法，目的在于**了解两个或多个变数间是否相关、研究其相关方向与强度**，**并建立数学模型以便观察特定变数来预测研究者感兴趣的变数**。

回归分析可以帮助人们了解在自变量变化时因变量的变化量。一般来说，通过回归分析我们可以由给出的自变量估计因变量的条件期望。

->应用

回归方法适合对一些**带有时序信息的数据进行预测或者趋势拟合**，常用在金融及其他涉及时间序列分析的领域：

• 股票趋势预测

• 交通流量预测

### sklearn中的回归算法

Sklearn提供的回归函数主要被封装在两个子模块中，分别是sklearn.linear_model和sklearn.preprocessing。

sklearn.linear_modlel封装的是一些线性函数，线性回归函数包括有：

• 普通线性回归函数（ LinearRegression ）
• 岭回归（Ridge）
• Lasso（Lasso）

非线性回归函数，如多项式回归（PolynomialFeatures）则通过sklearn.preprocessing子模块进行调用。

## 单元4：分类

首先介绍交叉验证和几个基本的分类器模型。

### 4.0.交叉验证

基本思想：

交叉验证法先将数据集D划分为k个大小相似的互斥子集，每个自己都尽可能保持数据分布的一致性，即从D中通过**分层采样**得到。 然后，**每次用k-1个子集的并集作为训练集，余下的那个子集作为测试集**；这样就可获得k组训练/测试集，从而可进行k次训练和测试，**最终返回的是这个k个测试结果的均值**。 通常把交叉验证法称为“k者交叉验证” , k最常用的取值是10，此时称为10折交叉验证。

![这里写图片描述](http://img.blog.csdn.net/20170726152734140?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.1.K近邻分类器(KNN)

KNN：通过计算待分类数据点，与已有数据集中的所有数据点的距离。取距离最小的前K个点，根据“少数服从多数“的原则，将这个数据点划分为出现次数最多的那个类别。

![这里写图片描述](http://img.blog.csdn.net/20170726104021603?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.2.sklearn中的K近邻分类器

在sklearn库中，可以使用**sklearn.neighbors.KNeighborsClassifier**创建一个K近邻分类器，主要参数有：

• n_neighbors：用于指定分类器中K的大小(默认值为5，注意与kmeans的区别)

• weights：设置选中的K个点对分类结果影响的权重（默认值为平均权重“uniform”，可以选择“distance”代表越近的点权重越高，或者传入自己编写的以距离为参数的权重计算函数）

• algorithm：设置用于计算临近点的方法，因为当数据量很大的情况下计算当前点和所有点的距离再选出最近的k各点，这个计算量是很费时的，所以（选项中有ball_tree、 kd_tree和brute，分别代表不同的寻找邻居的优化算法，默认值为auto，根据训练数据自动选择）

**->使用方法**

举例：
```python
from sklearn.neighbors import KNeighborsClassifier
#创建一组数据 X 和它对应的标签 y
X=[[0],[1],[2],[3]]
y=[0,0,0,1]
#使用最近的3个邻居作为分类的依据，得到分类器
neigh = KNeighborsClassifier(n_neighbors=3)
#将训练数据 X 和 标签 y 送入分类器进行学习
neigh.fit(X, y)
#调用 predict() 函数，对未知分类样本 [1.1] 分类，可以直接并将需要分类
#的数据构造为数组形式作为参数传入，得到分类标签作为返回值
print(neigh.predict([[1.1]]))
```

样例输出值是 0，表示K近邻分类器通过计算样本 [1.1] 与训练数据的距离，取 0,1,2 这 3 个邻居作为依据，根据“投票法”最终将样本分为类别 0。

**->使用经验**

在实际使用时，我们可以使用所有训练数据构成特征 X 和标签 y，使用fit() 函数进行训练。

在正式分类时，通过一次性构造测试集或者一个一个输入样本的方式，得到样本对应的分类结果。

有关K 的取值：

• 如果较大，相当于使用较大邻域中的训练实例进行预测，可以减小估计误差，但是距离较远的样本也会对预测起作用，导致预测错误。

• 相反地，如果 K 较小，相当于使用较小的邻域进行预测，如果邻居恰好是噪声点，会导致过拟合。

• 一般情况下，K 会倾向选取较小的值，并使用交叉验证法选取最优 K 值。

### 4.3.决策树

决策树是一种树形结构的分类器，通过顺序询问分类点的属性决定分类点最终的类别。通常根据特征的信息增益或其他指标，构建一颗决策树。在分类时，只需要按照决策树中的结点依次进行判断，即可得到样本所属类别。

举例：

根据下图这个构造好的分类决策树，一个无房产，单身，年收入55K的人的会被归入无法偿还信用卡这个类别。
![这里写图片描述](http://img.blog.csdn.net/20170726133849757?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.4.sklearn中的决策树

在sklearn库中，可以使用sklearn.tree.DecisionTreeClassifier创建一个决策树用于分类，其主要参数有：

• criterion ：用于选择属性的准则，可以传入“gini”代表基尼系数，或者“entropy”代表信息增益。

• max_features ：表示在决策树结点进行分裂时，从多少个特征中选择最优特征。可以设定固定数目、百分比或其他标准。它的默认值是使用所有特征个数。

**->使用方法**

举例：

```py
#导入鸢尾花数据集、决策树分类器、计算交叉验证值的函数 cross_val_score
from sklearn.datasets import load_iris
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import cross_val_score
#使用默认参数，创建一颗基于基尼系数的决策树，并将该决策树分类器赋值给变量 clf
clf = DecisionTreeClassifier()
iris = load_iris()
'''
这里我们将决策树分类器做为待评估的模型，iris.data鸢尾花数据做为特征，
iris.target鸢尾花分类标签做为目标结果，通过设定cv为10，使用10折交叉验
证。得到最终的交叉验证得分。
'''
print(cross_val_score(clf, iris.data, iris.target, cv=10))

```

输出：

```
[ 1.          0.93333333  1.          0.93333333  0.93333333  0.86666667
  0.93333333  0.93333333  1.          1.        ]
```

当然也可以使用如下的这种形式训练分类器并对测试集进行预测：

```Python
clf.fit(X, y)
clf.predict(x)
```

**->使用经验**

• 决策树本质上是寻找一种对特征空间上的划分，旨在构建一个与训练数据相拟合并复杂度小的决策树。

• 在实际使用中，需要根据数据情况，调整DecisionTreeClassifier类中传入的参数，比如选择合适的criterion，设置随机变量等。

### 4.5.朴素贝叶斯

朴素贝叶斯分类器是一个以贝叶斯定理为基础的多分类的分类器。

对于给定数据，首先基于特征的条件独立性假设，学习输入输出的联合概率分布，然后基于此模型，对给定的输入x，利用贝叶斯定理求出后验概率最大的输出y。

![这里写图片描述](http://img.blog.csdn.net/20170726135332258?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.6.sklearn中的朴素贝叶斯

在sklearn库中，实现了三个朴素贝叶斯分类器，如下表所示：

![这里写图片描述](http://img.blog.csdn.net/20170726135423798?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

区别在于**假设某一特征的所有属于某个类别的观测值符合特定分布**，如，分类问题的特征包括人的身高，身高符合高斯分布，这类问题适合高斯朴素贝叶斯。

在sklearn库中，可以使用sklearn.naive_bayes.GaussianNB创建一个高斯朴素贝叶斯分类器，其参数有：

• priors ：给定各个类别的先验概率。如果为空，则按训练数据的实际情况进行统计；如果给定先验概率，则在训练过程中不能更改。


**->使用方法**

举例：

```python
import numpy as np
from sklearn.naive_bayes import GaussianNB
X = np.array([[-1, -1], [-2, -1], [-3, -2], [1, 1], [2, 1], [3, 2]])
Y = np.array([1, 1, 1, 2, 2, 2])
#使用默认参数，创建一个高斯朴素贝叶斯分类器，并将该分类器赋给变量clf
clf = GaussianNB(priors=None)
clf.fit(X, Y)
print(clf.predict([[-0.8, -1]]))
```

输出：

```
[1]
```

**->使用经验**

朴素贝叶斯是典型的生成学习方法，由训练数据学习联合概率分布，并求得后验概率分布。

朴素贝叶斯一般在小规模数据上的表现很好，适合进行多分类任务。

### 4.7.人体运动状态预测-实例分析

**->背景介绍**

• 可穿戴式设备的流行，让我们可以更便利地使用传感器获取人体的各项数据，甚至生理数据。

• 当传感器采集到大量数据后，我们就可以通过对数据进行分析和建模，通过各项特征的数值进行用户状态的判断，根据用户所处的状态提供给用户更加精准、便利的服务。

**->数据介绍**

• 我们现在收集了来自 A,B,C,D,E 5位用户的可穿戴设备上的传感器数据，每位用户的数据集包含一个特征文件（a.feature）和一个标签文件（a.label）。

• 特征文件中每一行对应一个时刻的所有传感器数值，标签文件中每行记录了和特征文件中对应时刻的标记过的用户姿态，两个文件的行数相同，相同行之间互相对应。

• ?号表示缺失值

**------->关于特征文件**

特征文件的各项特征具体如下表所示：

![这里写图片描述](http://img.blog.csdn.net/20170726141249617?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

在传感器1对应的13列数据特征中，包含：1项温度数据、 3项一型三轴加速度数据、 3项二型三轴加速度数据、 3项三轴陀螺仪数据和3项三轴磁场数据。

![这里写图片描述](http://img.blog.csdn.net/20170726141346257?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

人体的温度数据可以反映当前活动的剧烈程度，一般在静止状态时，体温趋于稳定在36.5度上下；当温度高于37度时，可能是进行短时间的剧烈运动，比如跑步和骑行。

在数据中有两个型号的加速度传感器，可以通过互相印证的方式，保证数据的完整性和准确性。 通过加速度传感器对应的三个数值，可以知道空间中x、 y、z三个轴上对应的加速度，而空间上的加速度和用户的姿态有密切的关系，比如用户向上起跳时，z轴上的加速度会激增。

陀螺仪是角运动检测的常用仪器，可以判断出用户佩戴传感器时的身体角度是水平、倾斜还是垂直。直观地，通过这些数值都是推断姿态的重要指标。

磁场传感器可以检测用户周围的磁场强度和数值大小，这些数据可以帮助我们理解用户所处的环境。比如在一个办公场所，用户座位附近的磁场是大体上固定的，当磁场发生改变时，我们可以推断用户的位置和场景发生了变化。

**------->关于标签文件**

标签文件内容如图所示，每一行只有一个数字，代表与特征文件中对应行的用户姿态类别。总共有0-24共25种身体姿态，如，无活动状态，坐态、跑态等。标签文件作为训练集的标准参考准则，可以进行特征的监督学习。

**->任务介绍**

 假设现在出现了一个新用户，但我们只有传感器采集的数据，那么该如何得到这个新用户的姿态呢？

 又或者对同一用户如果传感器采集了新的数据，怎么样根据新的数据判断当前用户处于什么样的姿态呢？

在明确这是一个分类问题的情况下，我们可以选定某种分类模型（或者说是算法），通过使用训练数据进行模型学习，然后对每个测试样本给出对应的分类结果。

**->思路**

这里使用K近邻、决策树和朴素贝叶斯3个分类算法实现该思路。

step 1: 需要从特征文件和标签文件中将所有数据加载到内存中，由于存在**缺失值**，此步骤还需要进行简单的数据预处理。

step 2: 创建对应的分类器，并使用训练数据进行训练。

step 3: 利用测试集预测，通过使用真实值和预测值的比对，计算模型整体的准确率和召回率，来评测模型。

算法流程对应如下：

![这里写图片描述](http://img.blog.csdn.net/20170726142131496?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**->代码实现**

```python
import pandas as pd
import numpy as np  

from sklearn.preprocessing import Imputer# 导入预处理模块Imputer
from sklearn.cross_validation import train_test_split #导入自动生成训练集和测试集的模块train_test_split
from sklearn.metrics import classification_report# 导入预测结果评估模块classification_report
#导入分类器
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.naive_bayes import GaussianNB
 
def load_datasets(feature_paths, label_paths):
    '''
    读取特征文件和标签文件并返回
    '''
    #定义feature数组变量，列数量和特征维度一致为41；定义空的标签变量，列数量与标签维度一致为1
    feature = np.ndarray(shape=(0,41))
    label = np.ndarray(shape=(0,1))
    for file in feature_paths:
        #使用pandas库的read_table函数读取一个特征文件的内容，其中指定分隔符为逗号、缺失值为问号且文件不包含表头行
        df = pd.read_table(file, delimiter=',', na_values='?', header=None)
        #使用Imputer函数，通过设定strategy参数为‘mean’，使用平均值对缺失数据进行补全。 
        imp = Imputer(missing_values='NaN', strategy='mean', axis=0)
        #fit()函数用于训练预处理器，transform()函数用于生成预处理结果。
        imp.fit(df)
        df = imp.transform(df)
        #将预处理后的数据加入feature，依次遍历完所有特征文件
        feature = np.concatenate((feature, df))
    
    #读取标签文件
    for file in label_paths:
        df = pd.read_table(file, header=None)
        label = np.concatenate((label, df))
    #将标签归整化为一维向量    
    label = np.ravel(label)
    return feature, label
 
if __name__ == '__main__':
    ''' 数据路径 '''
    featurePaths = ['A/A.feature','B/B.feature','C/C.feature','D/D.feature','E/E.feature']
    labelPaths = ['A/A.label','B/B.label','C/C.label','D/D.label','E/E.label']
    
    ''' 读入数据  '''
    x_train,y_train = load_datasets(featurePaths[:4],labelPaths[:4])
    x_test,y_test = load_datasets(featurePaths[4:],labelPaths[4:])
    
    #使用train_test_split()函数，通过设置测试集比例test_size为0，将数据随机打乱，便于后续分类器的初始化和训练。
    x_train, x_, y_train, y_ = train_test_split(x_train, y_train, test_size = 0.0)
     
    print('Start training knn')
    knn = KNeighborsClassifier().fit(x_train, y_train)
    print('Training done')
    answer_knn = knn.predict(x_test)
    print('Prediction done')
     
    print('Start training DT')
    dt = DecisionTreeClassifier().fit(x_train, y_train)
    print('Training done')
    answer_dt = dt.predict(x_test)
    print('Prediction done')
     
    print('Start training Bayes')
    gnb = GaussianNB().fit(x_train, y_train)
    print('Training done')
    answer_gnb = gnb.predict(x_test)
    print('Prediction done')
     
    print('\n\nThe classification report for knn:')
    print(classification_report(y_test, answer_knn))
    print('\n\nThe classification report for DT:')
    print(classification_report(y_test, answer_dt))
    print('\n\nThe classification report for Bayes:')
    print(classification_report(y_test, answer_gnb))
```

输出：

```py
Start training knn
Training done
Prediction done
Start training DT
Training done
Prediction done
Start training Bayes
Training done
Prediction done


The classification report for knn:
             precision    recall  f1-score   support

        0.0       0.56      0.60      0.58    102341
        1.0       0.92      0.93      0.93     23699
        2.0       0.94      0.78      0.85     26864
        3.0       0.83      0.82      0.82     22132
        4.0       0.85      0.88      0.87     32033
        5.0       0.39      0.21      0.27     24646
        6.0       0.77      0.89      0.82     24577
        7.0       0.80      0.95      0.87     26271
       12.0       0.32      0.33      0.33     14281
       13.0       0.16      0.22      0.19     12727
       16.0       0.90      0.67      0.77     24445
       17.0       0.89      0.96      0.92     33034
       24.0       0.00      0.00      0.00      7733

avg / total       0.69      0.69      0.68    374783



The classification report for DT:
             precision    recall  f1-score   support

        0.0       0.48      0.73      0.58    102341
        1.0       0.66      0.96      0.78     23699
        2.0       0.84      0.86      0.85     26864
        3.0       0.93      0.72      0.81     22132
        4.0       0.23      0.16      0.19     32033
        5.0       0.62      0.52      0.57     24646
        6.0       0.76      0.57      0.65     24577
        7.0       0.32      0.15      0.20     26271
       12.0       0.60      0.67      0.63     14281
       13.0       0.67      0.47      0.56     12727
       16.0       0.57      0.07      0.13     24445
       17.0       0.84      0.85      0.85     33034
       24.0       0.38      0.29      0.33      7733

avg / total       0.59      0.59      0.56    374783



The classification report for Bayes:
             precision    recall  f1-score   support

        0.0       0.62      0.81      0.70    102341
        1.0       0.97      0.91      0.94     23699
        2.0       1.00      0.65      0.79     26864
        3.0       0.60      0.66      0.63     22132
        4.0       0.91      0.77      0.83     32033
        5.0       1.00      0.00      0.00     24646
        6.0       0.87      0.72      0.79     24577
        7.0       0.31      0.47      0.37     26271
       12.0       0.52      0.59      0.55     14281
       13.0       0.61      0.50      0.55     12727
       16.0       0.89      0.72      0.79     24445
       17.0       0.75      0.91      0.82     33034
       24.0       0.59      0.24      0.34      7733

avg / total       0.74      0.68      0.67    374783
```

**->结果分析**

![这里写图片描述](http://img.blog.csdn.net/20170726150800137?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

 从准确度的角度衡量，贝叶斯分类器的效果最好

 从召回率和F1值的角度衡量，k近邻效果最好

 贝叶斯分类器和k近邻的效果好于决策树

**->思考**

 在所有的特征数据中，可能存在缺失值或者冗余特征。如果将这些特征不加处理地送入后续的计算，可能会导致模型准确度下降并且增大计算量。

 在特征选择阶段，通常需要借助辅助软件（例如Weka）将数据进行可视化并进行统计。

### 4.8.上证指数涨跌预测-实例分析

**数据介绍：**

网易财经上获得的上证指数的历史数据，爬取了20年的上证指数数据。

**实验目的：**

根据给出当前时间**前150天**的历史数据，预测**当天**上证指数的涨跌。

**技术路线：**  sklearn.svm.SVC

**数据实例：**中核科技1997年到2017年的股票数据部分截图，红框部分为选取的特征值

![这里写图片描述](http://img.blog.csdn.net/20170726152510457?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

数据存放在000777.csv文件中：

![这里写图片描述](http://img.blog.csdn.net/20170726154008020?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**代码实现：**

```python
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
```

输出：

```python
svm classifier accuacy:
[0.53854505971769817, 0.53311617806731815, 0.54831704668838221, 0.52768729641693812, 0.53963083604777418]
```

**实验结果：**

![这里写图片描述](http://img.blog.csdn.net/20170726154834366?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

本次实验运用了两个核函数做实验，准确率由表中数据所示。 5次交叉验证的准确率相近，均为53%左右。

## 单元5：回归

### 5.1.线性回归

线性回归(Linear Regression)是利用数理统计中回归分析，来确定两种或两种以上**变量间相互依赖**的定量关系的一种**统计分析方法**，其使用形如y=wTx+b的线性模型拟合数据输入和输出之间的映射关系的。

![这里写图片描述](http://img.blog.csdn.net/20170726163508022?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

线性回归利用称为线性回归方程的**最小平方函数**对一个或多个自变量和因变量之间关系进行建模。 这种函数是一个或多个称为**回归系数的**模型参数的**线性组合**。

只有一个自变量的情况称为**简单回归**,大于一个自变量情况的叫做**多元回归**。

**->实际用途**

线性回归有很多实际的用途，分为以下两类：

1.如果目标是预测或者映射，线性回归可以用来对观测数据集的y和X的值拟合出一个预测模型。当完成这样一个模型以后，对于一个新增的X值，在没有给定与它相配对的y的情况下，可以用这个拟合过的模型预测出一个y值。（利用数据拟合模型进行预测）

2.给定一个变量y和一些变量X1, ⋯ ,Xn,这些变量有可能与y相关，线性回归分析可以用来量化y与Xi 之间相关性的强度，评估出与y不相关的Xi ，并识别出哪些Xi的子集包含了关于y的冗余信息。(相关性分析去除冗余)

### 5.2.房价与房屋尺寸关系的线性拟合

背景：

与房价密切相关的除了单位的房价，还有房屋的尺寸。我们可以根据已知的房屋成交价和房屋的尺寸进行线性回归，继而可以对已知房屋尺寸，而未知房屋成交价格的实例进行成交价格的预测。

目标：

对房屋成交信息建立回归方程，并依据回归方程对房屋价格进行预测

技术路线：

sklearn.linear_model.LinearRegression

调用sklearn.linear_model.LinearRegression()所需参数：

• fit_intercept : 布尔型参数，表示是否计算该模型截距。可选参数。

• normalize : 布尔型参数，若为True，则X在回归前进行归一化。可选参数。默认值为False。

• copy_X : 布尔型参数，若为True，则X将被复制；否则将被覆盖。 可选参数。默认值为True。

• n_jobs : 整型参数，表示用于计算的作业数量；若为-1，则用所有的CPU。可选参数。默认值为1。


数据：

```
prices.txt
房屋面积(单位:平方英尺) 房屋成交价格(单位:万)
```

```py
1000,168
792,184
1260,197
1262,220
1240,228
1170,248
1230,305
1255,256
1194,240
1450,230
1481,202
1475,220
1482,232
1484,460
1512,320
1680,340
1620,240
1720,368
1800,280
4400,710
4212,552
3920,580
3212,585
3151,590
3100,560
2700,285
2612,292
2705,482
2570,462
2442,352
2387,440
2292,462
2308,325
2252,298
2202,352
2157,403
2140,308
4000,795
4200,765
3900,705
3544,420
2980,402
4355,762
3150,392
```

**->可行性分析**

• 简单而直观的方式是通过数据的可视化直接观察房屋成交价格与房屋尺寸间是否存在线性关系。

• 对于本实验的数据来说，散点图就可以很好的将其在二维平面中进行可视化表示。
	
绘出如下散点图，可发现两者是有一定关系的。

![这里写图片描述](http://img.blog.csdn.net/20170726164501116?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**->代码实现**

```python
import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
 
# 读取数据集
datasets_X = []#房屋面积
datasets_Y = []#房屋价格
fr = open('prices.txt','r')
lines = fr.readlines()
for line in lines:
    items = line.strip().split(',')
    datasets_X.append(int(items[0]))#注意加上类型转换
    datasets_Y.append(int(items[1]))
 

#将datasets_X转换为二维数组，以符合 linear.fit 函数的参数要求
datasets_X = np.array(datasets_X).reshape([-1,1])
datasets_Y = np.array(datasets_Y)
 
#以数据datasets_X的最大值和最小值为范围，建立等差数列，方便后续画图。
minX = min(datasets_X)
maxX = max(datasets_X)
X = np.arange(minX,maxX).reshape([-1,1])
 
 
linear = linear_model.LinearRegression()
linear.fit(datasets_X, datasets_Y)
 
# 图像中显示

plt.scatter(datasets_X, datasets_Y, color = 'red',label='origin data')
plt.plot(X, linear.predict(X), color = 'blue',label='linear regression prediction')
plt.legend()#使label生效
plt.xlabel('Area')
plt.ylabel('Price')
plt.show()
```

输出：

![这里写图片描述](http://img.blog.csdn.net/20170726165919198?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.3.多项式回归

多项式回归(Polynomial Regression)是研究一个因变量与一个或多个自变量间多项式的回归分析方法。

如果自变量只有一个时，称为一元多项式回归；如果自变量有多个时，称为多元多项式回归。

一元m次多项式回归方程为： ![这里写图片描述](http://img.blog.csdn.net/20170726170224565?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

 二元二次多项式回归方程为： ![这里写图片描述](http://img.blog.csdn.net/20170726170231391?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**->什么时候用多项式回归**

在一元回归分析中，如果依变量y与自变量x的关系为非线性的，但是又找不到适当的函数曲线来拟合，则可以采用一元多项式回归。

**多项式回归的最大优点就是可以通过增加x的高次项对实测点进行逼近，直至满意为止。**

事实上，多项式回归可以**处理相当一类非线性问题**，它在回归分析中占有重要的地位，**因为任一函数都可以分段用多项式来逼近。**

![这里写图片描述](http://img.blog.csdn.net/20170726170455706?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


### 5.4.房价与房屋尺寸关系的非线性拟合

应用背景：

我们在前面已经根据已知的房屋成交价和房屋的尺寸进行了线性回归，继而可以对已知房屋尺寸，而未知房屋成交价格的实例进行了成交价格的预测，但是在实际的应用中这样的拟合往往不够好，因此我们在此对该数据集进行多项式回归。

目标：

对房屋成交信息建立多项式回归方程，并依据回归方程对房屋价格进行预测

技术路线：

sklearn.preprocessing.PolynomialFeatures

sklearn中的多项式回归实际上是先将变量X处理成多项式特征，然后使用线性模型学习多项式特征的参数，以达到多项式回归的目的。

例如：X = [x1, x2]

1.使用PolynomialFeatures构造X的二次多项式特征X_Poly:

![X_Poly = \[x1, x2, x1x2, x12, x22\]](http://img.blog.csdn.net/20170726170847349?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

2.使用linear_model学习X_Poly和y之间的映射关系，即参数：

![这里写图片描述](http://img.blog.csdn.net/20170726170903223?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

代码实现：

```py
import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
from sklearn.preprocessing import PolynomialFeatures
 
 
# 读取数据集
datasets_X = []
datasets_Y = []
fr = open('prices.txt','r')
lines = fr.readlines()
for line in lines:
    items = line.strip().split(',')
    datasets_X.append(int(items[0]))
    datasets_Y.append(int(items[1]))
 

datasets_X = np.array(datasets_X).reshape([-1,1])
datasets_Y = np.array(datasets_Y)
 
minX = min(datasets_X)
maxX = max(datasets_X)
X = np.arange(minX,maxX).reshape([-1,1])
 
 
poly_reg = PolynomialFeatures(degree = 2)
X_poly = poly_reg.fit_transform(datasets_X)
#使用线性模型学习X_poly和datasets_Y之间的映射关系（即参数）
lin_reg_2 = linear_model.LinearRegression()
lin_reg_2.fit(X_poly, datasets_Y)
 
# 图像中显示
plt.scatter(datasets_X, datasets_Y, color = 'red',label='origin data')
plt.plot(X, lin_reg_2.predict(poly_reg.fit_transform(X)), color = 'blue',label='Polynomial regression prediction')
plt.legend()#使label生效
plt.xlabel('Area')
plt.ylabel('Price')
plt.show()
```

输出：

![这里写图片描述](http://img.blog.csdn.net/20170726171248907?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.5.岭回归

再谈线性回归

对于一般地线性回归问题，参数的求解采用的是最小二乘法，其目标函数如下：![这里写图片描述](http://img.blog.csdn.net/20170726181457262?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

参数w的求解可为：![这里写图片描述](http://img.blog.csdn.net/20170726181526621?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

对于矩阵X，若某些列**线性相关性较大**（即训练样本中某些属性线性相关），就会导致的![这里写图片描述](http://img.blog.csdn.net/20170726181657017?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)值接近0，在计算 ![这里写图片描述](http://img.blog.csdn.net/20170726181720163?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)时就会出现不稳定性。

因此可得结论：传统的基于最小二乘的线性回归法缺乏稳定性。

**回到岭回归：**

其优化目标为：![这里写图片描述](http://img.blog.csdn.net/20170726181848525?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

参数w的求解方法为： ![这里写图片描述](http://img.blog.csdn.net/20170726181925890?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

岭回归(ridge regression)是一种专用于**共线性数据分析的有偏估计回归方法**

也是一种**改良的最小二乘估计法**，对某些数据的拟合要强于最小二乘法。

**sklearn中的岭回归**

在sklearn库中，可以使用sklearn.linear_model.Ridge调用岭回归模型，其主要参数有：

• alpha：正则化因子，对应于损失函数中的alpha

• fit_intercept：表示是否计算截距，

• solver：设置计算参数的方法，可选参数‘auto’、‘svd’、‘sag’等

### 5.6.交通流量预测实例

**数据介绍：**

数据为某路口的交通流量监测数据，记录全年小时级别的车流量。

**实验目的：**

根据已有的数据创建多项式特征，使用岭回归模型代替一般的线性模型，对车流量的信息进行多项式回归。

**技术路线：**

sklearn.linear_model.Ridgefrom

sklearn.preprocessing.PolynomialFeatures

**数据实例：**

![这里写图片描述](http://img.blog.csdn.net/20170726184248333?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

数据特征如下：

HR：一天中的第几个小时（0-23）

WEEK_DAY：一周中的第几天（0-6）

DAY_OF_YEAR：一年中的第几天（1-365）

WEEK_OF_YEAR：一年中的第几周（1-53）

TRAFFIC_COUNT：交通流量

全部数据集包含2万条以上数据（21626）

**代码实现**

```python
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
```

输出：

![这里写图片描述](http://img.blog.csdn.net/20170726191942631?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170726191950380?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**分析结论：**

预测值和实际值的走势大致相同

## 单元6：手写数字识别实例分析

### 6.1.实例介绍

**关于图像识别**

图像识别（Image Recognition）是指利用计算机对图像进行处理、分析和理解，以识别各种不同模式的目标和对像的技术。

图像识别的发展经历了三个阶段：文字识别、数字图像处理与识别、物体识别。机器学习领域一般将此类识别问题转化为**分类**问题。

**关于手写识别**

手写识别是常见的图像识别任务。计算机通过手写体图片来识别出图片中的字，与印刷字体不同的是，不同人的手写体风格迥异，大小不一，造成了计算机对手写识别任务的一些困难。

数字手写体识别由于其有限的类别（0~9共10个数字，因此也是一个**多分类任务**）成为了相对简单的手写识别任务。 DBRHD和MNIST是常用的两个数字手写识别数据集。

**关于MNIST**

MNIST的下载链接：http://yann.lecun.com/exdb/mnist/。

MNIST是一个包含数字0~9的手写体图片数据集，图片已归一化为以手写数字为中心的28*28规格的图片（每一个图片由28*28个像素点组成，每个像素点的值区间为0~255，0表示白色，255表示黑色。）。	

 MNIST由训练集与测试集两个部分组成，各部分规模如下：
 训练集：60,000个手写体图片及对应标签
 测试集：10,000个手写体图片及对应标签

![这里写图片描述](http://img.blog.csdn.net/20170726193917222?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**关于DBRHD**

DBRHD（Pen-Based Recognition of Handwritten Digits Data Set）是UCI的机器学习中心提供的数字手写体数据库：

 https://archive.ics.uci.edu/ml/datasets/Pen-Based+Recognition+of+Handwritten+Digits。

DBRHD数据集包含大量的数字0~9的手写体图片，这些图片来源于44位不同的人的手写数字，图片已归一化为以手写数字为中心的32*32规格的图片。（去掉了图片颜色等复杂因素，将手写体数字图片转化为训
练数据为大小32*32的文本矩阵空白区域使用0代表，字迹区域使用1表示。）

 DBRHD的训练集与测试集组成如下：
 训练集：7,494个手写体图片及对应标签，来源于40位手写者
 测试集：3,498个手写体图片及对应标签，来源于14位手写者

![这里写图片描述](http://img.blog.csdn.net/20170726193927345?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**手写识别研究现况**

已有许多模型在MNIST或DBRHD数据集上进行了实验，有些模型对数据集进行了偏斜矫正，甚至在数据集上进行了人为的扭曲、偏移、缩放及失真等操作以获取更加多样性的样本，使得模型更具有泛化性。

常用于数字手写体的分类器：
1） 线性分类器
 2） K最近邻分类器
3） Boosted Stumps
 4） 非线性分类器
5） SVM
 6） 多层感知器
7） 卷积神经网络

### 6.2.利用全连接的神经网络实现手写识别的任务

**任务：**

利用sklearn来训练一个简单的全连接神经网络，即多层感知机（Multilayer perceptron，MLP）用于识别数据集DBRHD的手写数字。

**MLP的输入**

DBRHD数据集的每个图片是一个由0或1组成的32*32=1024的文本矩阵；

多层感知机的输入为图片矩阵展开的1*1024个神经元。

**MLP的输出**

MLP输出：“one-hot vectors”

一个one-hot向量除了某一位的数字是1以外其余各维度数字都是0。

图片标签将表示成一个只有在第n维度（从0开始）数字为1的10维向量。比如，标签0将表示成[1,0,0,0,0,0,0,0,0,0,0]。即，**MLP输出层具有10个神经元。**

**MLP结构**

MLP的输入与输出层，中间隐藏层的层数和神经元的个数设置都将影响该MLP模型的准确率。

在本实例中，我们只设置一层隐藏层，在后续实验中比较该隐藏层神经元个数为50、 100、 200时
的MLP效果。

![这里写图片描述](http://img.blog.csdn.net/20170726201403108?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实现代码：

```python
import numpy as np     #导入numpy工具包
from os import listdir #使用listdir模块，用于访问本地文件
from sklearn.neural_network import MLPClassifier 
 
def img2vector(fileName):    
    '''将加载的32*32的图片矩阵展开成一列向量'''
    retMat = np.zeros([1024],int) #定义返回的矩阵，大小为1*1024
    fr = open(fileName)           #打开包含32*32大小的数字文件 
    lines = fr.readlines()        #读取文件的所有行
    for i in range(32):           #遍历文件所有行
        for j in range(32):       #并将01数字存放在retMat中     
            retMat[i*32+j] = lines[i][j]    
    return retMat
 
def readDataSet(path):    
    '''加载训练数据，并将样本标签转化为one-hot向量'''
    fileList = listdir(path)    #获取文件夹下的所有文件 
    numFiles = len(fileList)    #统计需要读取的文件的数目
    dataSet = np.zeros([numFiles,1024],int) #用于存放所有的数字文件
    hwLabels = np.zeros([numFiles,10])      #用于存放对应的one-hot标签
    for i in range(numFiles):   #遍历所有的文件
        filePath = fileList[i]  #获取文件名称/路径      
        digit = int(filePath.split('_')[0])  #通过文件名获取标签，注意类型转换      
        hwLabels[i][digit] = 1.0        #将对应的one-hot标签置1
        dataSet[i] = img2vector(path +'/'+filePath) #读取文件内容   
    return dataSet,hwLabels
 
#读取训练集
train_dataSet, train_hwLabels = readDataSet('digits/trainingDigits')
 
'''
构建神经网络：设置网络的隐藏层数、各隐藏层神经元个数、激活函数、学习率、优化方法、最大迭代次数。

设置含100个神经元的隐藏层,hidden_layer_sizes 存放的是一个元组，表示第i层隐藏层里神经元的个数
使用logistic激活函数和adam优化方法，并令初始学习率为0.0001，
'''
clf = MLPClassifier(hidden_layer_sizes=(100,),
                    activation='logistic', solver='adam',
                    learning_rate_init = 0.0001, max_iter=2000)
print(clf)
'''
fit函数能够根据训练集及对应标签集自动设置多层感知机的输入与输出层的神经元个数
例如train_dataSet为n*1024的矩阵，train_hwLabels为n*10的矩阵，则fit函数将MLP的输入层神经元个数设为1024，输出层神经元个数为10
'''

clf.fit(train_dataSet,train_hwLabels)
 
#读取测试集
dataSet,hwLabels = readDataSet('digits/testDigits')
res = clf.predict(dataSet)   #对测试集进行预测
error_num = 0                #统计预测错误的数目
num = len(dataSet)           #测试集的数目
for i in range(num):         #遍历预测结果
    #比较长度为10的数组，返回包含01的数组，0为不同，1为相同
    #若预测结果与真实结果相同，则10个数字全为1，否则不全为1
    if np.sum(res[i] == hwLabels[i]) < 10: 
        error_num += 1                     
print("Total num:",num," Wrong num:", \
      error_num,"  WrongRate:",error_num / float(num))
```

输出：

```python
MLPClassifier(activation='logistic', alpha=0.0001, batch_size='auto',
       beta_1=0.9, beta_2=0.999, early_stopping=False, epsilon=1e-08,
       hidden_layer_sizes=(100,), learning_rate='constant',
       learning_rate_init=0.0001, max_iter=2000, momentum=0.9,
       nesterovs_momentum=True, power_t=0.5, random_state=None,
       shuffle=True, solver='adam', tol=0.0001, validation_fraction=0.1,
       verbose=False, warm_start=False)
Total num: 946  Wrong num: 42   WrongRate: 0.04439746300211417
```

效果分析：

**->隐藏层神经元个数影响：**

运行隐藏层神经元个数为50、 100、 200的多层感知机，对比实验效果：

![这里写图片描述](http://img.blog.csdn.net/20170726201832596?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**随着隐藏层神经元个数的增加，MLP的正确率持上升趋势；**

大量的隐藏层神经元带来的计算负担与对结果的提升并不对等，因此，如何选取合适的隐藏神经元个数是一个值得探讨的问题。

**->迭代次数影响分析:**

我们设隐藏层神经元个数为100，初始学习率为0.0001，最大迭代次数分别为500、1000、 1500、 2000, 结果如下：

![这里写图片描述](http://img.blog.csdn.net/20170726202001981?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**过小的迭代次数可能使得MLP早停，造成较低的正确率。**

当最大迭代次数>1000时，正确率基本保持不变，这说明MLP在第1000迭代时已**收敛**，剩余的迭代次数不再进行。

一般设置较大的最大迭代次数来保证多层感知机能够收敛，达到较高的正确率。

**->学习率影响分析：**

改用随机梯度下降优化算法即将MLPclassifer的参数（ solver=‘sgd’, ），设隐藏层神经元个数为100，最大迭代次数为2000，学习率分别为：0.1、 0.01、 0.001、 0.0001，结果如下：

![这里写图片描述](http://img.blog.csdn.net/20170726202126222?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

结论：较小的学习率带来了更低的正确率，这是因为较小学习率无法在2000次迭代内
完成收敛，而步长较大的学习率使得MLP在2000次迭代内快速收敛到最优解。因此，较小
的学习率一般要配备较大的迭代次数以保证其收敛。

### 6.3.利用KNN实现手写识别的任务

实现代码：

```python
import numpy as np     #导入numpy工具包
from os import listdir #使用listdir模块，用于访问本地文件
from sklearn import neighbors
 
def img2vector(fileName):    
    retMat = np.zeros([1024],int) #定义返回的矩阵，大小为1*1024
    fr = open(fileName)           #打开包含32*32大小的数字文件 
    lines = fr.readlines()        #读取文件的所有行
    for i in range(32):           #遍历文件所有行
        for j in range(32):       #并将01数字存放在retMat中     
            retMat[i*32+j] = lines[i][j]    
    return retMat
 
def readDataSet(path):    
    fileList = listdir(path)    #获取文件夹下的所有文件 
    numFiles = len(fileList)    #统计需要读取的文件的数目
    dataSet = np.zeros([numFiles,1024],int)    #用于存放所有的数字文件
    hwLabels = np.zeros([numFiles])#用于存放对应的标签(与神经网络的不同)
    for i in range(numFiles):      #遍历所有的文件
        filePath = fileList[i]     #获取文件名称/路径   
        digit = int(filePath.split('_')[0])   #通过文件名获取标签     
        hwLabels[i] = digit        #直接存放数字，并非one-hot向量
        dataSet[i] = img2vector(path +'/'+filePath)    #读取文件内容 
    return dataSet,hwLabels
 
#read dataSet
train_dataSet, train_hwLabels = readDataSet('digits/trainingDigits')
knn = neighbors.KNeighborsClassifier(algorithm='kd_tree', n_neighbors=3)
knn.fit(train_dataSet, train_hwLabels)
 
#read  testing dataSet
dataSet,hwLabels = readDataSet('digits/testDigits')
 
res = knn.predict(dataSet)  #对测试集进行预测
error_num = np.sum(res != hwLabels) #统计分类错误的数目
num = len(dataSet)          #测试集的数目
print("Total num:",num," Wrong num:", \
      error_num,"  WrongRate:",error_num / float(num))
```

输出：

```
Total num: 946  Wrong num: 12   WrongRate: 0.0126849894292
```

实现效果：

**邻居数量K影响分析：**

设置K为1、 3、 5、 7的KNN分类器，对比他们的实验效果

![这里写图片描述](http://img.blog.csdn.net/20170726204054509?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

K=3时正确率最高，当K>3时正确率开始下降，这是由于当样本为稀疏数据集时（本实例只有946个样本），其第k个邻居点可能与测试点距离较远，因此投出了错误的一票进而影响了最终预测结果。

**对比实验：**

KNN分类器vs.多层感知机:

取在上面对不同的隐藏层神经元个数、最大迭代次数、学习率进行的各个对比实验中准确率最高（H）与最差（L）的MLP分类器来进行对比，其各个MLP的参数设置如下：

![这里写图片描述](http://img.blog.csdn.net/20170726204517803?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

将效果最好的KNN分类器（K=3）和效果最差的KNN分类器（K=7）与各个MLP分类器作对比如下：

![这里写图片描述](http://img.blog.csdn.net/20170726204551178?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

结论：

 KNN的准确率远高于MLP分类器，这是由于MLP在小数据集上容易过拟合的原因。
 
MLP对于参数的调整比较敏感，若参数设置不合理，容易得到较差的分类效果，因此参数的设置对于MLP至关重要