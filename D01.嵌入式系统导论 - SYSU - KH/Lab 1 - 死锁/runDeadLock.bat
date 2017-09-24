@echo off
rem 关闭回显，即运行该批处理命令时下面的命令不会在命令行上显示，@表示echo off这条命令自身也不显示
javac Deadlock.java
rem 编译.java文件
set var=0
rem 初始设置变量为0
:start
rem 开始标志
set /a var+=1
rem 每一次循环变量var加一
echo %var%
rem 在cmd上显示变量var的值
java DeadLock
rem 运行DeadLock类
if %var% leq 1000 GOTO start
rem 如果变量var的值小于等于1000，则跳转到start处接着运行命令
pause
rem 停止运行该批处理文件