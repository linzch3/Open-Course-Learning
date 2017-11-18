- [Lab 6 - 服务与多线程--简单音乐播放器](#lab-6---%E6%9C%8D%E5%8A%A1%E4%B8%8E%E5%A4%9A%E7%BA%BF%E7%A8%8B--%E7%AE%80%E5%8D%95%E9%9F%B3%E4%B9%90%E6%92%AD%E6%94%BE%E5%99%A8)
    - [实验目的](#%E5%AE%9E%E9%AA%8C%E7%9B%AE%E7%9A%84)
    - [实验内容](#%E5%AE%9E%E9%AA%8C%E5%86%85%E5%AE%B9)
    - [实验过程](#%E5%AE%9E%E9%AA%8C%E8%BF%87%E7%A8%8B)
    - [遇到的问题及解决方案](#%E9%81%87%E5%88%B0%E7%9A%84%E9%97%AE%E9%A2%98%E5%8F%8A%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88)
    - [参考资料](#%E5%8F%82%E8%80%83%E8%B5%84%E6%96%99)

# Lab 6 - 服务与多线程--简单音乐播放器

## 实验目的

- 1.学会使用 MediaPlayer；
- 2.学会简单的多线程编程，使用 Handle 更新 UI；
- 3.学会使用 Service 进行后台工作；
- 4.学会使用 Service 与 Activity 进行通信。

## 实验内容

实现一个简单的播放器，要求功能有：

- 1.播放、暂停、停止、退出功能；
- 2.后台播放功能；
- 3.进度条显示播放进度、拖动进度条改变进度功能；
- 4.播放时图片旋转，显示当前播放时间功能；

![](./images/1.png)

## 实验过程




## 遇到的问题及解决方案

- 1.如何在电脑端和手机端上导入文件到手机中。
    - 解决方案：
        - 电脑端：在保证手机已经开启调试模式后可连接电脑时，打开AS->点击Tools->点击Android选项->点击Android Device Monitor。
        - 手机端：下载app——RE文件管理器（操作需要Root权限，做法见下一个问题）。

- 2.想按照TA在实验文档中给出的例子一样，将此次实验的音乐文件放置在真机的`\data`下，但是通过无论是在手机端使用RE文件管理器还是在电脑端使用Android Device Monitor，均因为权限问题无法导入文件到`\data`路径下。
    - 解决方案：先开启手机的Root权限，然后通过RE文件管理器修改`\`路径下`data`文件夹的读写权限。随后便可在电脑端

- 3.如何开启手机的Root权限？
    - 注意事项：**开启Root权限可能会影响手机的保修，所以在进行该操作之前一定要慎重**。（不过好像也有办法可以让Root之后的手机还原到未Root的状态，具体方案就没有去了解）
    - 一般的方法：在电脑端下载**刷机大师**、**kingroot**等软件，通过USB连接手机到电脑，使用软件就可以进行刷机了。
    - 如果上面的方法没有用，可能就得自行百度`root 你的手机品牌`来查找具体方案了。
        - 由于我使用的是meizu手机，使用上面的刷机软件就无法获取Root权限。后来发现魅族高版本的魅族手机系统有开启Root权限的功能（设置->安全->ROOT权限），但是我的魅蓝2的版本太低，没有出现该设置接口，解决方案如下：
            - 在电脑端去官网下载最新的手机系统版本，然后拷贝安装包到手机内置存储卡，然后在手机的文档管理器上点击安装包进行安装，安装后就出现ROOT权限接口了。

- 4.开启手机的ROOT权限之后**找不到手机的开发者选项**？
    - 刚很兴奋地开启ROOT权限，回到设置后发现开发者选项不见了，真是历经波折，百度了之后得到如下的解决方案：
        - 打开设置->关于手机->找到手机的版本号->点击版本号3次就可以开启开发者选项。
        - 随后，回到设置，就可以找到开发者选项的入口了（这个每个手机都不一样）。
        - 更多可参考[这里][1]

- 5.[MediaPlayer播放音乐的时候报错： Should have subtitle controller already set][9]

## 参考资料

- [Android MediaPlayer各种状态切换][2]
- [Google:Develop > API Guides > Animation and Graphics][3]
- [Android动画-属性动画-ObjectAnimator][4]
- [Handler基本使用（二）new Message 、 Handler.obtainMessage和Message.obtain][5]
- [Handler sendMessage 与 obtainMessage (sendToTarget)比较][6]
- [android中常用的finish()与onDestroy()的区别][7]
- [ObjectAnimator 基本使用][8]


[1]:http://bbs.zol.com.cn/softbbs/d21_187366.html
[2]:http://blog.csdn.net/joke124/article/details/51674001
[3]:https://developer.android.com/guide/topics/graphics/prop-animation.html
[4]:http://www.jianshu.com/p/48d79eaf3470
[5]:http://blog.csdn.net/u011791526/article/details/53540346
[6]:http://blog.csdn.net/winson_jason/article/details/8149284
[7]:http://blog.csdn.net/lanxingfeifei/article/details/50662133
[8]:http://wiki.jikexueyuan.com/project/android-animation/7.html
[9]:http://blog.csdn.net/ouyang_peng/article/details/54023240

