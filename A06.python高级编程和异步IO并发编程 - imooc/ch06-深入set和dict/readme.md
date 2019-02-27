## 其他

深拷贝和浅拷贝的区别：https://www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy/

注意：不要去继承python内置的dict和list，可能有些函数不会被调用，如果要实现dict的子类，可以通过继承collections的UserDict来实现目的。

frozenset：已经初始化后就不可修改的集合