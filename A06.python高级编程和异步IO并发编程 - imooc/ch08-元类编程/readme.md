## property动态属性

```python
from datetime import date, datetime


class User:
    def __init__(self, name, birthday):
        self.name = name
        self.birthday = birthday

    @property
    def age(self):
        return datetime.now().year - self.birthday.year


user = User("tom", date(year=1987, month=3, day=28))
print(user.age)
```
输出：
```python
32
```

## `__getattr__`以及`__getattribute__`的区别

`__getattr__` ：在查找不到属性时调用
`__getattribute__`：所有属性的查找都要经过这个魔法函数，能够不重写就不重写，避免影响正常属性查找的流程

```python
class User:
    def __init__(self, info):
        self.info = info

    def __getattr__(self, item):
        return self.info[item]


user = User(info={"name": "tom", "nation": "USA", "sex": "boy"})
print(user.name, user.nation, user.sex)
```

输出：

```
tom USA boy
```

##  属性描述符和属性查找过程

通过属性描述符控制属性的赋值，比如控制变量age只能赋值为int类型数据，而不是字符串类型。

属性描述符分为两种：1，数据属性描述符。2，非数据属性描述符

属性查找过程：

如果user是某个类的实例，那么user.age（以及等价的getattr（user,'age')）首先调用`__getattribute__`。

如果类定义了`__getattr__`方法，那么在`__getattribute__`抛出AttributeError的时候就会调用到`__getattr__`，
而对于描述符（`__get__`）的调用，则发生在`__getattribute__`内部的。

user=User(),那么user.age顺序如下：

- （1）如果“age”出现在User或其基类（父类）的`__dict__`中，且age是data descriptor，那么调用其`__get__`方法，否则
- （2）如果“age”出现在user的__dict__中，那么直接返回`obj.__dict__['age']`,否则
- （3）如果“age”出现在User或其基类（父类）的__dict__中，
    - (3.1) 如果age是non-data descriptor，那么调用其`__get__`方法,否则
    - (3.2) 返回`__dict__['age']`
- （4）如果User有一个`__getattr__`方法，调用`__getattr__`方法，否则
- （5）抛出AttributeError


```python
import numbers


# 只要实现下面3个方法中的任何一个，IntField就是属性描述符
class IntField:
    # 数据属性描述符
    def __get__(self, instance, owner):
        return self.value  # 这个value就是下面自己定义的

    # 在__set__做参数类型的检查
    def __set__(self, instance, value):
        if not isinstance(value, numbers.Integral):
            raise ValueError("int value need")
        if value < 0:
            raise ValueError("positive value need")
        # instance.age=value 不能这么存放值，因为这种属性赋值的方法会循环调用__set__方法
        # 将值放在了IntField类里面，前面一个value是自己命名的，随便叫什么都可以
        self.value = value

    def __delete__(self, instance):
        pass


class NonDataIntField:
    # 非数据属性描述符
    def __get__(self, instance, owner):
        return self.value


class User:
    age = IntField()  # age是一个数据属性描述符对象
    # age = NonDataIntField() # age是一个非数据属性描述符对象


if __name__ == "__main__":
    user = User()
    user.age = 20  # 当age是数据属性描述符时，对其赋值的时候，会调用IntField中的__set__方法,该值进入IntField的value中，而不会进入user的__dict__中
    user.__dict__['age'] = 'abc'
    print(user.__dict__)  # __dict__方法用来查询属性，当age是非数据属性描述符时，age就会进入user的属性中：{'age': 20}
    print(user.__dict__['age'])  # abc
    print(user.age)  # 因为age是数据属性描述符，使用user.age的时候还是会调用IntField中的value值，而不会调用user的属性值abc
    print(getattr(user, 'age'))

```

当`age = IntField()`时，输出为：

```
{'age': 'abc'}
abc
20
20
```

当`age = NonDataIntField()`时，输出为：
```
{'age': 'abc'}
abc
abc
abc
```

## `__new__`和`__init__`的区别


`__init__`方法为初始化方法,`__new__`方法才是真正的构造函数。

参考：https://zhuanlan.zhihu.com/p/21379984

## 自定义元类

元类：创建类的类，没有必要用的时候尽量不要用，但若是自己写框架的话，则是可以通过元类编程提高程序的健壮性。


