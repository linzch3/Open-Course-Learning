<!-- TOC -->

- [leture 1 - Intro](#leture-1---intro)
- [leture 2 - Foundation of Mathematics](#leture-2---foundation-of-mathematics)
    - [概率](#概率)

<!-- /TOC -->
# leture 1 - Intro

leture 1中基本都是介绍性的东西，了解即可，相关重要的知识点后续也会讲到，故这里不再列举。

# leture 2 - Foundation of Mathematics

## 概率

乘法法则：

<img src="https://latex.codecogs.com/gif.latex?P(A,B)=P(A)P(B|A)=P(B)P(A|B)" title="P(A,B)=P(A)P(B|A)=P(B)P(A|B)" />

加法法则：

<img src="https://latex.codecogs.com/gif.latex?P(A)=P(A,B)&plus;P(A,B^c)=\sum_{B}P(A,B)=\sum_{i=1}^nP(A,B_i)=\sum_{i=1}^nP(A|B_i)P(B_i)" title="P(A)=P(A,B)+P(A,B^c)=\sum_{B}P(A,B)=\sum_{i=1}^nP(A,B_i)=\sum_{i=1}^nP(A|B_i)P(B_i)" />

测试题1

求<img src="https://latex.codecogs.com/gif.latex?\inline&space;\sum_GP(G|X=boy)" title="\sum_GP(G|X=boy)" />的值：

A.1

B.P(X=boy)

C.None of the above

正确答案：A。

测试题2

假设有一盒骰子，里面有4面的（点数为1、2、3、4），6面的、8面的、12面的、20面的均匀骰子各1个。如果我随机从盒子中选一个骰子，投掷它得到了点数5。那么我选中的骰子为4面、6面、8面、12面、20面的概率各是多少？（要求用上面提出的两条概率公式求解）

正确答案：

用变量<img src="https://latex.codecogs.com/gif.latex?\inline&space;s_i&space;\in&space;S=\{4,6,8,12,20\}" title="s_i \in S=\{4,6,8,12,20\}" />代表骰子，用<img src="https://latex.codecogs.com/gif.latex?\inline&space;d_i&space;\in&space;D=\{1,2,...,20\}" title="d_i \in D=\{1,2,...,20\}" />代表点数。

即求：<img src="https://latex.codecogs.com/gif.latex?\inline&space;P(s=s_i|d=5),s_i&space;\in&space;S=\{4,6,8,12,20\}" title="P(s=s_i|d=5),s_i \in S=\{4,6,8,12,20\}" />

则有：

<img src="https://latex.codecogs.com/gif.latex?%5C%5C%20P%28s%3Ds_i%7Cd%3D5%29%20%5C%5C%20%5C%5C%20%3D%5Cfrac%7BP%28d%3D5%7Cs%3Ds_i%29*P%28s%3Ds_i%29%7D%7BP%28d%3D5%29%7D%20%5C%5C%20%5C%5C%20%3D%5Cfrac%7BP%28d%3D5%7Cs%3Ds_i%29*P%28s%3Ds_i%29%7D%7B%5Csum_%7Bj%3D1%7D%5E%7B5%7DP%28d%3D5%7Cs%3Ds_j%29*P%28s%3Ds_j%29%7D%2Cs_i%20%5Cin%20S%3D%5C%7B4%2C6%2C8%2C12%2C20%5C%7D%20%5C%5C%20%5C%5C%20%5C%5C%20%5CRightarrow%20P%28s%3D4%7Cd%3D5%29%3D%5Cfrac%7B0*1/5%7D%7B%280%20&plus;%201/6%20&plus;%201/8%20&plus;%201/12%20&plus;%201/20%29*1/5%7D%3D0%20%5C%5C%20%5C%5C%20%5C%5C%20%5CRightarrow%20P%28s%3D6%7Cd%3D5%29%3D%5Cfrac%7B1/6*1/5%7D%7B%280%20&plus;%201/6%20&plus;%201/8%20&plus;%201/12%20&plus;%201/20%29*1/5%7D%3D0.3921568627450981%20%5C%5C%20%5C%5C%20%5C%5C%20%5CRightarrow%20P%28s%3D8%7Cd%3D5%29%3D%5Cfrac%7B1/8*1/5%7D%7B%280%20&plus;%201/6%20&plus;%201/8%20&plus;%201/12%20&plus;%201/20%29*1/5%7D%3D0.2941176470588236%20%5C%5C%20%5C%5C%20%5C%5C%20%5CRightarrow%20P%28s%3D12%7Cd%3D5%29%3D%5Cfrac%7B1/12*1/5%7D%7B%280%20&plus;%201/6%20&plus;%201/8%20&plus;%201/12%20&plus;%201/20%29*1/5%7D%3D0.19607843137254904%20%5C%5C%20%5C%5C%20%5C%5C%20%5CRightarrow%20P%28s%3D20%7Cd%3D5%29%3D%5Cfrac%7B1/20*1/5%7D%7B%280%20&plus;%201/6%20&plus;%201/8%20&plus;%201/12%20&plus;%201/20%29*1/5%7D%3D0.11764705882352944" />
 










