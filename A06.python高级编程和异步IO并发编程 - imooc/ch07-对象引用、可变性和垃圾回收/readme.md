## 变量到底是什么

变量可看成是“便利贴”。

```python
a = 1
a = "Abc"
```

## == 和 is 的区别

==：值相等为True
```
In[22]: a = "abc"
In[23]: b ="abc"
In[24]: a == b
Out[24]: True
```
is：指向同个对象时为True
```
In[25]: a = [1, 2, 3]
In[26]: b = a
In[27]: a is b
Out[27]: True
In[28]: id(a), id(b)
Out[28]: (1946263966152, 1946263966152)
```

## del语句与垃圾回收

del某个对象的时候，相应的魔法函数`__del__`会被调用，可用于自定义回收逻辑。

## 一个经典的参数错误

结论：当传入list等可变对象为输入参数时需要注意，可能可变对象的值会受到改变.

```python
class test:
    def __init__(self, a_list=[]):
        self.a_list = a_list


a = test()
a.a_list.append("a")
print(a.a_list)

b = test()
b.a_list.append("b")
print(b.a_list)
```

输出：

```
['a']
['a', 'b']
```

问题就在于，默认的a_list参数时可变的，当a和b初始化时不输入参数，则默认都使用同一个list作为输入参数，因此当输出b的a_list时就是['a', 'b']而不是['b']了。

