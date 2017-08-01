<!-- TOC -->

- [单元4：matplotlib库入门](#单元4matplotlib库入门)
    - [4.1 绘图部分](#41-绘图部分)
    - [4.2 pyplot的中文显示](#42-pyplot的中文显示)
    - [4.3 pyplot的文本显示](#43-pyplot的文本显示)
    - [4.4 pyplot的子绘图区域](#44-pyplot的子绘图区域)
    - [单元总结](#单元总结)
- [单元5：matplotlib库基础绘图函数（5个实例）](#单元5matplotlib库基础绘图函数5个实例)
    - [5.1 基础图标函数](#51-基础图标函数)
    - [5.2 饼图绘制](#52-饼图绘制)
    - [5.3 直方图绘制](#53-直方图绘制)
    - [5.4 极坐标绘制](#54-极坐标绘制)
    - [5.5 散点图绘制](#55-散点图绘制)
- [单元6：“引力波”的绘制](#单元6引力波的绘制)

<!-- /TOC -->
## 单元4：matplotlib库入门

更多可参考：http://matplotlib.org/gallery.html

**写在前面：matplotlib库非常复杂，我们没必要花时间去学习所有函数，对于该库，应该采用：根据我们已有的数据，查询文档或搜索，来即时选择可实现目的的函数，以实践指导理论学习。**

Matplotlib库由各种可视化类构成，内部结构复杂，受**Matlab**启发**（该模块的用法和matlab有很大的相似之处）**

matplotlib.pyplot是绘制各类可视化图形的命令子库，相当于快捷方式

```py
import matplotlib.pyplot as plt
```

plt通常为该模块的别名

实例1：

![这里写图片描述](http://img.blog.csdn.net/20170430221938850?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
实例2：

![这里写图片描述](http://img.blog.csdn.net/20170430222008255?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
实例3：

![这里写图片描述](http://img.blog.csdn.net/20170430221951241?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.1 绘图部分
**subplot函数**

![这里写图片描述](http://img.blog.csdn.net/20170430222249631?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430222339481?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
注意：调用subplot函数之后要使用plot函数才可以绘图

**plot函数**

![这里写图片描述](http://img.blog.csdn.net/20170430222402841?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430222515624?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

参数解释【format_string】：

format_string: 控制曲线的**格式字符串**，可选，其由**颜色字符、 风格字符和标记字符**组成

颜色字符：

![这里写图片描述](http://img.blog.csdn.net/20170430222705945?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

风格字符：

![这里写图片描述](http://img.blog.csdn.net/20170430222742992?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

标记字符：

![这里写图片描述](http://img.blog.csdn.net/20170430222819383?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实例：

![这里写图片描述](http://img.blog.csdn.net/20170430222858739?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

参数解释【kwargs】：

![这里写图片描述](http://img.blog.csdn.net/20170430223046976?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.2 pyplot的中文显示

**第一种方法（修改全局的字体属性）**

pyplot并不默认支持中文显示，需要**rcParams**修改字体实现

![这里写图片描述](http://img.blog.csdn.net/20170430223333667?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**rcParams属性：**

![这里写图片描述](http://img.blog.csdn.net/20170430223417324?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**中文字体的分类：**

![这里写图片描述](http://img.blog.csdn.net/20170430223439682?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**实例：**

![这里写图片描述](http://img.blog.csdn.net/20170430223553217?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**第二种方法（修改局部的字体属性）**

在有中文输出的地方，增加一个属性：fontproperties

![这里写图片描述](http://img.blog.csdn.net/20170430230031625?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.3 pyplot的文本显示

**常用函数：**

![这里写图片描述](http://img.blog.csdn.net/20170430232846179?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

text函数实例：

![这里写图片描述](http://img.blog.csdn.net/20170430233000337?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

annotate函数实例：

![这里写图片描述](http://img.blog.csdn.net/20170430233311373?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 4.4 pyplot的子绘图区域

假设现在要绘制如下有多个区域的图形，那么subplot显然无法满足我们的要求。

![这里写图片描述](http://img.blog.csdn.net/20170430233528080?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

因此引入subplot2grid函数来实现该功能。

![这里写图片描述](http://img.blog.csdn.net/20170430233634807?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

因此使用该函数，对应上图的调用语句为：

![这里写图片描述](http://img.blog.csdn.net/20170430233759318?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

为了使上面的subplot2grid函数的调用显示更加简洁，这里引入GridSpec类来实现该功能

![这里写图片描述](http://img.blog.csdn.net/20170430234004259?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 单元总结

![这里写图片描述](http://img.blog.csdn.net/20170430234104434?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430234111731?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 单元5：matplotlib库基础绘图函数（5个实例）

### 5.1 基础图标函数

![这里写图片描述](http://img.blog.csdn.net/20170430234734813?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430234741017?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170430234910161?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.2 饼图绘制

![这里写图片描述](http://img.blog.csdn.net/20170430235035190?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170430235110596?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.3 直方图绘制

![这里写图片描述](http://img.blog.csdn.net/20170430235250034?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.4 极坐标绘制

![这里写图片描述](http://img.blog.csdn.net/20170430235713922?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 5.5 散点图绘制

![这里写图片描述](http://img.blog.csdn.net/20170430235840350?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 单元6：“引力波”的绘制
介绍：

物理学中，引力波是因为时空弯曲对外以辐射形式传播的能量，爱因斯坦基于广义相对论预言了引力波的存在，2015年9月14日， LIGO合作组宣布探测到首个引力波信号。2016年6月16日，LIGO合作组宣布2015年12月26日03:38:53（UTC），两台不同位置的引力波探测器同时探测到了一个引力波信号。

数据源：
http://python123.io/dv/grawave.html
http://python123.io/dv/H1_Strain.wav
http://python123.io/dv/L1_Strain.wav
http://python123.io/dv/wf_template.txt

代码：
```py
import numpy as np
import matplotlib.pyplot as plt
from scipy.io import wavfile

rate_h, hstrain= wavfile.read(r"./fileNeeded/H1_Strain.wav","rb")
rate_l, lstrain= wavfile.read(r"./fileNeeded/L1_Strain.wav","rb")
#reftime, ref_H1 = np.genfromtxt('./fileNeeded/GW150914_4_NR_waveform_template.txt').transpose()
reftime, ref_H1 = np.genfromtxt('./fileNeeded/wf_template.txt').transpose()

htime_interval = 1/rate_h
ltime_interval = 1/rate_l
fig = plt.figure(figsize=(12, 6))#创建一个大小为12*6的绘图空间


htime_len = hstrain.shape[0]/rate_h
htime = np.arange(-htime_len/2, htime_len/2 , htime_interval)
plth = fig.add_subplot(221)
plth.plot(htime, hstrain, 'y')#画出以时间为X轴，应变数据为Y轴的图像并设置标题和坐标轴的标签
plth.set_xlabel('Time (seconds)')
plth.set_ylabel('H1 Strain')
plth.set_title('H1 Strain')

ltime_len = lstrain.shape[0]/rate_l
ltime = np.arange(-ltime_len/2, ltime_len/2 , ltime_interval)
pltl = fig.add_subplot(222)
pltl.plot(ltime, lstrain, 'g')
pltl.set_xlabel('Time (seconds)')
pltl.set_ylabel('L1 Strain')
pltl.set_title('L1 Strain')

pltref = fig.add_subplot(212)
pltref.plot(reftime, ref_H1)
pltref.set_xlabel('Time (seconds)')
pltref.set_ylabel('Template Strain')
pltref.set_title('Template')
fig.tight_layout()#自动调整图像外部边缘

plt.savefig("Gravitational_Waves_Original.png")
plt.show()
plt.close(fig)

```

效果：

![这里写图片描述](http://img.blog.csdn.net/20170501095937141?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGluemNoMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

其中template是理想情况下的引力波图像，H1 Strain、L1 Strain是带有噪声信息的引力波图像。