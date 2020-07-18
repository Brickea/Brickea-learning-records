# Java 基础面试题 <!-- omit in toc -->

- [JVM内存](#jvm内存)
- [java8中，下面哪个类用到了解决哈希冲突的开放定址法](#java8中下面哪个类用到了解决哈希冲突的开放定址法)
- [JDK 1.7 前接口和抽象类的特性](#jdk-17-前接口和抽象类的特性)
- [线程安全的集合框架](#线程安全的集合框架)
- [进程互斥 - 皮森特算法](#进程互斥---皮森特算法)

## JVM内存

![](https://uploadfiles.nowcoder.com/images/20190606/291053_1559812298987_4E467FB794A7AF7967F62555B4F0B6A6)

**不包括 Heap Frame**

## java8中，下面哪个类用到了解决哈希冲突的开放定址法

* LinkedHashSet
* HashMap
* ThreadLocal (这个用到了)
* TreeMap

threadlocal 使用开放地址法 - 线性探测法：当前哈希槽有其他对象占了，顺着数组索引寻找下一个，直到找到为止

hashset 中调用 hashmap 来存储数据的，hashmap 采用的分离链表法：当哈希槽中有其他对象了，使用链表的方式连接到那个对象上

## JDK 1.7 前接口和抽象类的特性

1. 抽象类可以有构造方法,只是不能直接创建抽象类的实例对象而已
2. 在接口中 不可以有构造方法,在接口里写入构造方法时，编译器提示：Interfaces cannot have constructors。
3. Java不允许类多重继承
4. jdk1.8后接口中的方法可以有方法体，jdk1.8之前不可以有方法体

## 线程安全的集合框架

HashMap,TreeMap是线程不安全的。 HashTable 和 ConcurrentHashMap 都是线程安全的。同时Collection类还提供了synchronized()方法，使得线程安全

## 进程互斥 - 皮森特算法

```java

boolean flag[2];
 
int turn;
 
void P0()
 
{
 
   while(true)
 
{
 
    flag[0]=true;
 
    turn=1;
 
    while(flag[1]&&turn==1)
 
       /* donothing*/ ;
 
    /*critical section*/ ;
 
    flag[0]=false;
 
}
 
}
 
void P1()
 
{
 
   while(true)
 
{
 
    flag[1]=true;
 
    turn=0;
 
    while(flag[0]&&turn==0)
 
       /* donothing*/ ;
 
    /*critical section*/ ;
 
    flag[1]=false;
 
}
 
}
 
void main()
 
{
 
flag[0]=flag[1]=false;
 
/*start p0 and p1*/ ;
 
```

考虑进程P0，一旦它设置flag[0]=true，则P1不能进入临界区。如果P1已经进入临界区，那么flag[1]=true，P0被阻塞不能进入临界区。

另一方面，互相阻塞也避免了。假设P0在while里被阻塞了，表示flag[1]为true且turn＝1，则此时P1可以执行。