
# Lab 5 - appwidget 及broadcast 使用

## 实验目的 

- 1、掌握 AppWidget 编程基础  
- 2、掌握 Broadcast 编程基础  
- 3、掌握动态注册 Broadcast 和静态注册 Broadcast  

## 实验内容

在上次实验的基础上进行修改,实现一个Android 应用，实现静态广播、动态广播两种改变widget内容的方法。

具体要求:   

- widget初始情况，如下图左一。

- 点击widget可以启动应用，并在widget随机推荐一个商品，如下图左二。

- 点击widget跳转到该商品详情界面，如下图左三。

- 点击购物车图标，widget相应更新，如下图左四。

- 点击widget跳转到购物车界面，如下图左五。

![](./images/1.png)

- 实现方式要求:
    - 启动时的widget的更新通过静态广播实现.
    - 点击购物车图标时候widget的更新通过动态广播实现。