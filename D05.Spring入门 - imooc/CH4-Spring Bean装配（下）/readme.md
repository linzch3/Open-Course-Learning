# Bean的定义

## classpath扫描与组件管理

![](1.png)

## 类的自动监测以及Bean的注册

![](7.png)

注：这里SimpleMovielister已经被注解为Service，Spring可以自动监测并注册Bean，如果没有注解则需要在XML文件中配置。

## context:annotation-config

![](8.png)

## 类的自动监测以及Bean的注册

![](9.png)

## 使用过滤器进行自定义扫描

![](2.png)

![](3.png)

## 定义bean

![](4.png)

注：默认Bean的名称是类名第一个字母变为小写，该名称也对应XML文件中的id属性值。如这里被注解为Repository的类的默认Bean名为movidFinderImpl。

## 作用域

![](5.png)

## 代理方式

![](6.png)

----

# @Autowired注解说明

## @Required注解（并不怎么常用）

![](11.png)

## @Autowired注解（非常常用）

![](12.png)

![](13.png)

![](14.png)

![](15.png)

![](17.png)

## @Qualifier注解

![](16.png)

使用XML配置的方式：

![](21.png)

![](18.png)

![](20.png)

# 基于Java的容器注解说明——@Bean

![](22.png)

![](23.png)


# 基于Java的容器注解说明——@ImportResource和@Value

第一种方式：使用XML配置：

![](24.png)

第二种方式：使用注解：

![](25.png)

以上两种方式等价

# 基于Java的容器注解说明——@Bean和@Scope

![](26.png)

# 基于Java的容器注解说明——基于泛型的自动装配

![](27.png)

# Spring对JSR支持的说明

![](28.png)



