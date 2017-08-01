<!-- TOC -->

- [**class 1**](#class-1)
    - [**机器学习的组成部分**](#机器学习的组成部分)
    - [机器学习与其他领域的关系](#机器学习与其他领域的关系)
        - [ML与data mining（数据挖掘）的关系](#ml与data-mining数据挖掘的关系)
        - [ML与artificial intelligence（人工智能）的关系](#ml与artificial-intelligence人工智能的关系)
        - [ML与statistics (统计)的关系](#ml与statistics-统计的关系)

<!-- /TOC -->
## **class 1**

### **机器学习的组成部分**

机器学习可用如下图示理解，首先对于给定的数据集D（里面包含我们已有的训练数据），我们需要得到的就是**隐藏于这些数据的映射关系**，也就是未知的**目标函数f**，于是我们便通过某种方式得到包含可能的映射关系的hypothesis set H，然后用再根据我们的learning algoprithm A来 **“筛选”出最接近f效果的** final hypothesis g。

<img src='./images/definition.jpg'></img>

因此，可总结出，机器学习的定义为：

**use data to compute hypothesis g that approximates target ff.**

### 机器学习与其他领域的关系

#### ML与data mining（数据挖掘）的关系

data mining: use (huge) data to find property that is interesting

**两者基本分不开。**


#### ML与artificial intelligence（人工智能）的关系

artificial intelligence：compute something that shows intelligent behavior

**ML是实现artificial intelligence的一种方式。**

#### ML与statistics (统计)的关系

statistics：use data to make inference about an unknown process

**statistics是实现ML的一种方式。**
