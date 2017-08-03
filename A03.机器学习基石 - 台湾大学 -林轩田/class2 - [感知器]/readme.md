<!-- TOC -->

- [class 2](#class-2)
    - [1.感知器(perceptron)模型简介](#1感知器perceptron模型简介)
    - [2.PLA: perceptron learning algorithm](#2pla-perceptron-learning-algorithm)
    - [3.PLA什么时候会停下来](#3pla什么时候会停下来)
    - [4.应用PLA在非线性可分的数据集上](#4应用pla在非线性可分的数据集上)

<!-- /TOC -->
# class 2

## 1.感知器(perceptron)模型简介

对于给定的数据x=(x1,x2,x3,...,xd),其中xi是x在对应的第i个特征下的数据。

则perceptron hypothesis就定义为：

<img src="https://latex.codecogs.com/gif.latex?h(x)=sign((\sum_{i=1}^{d}w_{i}x_{i})&space;-&space;threshold)" title="h(x)=sign((\sum_{i=1}^{d}w_{i}x_{i}) - threshold)" />

其中<img src="https://latex.codecogs.com/gif.latex?\inline&space;h(x)&space;\in&space;\{-1,&space;&plus;1\}" title="h(x) \in \{-1, +1\}" />（忽略0），也即是h(x)仅有两个取值，因此perceptron也被称为二分类器。

公式中的wi是给不同特征赋予的权重，threshold是控制h(x)划分边界的阈值。

上面的公式实际上还可以化简为更简洁的形式：

<img src='./images/formula_reduction.jpg'/>

这里为x引入第0维度的数据x0=+1，引入对应的权重w0=threshold。从而将式子表示为对两个向量的点乘的结果进行取符号操作。

实际上h(x)可表示为空间中的一个超平面，在其一边分类为+1，在其另外一边分类为-1。以下图为例，在R2上的h(x)就是一边直线，其将二维平面划分为两个部分。

<img src='./images/perceptrons_in_R2.jpg'/>

看到这里，可以说perceptrons是一种**线性的**二分类器。

## 2.PLA: perceptron learning algorithm

从1中知道了perceptrons这种hypothesis set了，那么我们该如何确定一个最佳的perceptron呢？也即是说**如何确定最佳的w**?（threshold这个参数应是人为先确定好的）

回想上一节的内容，我们需要实现的就是**让g几乎接近f的效果**，也即是：

**对于给定的数据集D，我们要尽量实现对于每个xn，都有g(xn)=f(xn)=yn。**

而空间中的超平面是有无数多个的，也即是说：我们不可能通过遍历来找到最佳的w。因此，结合数据集，可以通过下面的idea来找到最佳的w：

首先从一个随意设置的g0（或者说w0）开始，不断地用D中的数据集来矫正g0(w0),直到所有数据都被划分到对应的一边上。（这个说法有点不严谨，仅供理解）

该算法可表示如下：

<img src='./images/PLA1.jpg'/>

其中，第一步中判断分类错误的情况可分为如下两种：

-> +1 类被划分为 -1类：也即是超平面法向量与x的夹角为钝角（本应是锐角）

矫正方法：用w+x（向量加法）替换w

<img src='./images/+1to-1.jpg'/>

-> -1 类被划分为 +1类：也即是超平面法向量与x的夹角为锐角（本应是钝角）

矫正方法：用w-x（向量加法）替换w

<img src='./images/-1to+1.jpg'/>

观察以上的这两种矫正方法，发现可统一写成：用w+y*x替换w。

该算法运行直到没有分类错误时才结束，其中常见的得到next mistake的方法可以是随机挑选，也可以是循环遍历的。

下面给出算法运行的一个实例，经过了10次更新后所有点的分类都没有错误。

<img src='./images/example_flow.jpg'/>

注意：update 1理解起来可能有点奇怪，这是因为初始化时w0都为0，因此xn(1)这个点分类错误，更新w1为w0+y*xn(1)，因此图片中看起来w(t+1)会和xn(1)在同个方向上。

## 3.PLA什么时候会停下来

上面讲到，当分类没有错误时，PLA才会停止算法运行。因此，PLA要能停下来的条件就是：**数据集是可用一个超平面划分为两个部分的，即是线性可分的。**

<img src='./images/separability.jpg'/>

PLA会停下来 等价于 在线性可分的条件下，PLA能找到一条可以将数据集无错地分成两类的超平面，也即是符合target function f 标准"的w，我们将其命名为wf，则有：

<img src="https://latex.codecogs.com/gif.latex?\inline&space;y_{n}&space;=&space;sign(w_{f}^{T}x_{n})" title="y_{n} = sign(w_{f}^{T}x_{n})" />

那么对于任意的xn，有：

<img src='./images/inference1.jpg'/>

注：这里用wf与w(t+1)的内积的大小来判断两者的接近程度。

可以看到wf与w(t+1)的内积随着t的增长会越来越大，但若要证明两者是越来越接近的，就需要证明wt（或者w(t+1)）本身模长的增长d的影响是否可以忽略。

由于算法中是遇到分类错误点才进行更新的，因此对分类错误点，有：

<img src='./images/inference2.jpg'/>

则有：

<img src='./images/inference3.jpg'/>

因此由于错误点的存在，wt的增长是缓慢的。

结合上面的推论，可得如下结论：

从w0=0开始,经过T次的错误矫正，有：

<img src='./images/inference4.jpg'/>

因此T越大，cos<wf,wt>越大，由于cos<wf,wt>有上界1，因此wt最终会解决wf，因此PLA会停止。

----
思考题：假设：<img src="https://latex.codecogs.com/gif.latex?\inline&space;R^{2}=&space;\max&space;\limits_{n}&space;{\left&space;\|&space;x_{n}&space;\right&space;\|}^{2}" title="R^{2}= \max \limits_{n} {\left \| x_{n} \right \|}^{2}" /> 并且<img src="https://latex.codecogs.com/gif.latex?\inline&space;\rho&space;=&space;\max&space;\limits_{n}&space;y_{n}\frac{w_{f}}{\left&space;\|&space;w_{f}&space;\right&space;\|}x_{n}" title="\rho = \max \limits_{n} y_{n}\frac{w_{f}}{\left \| w_{f} \right \|}x_{n}" />，求T的上界。

解：

首先，根据上面的推导，可得知如下的关系式：

<img src="https://latex.codecogs.com/gif.latex?1&space;\geq&space;\frac{w_{f}^{T}w_{T}}{\left&space;\|&space;w_{f}^{T}&space;\right&space;\|\left&space;\|&space;w_{T}&space;\right&space;\|}&space;\geq&space;\sqrt{T}&space;*constant" title="1 \geq \frac{w_{f}^{T}w_{T}}{\left \| w_{f}^{T} \right \|\left \| w_{T} \right \|} \geq \sqrt{T} *constant" />

则:

<img src="https://latex.codecogs.com/gif.latex?T&space;\leq&space;\frac{1}{constant^{2}}" title="T \leq \frac{1}{constant^{2}}" />

又因：

<img src="https://latex.codecogs.com/gif.latex?w_f^T&space;w_{t&plus;1}=&space;w_f^T&space;w_t&space;&plus;&space;y_n&space;w_f^T&space;x_n&space;\geq&space;w_f^T&space;w_t&space;&plus;&space;\min_n&space;y_n&space;w_f^T&space;x_n" title="w_f^T w_{t+1}= w_f^T w_t + y_n w_f^T x_n \geq w_f^T w_t + \min_n y_n w_f^T x_n" />

根据该递推式，可得：

<img src="https://latex.codecogs.com/gif.latex?w_f^T&space;w_T&space;\geq&space;w_f^T&space;w_0&space;&plus;&space;T&space;*&space;\min_n&space;y_n&space;w_f^T&space;x_n&space;=&space;0&space;&plus;&space;T&space;*&space;\min_n&space;y_n&space;w_f^T&space;x_n" title="w_f^T w_T \geq w_f^T w_0 + T * \min_n y_n w_f^T x_n = 0 + T * \min_n y_n w_f^T x_n" />

则：

<img src="https://latex.codecogs.com/gif.latex?\frac{w_f^T&space;w_T}{\left&space;\|&space;w_f^T&space;\right&space;\|\left&space;\|&space;w_T&space;\right&space;\|}&space;\geq&space;\frac{T}{\left&space;\|&space;w_T&space;\right&space;\|}&space;*&space;\min_n&space;y_n&space;\frac{w_f^T}{\left&space;\|&space;w_f^T&space;\right&space;\|}&space;x_n&space;=&space;T&space;*&space;\frac{\rho&space;}{\left&space;\|&space;w_T&space;\right&space;\|}" title="\frac{w_f^T w_T}{\left \| w_f^T \right \|\left \| w_T \right \|} \geq \frac{T}{\left \| w_T \right \|} * \min_n y_n \frac{w_f^T}{\left \| w_f^T \right \|} x_n = T * \frac{\rho }{\left \| w_T \right \|}" />

又因：

<img src="https://latex.codecogs.com/gif.latex?\left&space;\|&space;w_{t&plus;1}&space;\right&space;\|^2&space;-&space;\max_n&space;\left&space;\|&space;x_{n}&space;\right&space;\|^2&space;\leq&space;\left&space;\|&space;w_{t}&space;\right&space;\|^2" title="\left \| w_{t+1} \right \|^2 - \max_n \left \| x_{n} \right \|^2 \leq \left \| w_{t} \right \|^2" />

则：

<img src="https://latex.codecogs.com/gif.latex?\left&space;\|&space;w_{T}&space;\right&space;\|^2&space;-&space;T&space;*&space;\max_n&space;\left&space;\|&space;x_{n}&space;\right&space;\|^2&space;\leq&space;\left&space;\|&space;w_{0}&space;\right&space;\|^2&space;=&space;0" title="\left \| w_{T} \right \|^2 - T * \max_n \left \| x_{n} \right \|^2 \leq \left \| w_{0} \right \|^2 = 0" />

即：

<img src="https://latex.codecogs.com/gif.latex?\left&space;\|&space;w_{T}&space;\right&space;\|&space;\leq&space;\sqrt{T}&space;*&space;\sqrt{&space;\max_n&space;\left&space;\|&space;x_{n}&space;\right&space;\|^2}&space;=&space;\sqrt{T}&space;*&space;R" title="\left \| w_{T} \right \| \leq \sqrt{T} * \sqrt{ \max_n \left \| x_{n} \right \|^2} = \sqrt{T} * R" />

则可得：

<img src="https://latex.codecogs.com/gif.latex?constant&space;=&space;\frac&space;{\rho}{R}" title="constant = \frac {\rho}{R}" />

即T的上界为：

<img src="https://latex.codecogs.com/gif.latex?upperBound(T)=&space;\frac&space;{R^2}{\rho^2}" title="upperBound(T)= \frac {R^2}{\rho^2}" />

----

## 4.应用PLA在非线性可分的数据集上

对于非线性可分的数据集，我们无法找到一个可完全将数据集完全分离的超平面，因此只能推而求其次，找到一个能最接近f的g,也即是求下列优化问题：

<img src="https://latex.codecogs.com/gif.latex?w_g&space;\leftarrow&space;\operatorname*{argmin}_w&space;\sum_{n=1}^{N}[&space;y_n&space;\neq&space;sign(w^T&space;x_n)]" title="w_g \leftarrow \operatorname*{argmin}_w \sum_{n=1}^{N}[ y_n \neq sign(w^T x_n)]" />

这个问题已被证明为NP-hard的。


这里给出一种解法——pocket algorithm：

<img src='./images/pocket_algorithm.jpg'/>
