<!-- TOC -->

- [week 5](#week-5)
    - [5-1 Repeated Games](#5-1-repeated-games)
    - [5-2 Infinitely Repeated Games: Utility](#5-2-infinitely-repeated-games-utility)
    - [5-3 Stochastic Games](#5-3-stochastic-games)
    - [5-4 Learning in Repeated Games](#5-4-learning-in-repeated-games)
        - [Fictitious Play](#fictitious-play)
        - [No-regret Learning](#no-regret-learning)

<!-- /TOC -->
# week 5

## 5-1 Repeated Games

一些例子的介绍。Repeated Games help players making better decision.

## 5-2 Infinitely Repeated Games: Utility

当players play无穷多个重复的Games时，我们无法写出它的Extensive Form（如果要写那就是一颗无穷深度的树，永远找不到树的叶节点），那么我们就需要定义一种新的Utility判断方式：

<div align="center"><img src='./images/def1.jpg'/></div>

该计算方式是 无穷多次game的payoff的求和平均值(average reward)。

但是这样的计算方式就有一个缺点：假设刚开始的前1000次game的payoff为-1000000，而在后续的game的payoff均为1，那么求出来的average reward就是1了。在数学上没问题，但是在通常实际上，我们更希望刚开始的时候的payoff不要太低，也就是初期的payoff的重要性要大些。为了解决这个问题，引入如下定义：

<div align="center"><img src='./images/df2.jpg'/></div>

**Two equivalent interpretations of the discount factor**:
- 1. the agent **cares more about** his well-being in the **near term** than in the long term
- 2. the agent cares about the future just as much as the present, but with **probability 1 − β** the game will end in any given round.(或者说在每一轮新的game都以概率β接着进行)

## 5-3 Stochastic Games

A stochastic game is a generalization of repeated games：
- agents **repeatedly** play games from **a set of normal-form games**
- the game played at any iteration depends on the previous game played and (also depends) on the actions taken by all agents in that game

其和repeated game的区别可参考下图：

<div align="center"><img src='./images/sto-diff-rep.jpg'/></div>

Stochastic Games会随机地从当前的game跳到新的game中去。

正式定义如下：

<div align="center"><img src='./images/def3.jpg'/></div>

Notes: **MDP(Markov Decision Process)  is a single-agent stochastic game.**

Can do analysis as with repeated games.
- limit average reward
- future discount reward

## 5-4 Learning in Repeated Games

two types of learning in repeated games.
- Fictitious Play
- No-regret Learning

### Fictitious Play

定义如下：

<div align="center"><img src='./images/def4.png'/></div>

不过，这里的“Break ties somehow.”不怎么理解。

Example:

<div align="center"><img src='./images/ex1.jpg'/></div>

以Round 0到Round 1为介绍：

player 1认为player 2 play heads的猜测次数是1.5从，play tails的猜测次数为2次，因此player 1认为player 2在下一轮（也就是Round 1）中会选择play tails，而player 1如果play tails则有>0的payoff。因此player 1会选择play tails。
而对于player 2来说，其则认为player 1 play heads的概率比较大，因此为了play得与player 1不同（才有>0的payoff)，因此player 2会选择play tails。

而当整个game持续play下去后，两者play heads或者tails的概率均为0.5，而**这恰好是这个game的纳什均衡点**。这是巧合吗？

实际上，有如下的定理：

<div align="center"><img src='./images/def5.jpg'/></div>

注：empirical：经验的

定理的一个重要条件是game要**收敛**，其需要满足如下的一些条件：

<div align="center"><img src='./images/def6.jpg'/></div>

注：
- 1. generic payoffs：**unknown**
- 2. potential game：**unknown**

### No-regret Learning

相关定义及说明：

<div align="center"><img src='./images/def7.jpg'/></div>


