# Java 中的 ==, equals 与 hashCode 的区别与联系 <!-- omit in toc -->

- [一句话概括](#一句话概括)
- [关系操作符 ==](#关系操作符-)
- [```equals```方法](#equals方法)
  - [来源](#来源)
  - [```equals```方法的作用](#equals方法的作用)
  - [equals 重写原则](#equals-重写原则)
- [hashCode 方法](#hashcode-方法)
  - [来源](#来源-1)
  - [哈希相关概念](#哈希相关概念)
- [hashCode](#hashcode)
  - [hashCode 的常规协定](#hashcode-的常规协定)
- [References](#references)

## 一句话概括

* == ： 该操作符生成的是一个boolean结果，它计算的是操作数的值之间的关系
* equals ： Object 的 实例方法，比较两个对象的content是否相同
* hashCode ： Object 的 native方法 , 获取对象的哈希值，用于确定该对象在哈希表中的索引位置，它实际上是一个int型整数

## 关系操作符 == 

* 若操作数的类型是基本数据类型，则该关系操作符判断的是左右两边操作数的值是否相等
* 若操作数的类型是引用数据类型，则该关系操作符判断的是左右两边操作数的内存地址是否相同。也就是说，若此时返回true,则该操作符作用的一定是同一个对象。

## ```equals```方法

### 来源

equals方法是基类Object中的实例方法，因此对所有继承于Object的类都会有该方法。

在 Object 中的声明：

```java
public boolean equals(Object obj) {}
```

### ```equals```方法的作用

判断两个对象的 ```content``` 是否相等

为了更直观的理解，看一下源码实现

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

很显然，在Object类中，equals方法是用来比较两个对象的引用是否相等，即是否指向同一个对象。

但我们都知道，下面代码输出为 true:

```java
package JavaDayDayUp.EqualsTest;

public class Main {
    public static void main(String[] args) {
        String test1 = new String("hello");
        String test2 = new String("hello");

        System.out.println(test1.equals(test2));
    }
}

```

原来是 String 类重写了 equals 方法：

```java
/**
    * Compares this string to the specified object.  The result is {@code
    * true} if and only if the argument is not {@code null} and is a {@code
    * String} object that represents the same sequence of characters as this
    * object.
    *
    * @param  anObject
    *         The object to compare this {@code String} against
    *
    * @return  {@code true} if the given object represents a {@code String}
    *          equivalent to this string, {@code false} otherwise
    *
    * @see  #compareTo(String)
    * @see  #equalsIgnoreCase(String)
    */
public boolean equals(Object anObject) {
    if (this == anObject) { // 先判断引用是否相同(是否为同一对象),
        return true;
    }
    if (anObject instanceof String) {  //再判断类型是否一致,
        // 最后判断内容是否一致.
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

即对于诸如“字符串比较时用的什么方法,内部实现如何？”之类问题的回答即为：

使用equals方法，内部实现分为三个步骤：

* 先 比较引用是否相同(是否为同一对象),
* 再 判断类型是否一致（是否为同一类型）,
* 最后 比较内容是否一致

Java 中所有内置的类的 equals 方法的实现步骤均是如此，特别是诸如 Integer，Double 等包装器类。

### equals 重写原则

* 对称性： 如果x.equals(y)返回是“true”，那么y.equals(x)也应该返回是“true” ；

* 自反性： x.equals(x)必须返回是“true” ；

* 类推性： 如果x.equals(y)返回是“true”，而且y.equals(z)返回是“true”，那么z.equals(x)也应该返回是“true” ；

* 一致性： 如果x.equals(y)返回是“true”，只要x和y内容一直不变，不管你重复x.equals(y)多少次，返回都是“true” ；

* 对称性： 如果x.equals(y)返回是“true”，那么y.equals(x)也应该返回是“true”。

* 任何情况下，x.equals(null)【应使用关系比较符 ==】，永远返回是“false”；x.equals(和x不同类型的对象)永远返回是“false”

## hashCode 方法

### 来源

ashCode 方法是基类Object中的 实例```native```方法，因此对所有继承于Object的类都会有该方法。
　　
在 Object类 中的声明（native方法暗示这些方法是有实现体的，但并不提供实现体，因为其实现体是由非java语言在外面实现的）：

```java
public native int hashCode();
```

### 哈希相关概念

我们首先来了解一下哈希表：

1. 概念 ： Hash 就是把任意长度的输入(又叫做预映射， pre-image)，通过散列算法，变换成固定长度的输出(int)，该输出就是散列值。这种转换是一种 压缩映射，也就是说，散列值的空间通常远小于输入的空间。不同的输入可能会散列成相同的输出，从而不可能从散列值来唯一的确定输入值。简单的说，就是一种将任意长度的消息压缩到某一固定长度的消息摘要的函数。

2. 应用–数据结构 ： 数组的特点是：寻址容易，插入和删除困难; 而链表的特点是：寻址困难，插入和删除容易。那么我们能不能综合两者的特性，做出一种寻址容易，插入和删除也容易的数据结构？答案是肯定的，这就是我们要提起的哈希表，哈希表有多种不同的实现方法，我接下来解释的是最常用的一种方法——拉链法，我们可以理解为 “链表的数组”，如图：
   
![](res/Hashtable.png)

左边很明显是个数组，数组的每个成员是一个链表。该数据结构所容纳的所有元素均包含一个指针，用于元素间的链接。我们根据元素的自身特征把元素分配到不同的链表中去，也是根据这些特征，找到正确的链表，再从链表中找出这个元素。其中，将根据元素特征计算元素数组下标的方法就是散列法。

* 拉链法的适用范围 ： 快速查找，删除的基本数据结构，通常需要总数据量可以放入内存。

* hash函数选择，针对字符串，整数，排列，具体相应的hash方法；

* 碰撞处理，一种是open hashing，也称为拉链法，另一种就是closed hashing，也称开地址法，opened addressing。

## hashCode

在 Java 中，由 Object 类定义的 hashCode 方法会针对不同的对象返回不同的整数。（这是通过将该对象的内部地址转换成一个整数来实现的）

### hashCode 的常规协定

* 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。

* 如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。

* 如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法 不要求 一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。
　
要想进一步了解 hashCode 的作用，我们必须先要了解Java中的容器，因为 HashCode 只是在需要用到哈希算法的数据结构中才有用，比如 HashSet, HashMap 和 Hashtable。

Java中的集合（Collection）有三类，一类是List，一类是Queue，再有一类就是Set。 前两个集合内的元素是有序的，元素可以重复；最后一个集合内的元素无序，但元素不可重复。

那么, 这里就有一个比较严重的问题：要想保证元素不重复，可两个元素是否重复应该依据什么来判断呢？ 这就是 Object.equals 方法了。但是，如果每增加一个元素就检查一次，那么当元素很多时，后添加到集合中的元素比较的次数就非常多了。 也就是说，如果集合中现在已经有1000个元素，那么第1001个元素加入集合时，它就要调用1000次equals方法。这显然会大大降低效率。

于是，Java采用了哈希表的原理。 这样，我们对每个要存入集合的元素使用哈希算法算出一个值，然后根据该值计算出元素应该在数组的位置。所以，当集合要添加新的元素时，可分为两个步骤：

1. 先调用这个元素的 hashCode 方法，然后根据所得到的值计算出元素应该在数组的位置。如果这个位置上没有元素，那么直接将它存储在这个位置上；

2. 如果这个位置上已经有元素了，那么调用它的equals方法与新元素进行比较：相同的话就不存了，否则，将其存在这个位置对应的链表中（Java 中 HashSet, HashMap 和 Hashtable的实现总将元素放到链表的表头）。

## References

* [Java 中的 ==, equals 与 hashCode 的区别与联系](https://blog.csdn.net/justloveyou_/article/details/52464440)