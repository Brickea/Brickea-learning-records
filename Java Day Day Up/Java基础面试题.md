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

```c++
#define N 2 //进程数为2  
int turn;  //现在轮到哪个进程？  
int interested  
; //初始化置为false，即没有在临界区等待读写共享数据的  
  
void enter_region(int process) //进入临界区  
{  
turn = process;  
int other = 1 - turn; //另一个进程  
interested[turn] = true;  
while(turn == process && interested[other] == true)  
; //一直循环，直到other进程退出临界区  
}  
  
void leave_region(int process)  
{  
interested[process] = false;  
}  
```


进程通信时无非会产生下列的两种情况：

1. 进程0通信，进程1不影响。（反之亦然）

enter_region()中各参数的值：

turn = 0;

other = 1;

interested[0] = true;

interested[1] = false;

while循环直接在interested[other]那一步就退出了，进程0成功进入临界区。

2. 进程0通信，进程1也要通信。（反之亦然）

进程0的状态如上。

进程1在enter_region()后各参数的值：

turn = 1;

other = 0;

interested[1] = true;

interested[0] = true;

while循环持续，直到进程0调用leave_region()退出临界区，使得interested[0] = false。