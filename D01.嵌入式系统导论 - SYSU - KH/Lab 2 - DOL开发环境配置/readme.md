<!-- TOC -->

- [1. Lab 2 - DOL开发环境配置](#1-lab-2---dol开发环境配置)
    - [1.1. 实验操作流程](#11-实验操作流程)
        - [1.1.1. 预配置环境](#111-预配置环境)
        - [1.1.2. 下载文件](#112-下载文件)
        - [1.1.3. 解压文件](#113-解压文件)
        - [1.1.4. 编译systemc](#114-编译systemc)
        - [1.1.5. 编译dol](#115-编译dol)
    - [1.2. 相关理论知识](#12-相关理论知识)
- [2. 附录](#2-附录)
    - [2.1. 执行命令时遇到的问题及解决办法](#21-执行命令时遇到的问题及解决办法)
        - [2.1.1. 执行命令：sudo apt-get install openjdk-7-jdk](#211-执行命令sudo-apt-get-install-openjdk-7-jdk)
        - [2.1.2. 其他可能遇到的问题及解决方案](#212-其他可能遇到的问题及解决方案)

<!-- /TOC -->
# 1. Lab 2 - DOL开发环境配置

## 1.1. 实验操作流程

### 1.1.1. 预配置环境

打开ubuntu的terminal，输入如下命令：

```r
sudo apt-get update
sudo apt-get install ant
sudo apt-get install openjdk-7-jdk
sudo apt-get install unzip
```

### 1.1.2. 下载文件

接着使用wget下载两个文件：

```r
sudo wget http://www.accellera.org/images/downloads/standards/systemc/systemc-2.3.1.tgz

sudo wget http://www.tik.ee.ethz.ch/~shapes/downloads/dol_ethz.zip
```
### 1.1.3. 解压文件

接着新建dol的文件夹（下一步运行unzip命令是不会解压文件都一个新的文件夹的，所以我们要手动创建）：

```r
sudo mkdir dol
```

将dolethz.zip解压到 dol文件夹中:

```r
sudo unzip dol_ethz.zip -d dol
```

并在当前目录解压systemc:

```r
sudo tar -zxvf systemc-2.3.1.tgz
```

### 1.1.4. 编译systemc

解压后进入systemc-2.3.1的目录下

```r
cd systemc-2.3.1
```
新建一个临时文件夹objdir

```r
sudo mkdir objdir
```

进入该文件夹objdir

```r
cd objdir
```

运行configure(能根据系统的环境设置一下参数，用于编译)

```r
sudo ../configure CXX=g++ --disable-async-updates
```
运行结果为：

<img src="./images/3.jpg">

接着输入：

```r
sudo make install
```

返回到上一级目录(`cd ..`)，查看所有文件，并输出当前的工作路径（这个后续会用到）

<img src="./images/4.jpg">

回到刚刚dol的那个文件夹`cd ../dol`，找到build_zip.xml这个文件

<img src="./images/5.jpg">

执行`sudo gedit build_zip.xml`命令，找到红色框中部分的两段代码：

<img src="./images/6.jpg">

将蓝色部分的代码改为上面找到的systemC的工作路径，比如我的修改后则为：

<img src="./images/7.jpg">

**注意：对于64位系统的机器，lib-linux要改成lib-linux64**，查询方式如下：

<img src="./images/8.jpg">

### 1.1.5. 编译dol

接着输入如下命令，成功后会显示BUILD SUCCESSFUL

```r
sudo ant -f build_zip.xml all
```

接着输入`cd build/bin/main`运行第一个例子，命令为：

```r
sudo ant -f runexample.xml -Dnumber=1
```

运行后得到如下结果就算成功啦

<img src="./images/9.jpg">

用ls命令发现在当前文件夹下多出了一个example1文件夹，打开后发现里面有个example1.dot文件

<img src="./images/10.jpg">

.dot文件需要xdot等软件才能打开，因此使用如下命令安装xodt：

```r
sudo apt-get install xdot
```

安装完成后执行`xdot example1.dot`可看到：

<img src="./images/11.jpg">

至此，环境搭建完成。

## 1.2. 相关理论知识

这次说是说DOL开发环境配置，但是这DOL到底是什么东西？

可参考这里：http://www.tik.ee.ethz.ch/~shapes/dol.html

除此之外，DOL还需要make，javac，ant等环境的支持，这又是些什么东西呢？

关于make，可参考这里：http://blog.chinaunix.net/uid-9314244-id-2004686.html

关于ant，可参考这里：http://blog.163.com/qiangyongbin2000@126/blog/static/77517819201151653423687/

关于java和javac：

- javac命令用来编译java文件

- java命令可以执行生成的class文件

# 2. 附录

## 2.1. 执行命令时遇到的问题及解决办法

### 2.1.1. 执行命令：sudo apt-get install openjdk-7-jdk

在执行`sudo apt-get install openjdk-7-jdk`命令时出现如下问题：

<img src="./images/1.jpg">

输入javac -v查询后如下：

<img src="./images/2.jpg">

因此，可能在我的这个ubuntu下不能安装`openjdk-7-jdk`，因此可根据上图安装`openjdk-8-jdk`：

`sudo apt-get install openjdk-8-jdk`

安装后输如javac -version就可查询安装的版本了。

### 2.1.2. 其他可能遇到的问题及解决方案


1.sc_application没有找到，可能的三种原因如下：

- 1.1.systemC安装出问题，没有找到systemC的库，重新安装下systemC。
- 1.2.编译失败了，在所修改后的路径下没有找到文件。可能你的系统是32位的，用uname -a命令查。如果出现 x86_64,说明是64位的，如果是i686的，说明是32位的。根据位数来修改lib-linux。
- 1.3.中文系统会因为时间问题，导致runexample.xml文件编译出错，注释掉里面一段代码就行。具体看群文件。

2.xml文件双击打开后修改完无法保存。

因为里面的xml文件加锁了，只是普通的用户权限无法进行修改，需要root权限。可以在命令行用sudo gedit 文件名 指令来修改。也可以使用vi或vim命令去修改。

3.16.04的ubuntun需要安装jdk8，强行安装jdk7可能无法运行。

4.dot文件可以直接进入到对应文件夹，双击打开，打开时会提醒你安装xdot工具。安装后就能打开
