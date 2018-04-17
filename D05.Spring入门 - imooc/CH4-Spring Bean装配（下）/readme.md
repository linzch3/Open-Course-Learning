# Bean的定义

## classpath扫描与组件管理

![](./images/1.png)

## 类的自动监测以及Bean的注册

![](./images/7.png)

注：这里SimpleMovielister已经被注解为Service，Spring可以自动监测并注册Bean，如果没有注解则需要在XML文件中配置。

## context:annotation-config

![](./images/8.png)

## 类的自动监测以及Bean的注册

![](./images/9.png)

## 使用过滤器进行自定义扫描

![](./images/2.png)

![](./images/3.png)

## 定义bean

![](./images/4.png)

注：默认Bean的名称是类名第一个字母变为小写，该名称也对应XML文件中的id属性值。如这里被注解为Repository的类的默认Bean名为movidFinderImpl。

## 作用域

![](./images/5.png)

## 代理方式

![](./images/6.png)

----

# @Autowired注解说明

## @Required注解（并不怎么常用）

![](./images/11.png)

## @Autowired注解（非常常用）

![](./images/12.png)

![](./images/13.png)

![](./images/14.png)

![](./images/15.png)

![](./images/17.png)

## @Qualifier注解

![](./images/16.png)

使用XML配置的方式：

![](./images/21.png)

![](./images/18.png)

![](./images/20.png)

# 基于Java的容器注解说明——@Bean

![](./images/22.png)

![](./images/23.png)


# 基于Java的容器注解说明——@ImportResource和@Value

第一种方式：使用XML配置：

![](./images/24.png)

第二种方式：使用注解：

![](./images/25.png)

以上两种方式等价

# 基于Java的容器注解说明——@Bean和@Scope

![](./images/26.png)

# 基于Java的容器注解说明——基于泛型的自动装配

![](./images/27.png)

# Spring对JSR支持的说明

![](./images/28.png)



