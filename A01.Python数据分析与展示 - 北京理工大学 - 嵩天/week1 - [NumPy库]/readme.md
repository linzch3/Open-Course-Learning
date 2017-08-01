
<!-- TOC -->

- [单元一：NumPy库入门](#单元一numpy库入门)
    - [1.1 数据的维度](#11-数据的维度)
    - [1.2 NumPy的数组对象：ndarray](#12-numpy的数组对象ndarray)
    - [1.3 ndarray数组的元素类型](#13-ndarray数组的元素类型)
    - [1.4 ndarray数组的创建](#14-ndarray数组的创建)
    - [1.5 ndarray数组的变换](#15-ndarray数组的变换)
    - [1.6 ndarray数组的操作](#16-ndarray数组的操作)
    - [1.7 ndarray数组的运算](#17-ndarray数组的运算)
    - [单元小结](#单元小结)
- [单元二：Numpy数据存取与函数](#单元二numpy数据存取与函数)
    - [2.1 数据的CSV存取](#21-数据的csv存取)
    - [2.2 多维数据的存取](#22-多维数据的存取)
    - [2.3 NumPy的随机数函数](#23-numpy的随机数函数)
    - [2.4 NumPy的统计函数](#24-numpy的统计函数)
    - [2.5 NumPy的梯度函数](#25-numpy的梯度函数)
    - [单元小结](#单元小结-1)
- [单元三：实例1-图像的手绘效果](#单元三实例1-图像的手绘效果)
    - [3.1 图像的数据表示](#31-图像的数据表示)
    - [3.2图像的变换](#32图像的变换)
    - [3.3 -图像的手绘效果实例分析](#33--图像的手绘效果实例分析)

<!-- /TOC -->

## 单元一：NumPy库入门
### 1.1 数据的维度
**维度：一组数据的组织形式**

![这里写图片描述](http://img.blog.csdn.net/20170430101935779?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**一维数据**
一维数据由**对等关系**的有序或无序数据构成，采用**线性**方式组织，对**应列表、数组和集合**等概念
如：3.1413, 3.1398, 3.1404, 3.1401, 3.1349, 3.1376。

其中，关于列表和数组的区别是：

![这里写图片描述](http://img.blog.csdn.net/20170430102154173?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**二维数据**
二维数据由多个一维数据构成，是一维数据的组合形式，表格是典型的二维数据，其中，表头是二维数据的一部分。

![这里写图片描述](http://img.blog.csdn.net/20170430102411967?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
**多维数据**
多维数据由一维或二维数据在新维度上扩展形成

![这里写图片描述](http://img.blog.csdn.net/20170430102419910?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**高维数据**
高维数据仅利用**最基本的二元关系**展示数据间的复杂结构

![这里写图片描述](http://img.blog.csdn.net/20170430102506905?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**数据维度的Python表示**

![这里写图片描述](http://img.blog.csdn.net/20170430102629381?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430102639725?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.2 NumPy的数组对象：ndarray

NumPy是一个开源的Python科学计算基础库，包含：

• 一个强大的N维数组对象 ndarray

• 广播功能函数

• 整合C/C++/Fortran代码的工具

• 线性代数、傅里叶变换、随机数生成等功能

NumPy是SciPy、 Pandas等数据处理或科学计算库的基础

我们一般使用 `import numpy as np`来引用numpy库

**ndarray 意为：N维数组对象**

这里自然就有一个疑问：Python已有列表类型，为什么需要一个数组对象(类型)？看下面的例子：

![这里写图片描述](http://img.blog.csdn.net/20170430103138892?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

那么，引入ndarray 的好处就是：

• 数组对象可以去掉元素间运算所需的循环，使一维向量更像单个数据

• 设置专门的数组对象，经过优化，可以提升这类应用的运算速度

注：科学计算中，一个维度所有数据的类型往往相同

• 数组对象采用相同的数据类型，有助于节省运算和存储空间

---
ndarray由两部分构成：

• 实际的数据

• 描述这些数据的元数据（数据维度、数据类型等）

ndarray数组一般要求所有元素**类型相同（同质）**，数组下标从0开始

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430103624160?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**ndarray对象的属性**

![这里写图片描述](http://img.blog.csdn.net/20170430103756880?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430103836944?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.3 ndarray数组的元素类型

![这里写图片描述](http://img.blog.csdn.net/20170430104333810?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430104341170?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430104406124?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**疑问：ndarray为什么要支持这么多种元素类型？**

对比：Python语法仅支持整数、浮点数和复数3种类型

• 科学计算涉及数据较多，对存储和性能都有较高要求

• 对元素类型精细定义，有助于NumPy合理使用存储空间并优化性能

• 对元素类型精细定义，有助于程序员对程序规模有合理评估

**非同质的ndarray对象**

![这里写图片描述](http://img.blog.csdn.net/20170430104747192?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.4 ndarray数组的创建

**ndarray数组的创建方法**

• 从Python中的列表、元组等类型创建ndarray数组

• 使用NumPy中函数创建ndarray数组，如：arange, ones, zeros等

• 从字节流（raw bytes）中创建ndarray数组

• 从文件中读取特定格式，创建ndarray数组

---
（1） 从Python中的列表、元组等类型创建ndarray数组

使用方法：

![这里写图片描述](http://img.blog.csdn.net/20170430105110712?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430105200558?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

（2）使用NumPy中函数创建ndarray数组，如：arange, ones, zeros等

函数用法1：

![这里写图片描述](http://img.blog.csdn.net/20170430105237542?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注意：shape这个参数应是 **元组** 类型

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430105346324?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

函数用法2：

![这里写图片描述](http://img.blog.csdn.net/20170430112820342?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

（3）使用NumPy中其他函数创建ndarray数组
函数用法：

![这里写图片描述](http://img.blog.csdn.net/20170430112920656?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430112938938?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.5 ndarray数组的变换

对于创建后的ndarray数组，可以对其进行**维度变换和元素类型变换**

**维度变换：**

![这里写图片描述](http://img.blog.csdn.net/20170430113144798?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
**注意：这里有些函数调用后会修改原数组，有些则不会。**

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430113214957?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例2：

![这里写图片描述](http://img.blog.csdn.net/20170430113343413?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**元素类型变换：**

![这里写图片描述](http://img.blog.csdn.net/20170430113445643?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**ndarray数组向列表的转换**

![这里写图片描述](http://img.blog.csdn.net/20170430113535478?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.6 ndarray数组的操作

**数组的索引和切片**

索引：获取数组中**特定位置元素**的过程

切片：获取数组**元素子集**的过程

**一维数组的索引和切片**：与Python的列表类似

![这里写图片描述](http://img.blog.csdn.net/20170430113833363?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**多维数组的索引**：

![这里写图片描述](http://img.blog.csdn.net/20170430114136130?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**多维数组的切片**

![这里写图片描述](http://img.blog.csdn.net/20170430114540273?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1.7 ndarray数组的运算

**数组与标量之间的运算作用于数组的每一个元素**

![这里写图片描述](http://img.blog.csdn.net/20170430114656884?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**NumPy一元函数：对ndarray中的数据执行元素级运算的函数**

![这里写图片描述](http://img.blog.csdn.net/20170430114821979?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430114829822?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430114916119?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**NumPy二元函数**

![这里写图片描述](http://img.blog.csdn.net/20170430114958934?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430115050748?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 单元小结

![这里写图片描述](http://img.blog.csdn.net/20170430120037000?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 单元二：Numpy数据存取与函数

### 2.1 数据的CSV存取

CSV (Comma‐Separated Value, 逗号分隔值),CSV是一种常见的文件格式，用来存储批量数据

![这里写图片描述](http://img.blog.csdn.net/20170430121158024?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**savetxt: 存CSV文件**

![这里写图片描述](http://img.blog.csdn.net/20170430121657259?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例1：

![这里写图片描述](http://img.blog.csdn.net/20170430121738853?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例2：

![这里写图片描述](http://img.blog.csdn.net/20170430121821331?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**loadtxt: 读CSV文件**

![这里写图片描述](http://img.blog.csdn.net/20170430121855853?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
**注：dtype一般默认为 浮点 类型**

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430123105364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**CSV文件的局限性**

CSV只能有效存储**一维和二维**数组

np.savetxt() np.loadtxt()只能有效存取一维和二维数组

### 2.2 多维数据的存取

**使用tofile函数保存多维数据**

![这里写图片描述](http://img.blog.csdn.net/20170430123356509?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例1：

![这里写图片描述](http://img.blog.csdn.net/20170430123422729?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例2：

![这里写图片描述](http://img.blog.csdn.net/20170430123549137?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**使用fromfile函数读取多维数据**

![这里写图片描述](http://img.blog.csdn.net/20170430123707827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430123746737?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

注意：该方法需要读取时知道存入文件时**数组的维度和元素类型**，a.tofile()和np.fromfile()需要配合使用，可以通过元数据文件来存储额外信息

**NumPy便捷文件的读取**

![这里写图片描述](http://img.blog.csdn.net/20170430124200005?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430124239599?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 2.3 NumPy的随机数函数

NumPy的随机函数子库：np.random.* 包含有关随机数的函数

![这里写图片描述](http://img.blog.csdn.net/20170430124452554?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430124517304?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430124538461?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---
![这里写图片描述](http://img.blog.csdn.net/20170430125419558?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**注意函数是否改变原数组**

![这里写图片描述](http://img.blog.csdn.net/20170430124758243?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430124956228?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---
![这里写图片描述](http://img.blog.csdn.net/20170430125459386?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430125520446?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 2.4 NumPy的统计函数

![这里写图片描述](http://img.blog.csdn.net/20170430125632088?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430125735341?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---
![这里写图片描述](http://img.blog.csdn.net/20170430125814607?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430125843576?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**注：argmax/argmin经常与unravel_index结合使用---->得到多维数组中最大/小数据的多维数组下标**

### 2.5 NumPy的梯度函数

![这里写图片描述](http://img.blog.csdn.net/20170430130050281?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430130114797?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430130130454?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 单元小结

![这里写图片描述](http://img.blog.csdn.net/20170430130248282?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430130255423?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## 单元三：实例1-图像的手绘效果

### 3.1 图像的数据表示
图像一般使用RGB色彩模式，即每个像素点的颜色由红(R)、绿(G)、蓝(B)组成。

RGB三个颜色通道的变化和叠加得到各种颜色，其中

• R 红色，取值范围，0‐255

• G 绿色，取值范围，0‐255

• B 蓝色，取值范围，0‐255

RGB形成的颜色包括了人类视力所能感知的所有颜色

**PIL库**

![这里写图片描述](http://img.blog.csdn.net/20170430130632049?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430130702862?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430130802378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**注：图像是一个三维数组，维度分别是高度、宽度和像素RGB值**

### 3.2图像的变换

**变换原理：读入图像后，获得像素RGB值，修改后保存为新的文件**
![这里写图片描述](http://img.blog.csdn.net/20170430130945988?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430131116442?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430131123488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430131129704?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 3.3 -图像的手绘效果实例分析

![这里写图片描述](http://img.blog.csdn.net/20170430131237052?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

效果分析：

![这里写图片描述](http://img.blog.csdn.net/20170430131302599?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

代码：

```python
#HandDrawPic.py
# -*- coding: utf-8 -*-
from PIL import Image
import numpy as np

a = np.asarray(Image.open('./beijing.jpg').convert('L')).astype('float')

depth = 10. 						# (0-100)
grad = np.gradient(a)				#取图像灰度的梯度值
grad_x, grad_y = grad 				#分别取横纵图像梯度值
grad_x = grad_x*depth/100.
grad_y = grad_y*depth/100.
A = np.sqrt(grad_x**2 + grad_y**2 + 1.)#这里相当于 grad_z=1.0
uni_x = grad_x/A
uni_y = grad_y/A
uni_z = 1./A

vec_el = np.pi/2.2 					# 光源的俯视角度（根据图片假设的），弧度值
vec_az = np.pi/4. 					# 光源的方位角度（根据图片假设的），弧度值
dx = np.cos(vec_el)*np.cos(vec_az) 	#光源对x 轴 单位长度 的影响
dy = np.cos(vec_el)*np.sin(vec_az) 	#光源对y 轴 单位长度 的影响
dz = np.sin(vec_el) 				#光源对z 轴 单位长度 的影响

b = 255*(dx*uni_x + dy*uni_y + dz*uni_z) 	#光源归一化
b = b.clip(0,255)#为避免数据越界，将生成的灰度值裁剪至0‐255区间

im = Image.fromarray(b.astype('uint8')) 	#重构图像
im.save('./beijingHD.jpg')

```
代码分析：
原理：利用像素之间的梯度值和虚拟深度值对图像进行重构，根据**灰度变化**来模拟人类视觉的远近程度。

![这里写图片描述](http://img.blog.csdn.net/20170430133616300?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430133722018?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430133749912?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430133904649?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)