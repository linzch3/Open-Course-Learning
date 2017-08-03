<!-- TOC -->

- [class 3](#class-3)
    - [1.根据输出空间划分ML中的学习类型](#1根据输出空间划分ml中的学习类型)
    - [2.根据标签数据类型划分ML中的学习类型](#2根据标签数据类型划分ml中的学习类型)
    - [3.根据protocol类型划分ML中的学习类型](#3根据protocol类型划分ml中的学习类型)
    - [4.根据输入空间划分ML中的学习类型](#4根据输入空间划分ml中的学习类型)

<!-- /TOC -->
# class 3

## 1.根据输出空间划分ML中的学习类型

对应类型以及例子：

- **二元分类**：Y={-1，+1}
- 多元分类：Y={1，2，3，...,K}
- **回归**：<img src="https://latex.codecogs.com/gif.latex?Y=\mathbb{R}" title="Y=\mathbb{R}" />
- 结构化学习(structured learning)：Y=structure，e.g.，对句子中的词语进行词性标注（难度挺大的）
- 还有很多其他的类型...

注：

- 回归的结果是连续的，因此其输出空间为<img src="https://latex.codecogs.com/gif.latex?\mathbb{R}^n" title="\mathbb{R}^n" />，其可被看成是连续的多元分类

- 结构化学习可这样理解：输出空间是具有结构上的关系的，机器就是要学习这些结构上的关系

## 2.根据标签数据类型划分ML中的学习类型

对应类型以及定义：

- **监督学习**：对于数据集中的所有xn，都有yn与之对应
- 无监督学习：对于数据集中的所有xn，都没有yn与之对应，e.g.,聚类、异常点检测。
- 半监督学习：对于数据集中的所有xn，只有一些yn与之对应
- 强化学习： 通过给出行为的“评分”高低隐式地给出yn
- 还有很多其他的类型...

## 3.根据protocol类型划分ML中的学习类型

这里的protocol暂时找不到较为贴切的翻译，其大概是指**机器与人提供数据的交互方式。**

对应的类型以及定义：

- batch: 批量地将数据输入
- online: 逐个地(sequentially)将数据输入
- active: 主动地(有策略性、目的性地)“询问”特定数据的输出，可看成机器主动“问问题” 
- 还有很多其他的类型...

## 4.根据输入空间划分ML中的学习类型

对应的类型以及定义：

- concrete: 具体地找到数据中的某些特征作为输入，通常具有复杂的物理含义（但是相关性越强）
- raw: 直接利用数据的原始信息作为输入，比如直接用图像的像素作为输入而不是根据像素点的分布再次抽取去特征作为输入，通常具有较为简单的物理含义
- abstract: 没有或只有一点物理含义，通常实现起来困难较大，比如一个<img src="https://latex.codecogs.com/gif.latex?Y=\mathbb{R}" title="Y=\mathbb{R}" />并且<img src="https://latex.codecogs.com/gif.latex?X&space;\in&space;\mathbb{N}\times&space;\mathbb{N}" title="X \in \mathbb{N}\times \mathbb{N}" /> (对应(user id,user name))的回归问题，这里就需要人或机器从这些抽象的系统数据去提取出更为具体的特征进行学习的工作，因此难度会比较大。