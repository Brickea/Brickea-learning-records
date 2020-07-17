# Java 多线程 <!-- omit in toc -->

- [线程创建的三种方式](#线程创建的三种方式)
  - [继承Thread实现多线程](#继承thread实现多线程)
  - [实现 ```Runnable```接口](#实现-runnable接口)
  - [创建多线程匿名类](#创建多线程匿名类)
- [线程常用方法](#线程常用方法)
  - [暂停当前线程](#暂停当前线程)
  - [加入到当前线程中](#加入到当前线程中)
  - [线程优先级](#线程优先级)
  - [临时暂停线程](#临时暂停线程)
  - [守护线程](#守护线程)
- [同步](#同步)
  - [同步思路](#同步思路)
  - [```synchronized``` 同步对象概念](#synchronized-同步对象概念)
  - [在方法上加 ```synchronized```](#在方法上加-synchronized)
- [线程安全的类 - 面试](#线程安全的类---面试)
  - [HashMap 和 Hashtable的区别](#hashmap-和-hashtable的区别)
  - [StringBuffer和StringBuilder的区别](#stringbuffer和stringbuilder的区别)
  - [ArrayList和Vector的区别](#arraylist和vector的区别)
  - [把非线程安全的集合转换为线程安全](#把非线程安全的集合转换为线程安全)
- [死锁](#死锁)
- [线程交互](#线程交互)
  - [直观的解决方案](#直观的解决方案)
  - [使用wait 和 notify进行交互](#使用wait-和-notify进行交互)
  - [注意 wait notify notifyAll 的位置](#注意-wait-notify-notifyall-的位置)
- [线程池](#线程池)
  - [线程池的设计思路](#线程池的设计思路)
  - [使用Java内置线程池](#使用java内置线程池)
- [锁 lock](#锁-lock)
  - [使用Lock对象实现同步效果](#使用lock对象实现同步效果)
  - [trylock 方法](#trylock-方法)
  - [Lock的通讯](#lock的通讯)
  - [总结Lock和synchronized的区别总结](#总结lock和synchronized的区别总结)
- [原子操作](#原子操作)
  - [AtomicInteger](#atomicinteger)

## 线程创建的三种方式

进程(Processor)和线程(Thread)是不一样的

**进程**: 启动一个程序可以叫做一个进程，再启动一个程序就有了两个进程

**线程**：线程是在进程内部同事做的事情，比如在一个通讯软件，同时好几个人互相发私信就是一种多线程

此处展示不适多线程的转钱

```java
package JavaDayDayUp.ThreadTest;

public class Person {
    private int money;
    private String name;

    public Person(String name){
        this.name = name;
        this.money = 100;
    }

    public void giveMoney(Person p){
        try{
            Thread.sleep(1000); // 体现延迟
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(this.name+"正在给"+p.getName()+"转钱");
        if(this.hasMoney()) {
            this.money -= 10;
            p.setMoney(p.getMoney() + 10);
        }
    }

    private boolean hasMoney(){
        return this.money==0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

        brickea.giveMoney(sandy);
        brickea.giveMoney(sandy);
        brickea.giveMoney(sandy);

        charly.giveMoney(echo);
        charly.giveMoney(echo);
        charly.giveMoney(echo);
    }
}

```

```
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
charly正在给echo转钱
charly正在给echo转钱
charly正在给echo转钱
```

### 继承Thread实现多线程

使用多线程，就可以做到brickea给sandy转钱的同时，charly也可以给echo转钱

设计一个类Transfer 继承Thread，并且重写run方法

启动线程办法： 实例化一个Person对象，并且调用其start方法

就可以观察到 brickea给sandy转钱的同时，charly也可以给echo转钱

```java
package JavaDayDayUp.ThreadTest;

public class Transfer extends Thread{
    private Person a;
    private Person b;
    public Transfer(Person a,Person b){
        this.a = a;
        this.b = b;
    }

    private void transfer(){
        this.a.giveMoney(this.b);
    }

    @Override
    public void run() {
        this.transfer();
    }
}
```

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

        Transfer brickeaTsandy = new Transfer(brickea,sandy);
        brickeaTsandy.start();

        Transfer echoTcharly = new Transfer(echo,charly);
        echoTcharly.start();
    }
}

```

```
echo正在给charly转钱
brickea正在给sandy转钱
```

两者同时出现

### 实现 ```Runnable```接口

创建类Transfer，实现Runnable接口

启动的时候，首先创建一个Transfer对象，然后再根据该对象创建一个线程对象，并启动

```java
Transfer brickeaTsandy = new Transfer(brickea, sandy);
new Thread(brickeaTsandy).start();
```

battle1 对象实现了Runnable接口，所以有run方法，但是直接调用run方法，并不会启动一个新的线程。

必须，借助一个线程对象的start()方法，才会启动一个新的线程。

所以，在创建Thread对象的时候，把battle1作为构造方法的参数传递进去，这个线程启动的时候，就会去执行battle1.run()方法了。

```java
package JavaDayDayUp.ThreadTest;

public class Transfer implements Runnable {
    private Person a;
    private Person b;

    public Transfer(Person a, Person b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        this.a.giveMoney(this.b);
    }
}
```

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

        Transfer brickeaTsandy = new Transfer(brickea, sandy);
        new Thread(brickeaTsandy).start();

        Transfer echoTcharly = new Transfer(echo, charly);
        new Thread(echoTcharly).start();
    }
}

```

```
echo正在给charly转钱
brickea正在给sandy转钱
```

效果相同

### 创建多线程匿名类

使用匿名类，继承Thread,重写run方法，直接在run方法中写业务代码

匿名类的一个好处是可以很方便的访问外部的局部变量。

前提是外部的局部变量需要被声明为final。(JDK7以后就不需要了)

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

//        Transfer brickeaTsandy = new Transfer(brickea,sandy);
//        brickeaTsandy.start();
//
//        Transfer echoTcharly = new Transfer(echo,charly);
//        echoTcharly.start();

//        Transfer brickeaTsandy = new Transfer(brickea, sandy);
//        new Thread(brickeaTsandy).start();
//
//        Transfer echoTcharly = new Transfer(echo, charly);
//        new Thread(echoTcharly).start();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                brickea.giveMoney(sandy);
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                charly.giveMoney(echo);
            }
        };

        thread1.start();
        thread2.start();
    }
}

```

```
brickea正在给sandy转钱
charly正在给echo转钱
```


把上述3种方式再整理一下：

1. 继承Thread类
2. 实现Runnable接口
3. 匿名类的方式

注： 启动线程是start()方法，run()并不能启动一个新的线程

## 线程常用方法

### 暂停当前线程

```Thread.sleep(1000)``` 表示当前线程暂停1000毫秒，其他线程不受影响

```Thread.sleep(1000)``` 会抛出 ```InterruptedException```中断异常，因为线程sleep的时候，有可能被中断

```java
package multiplethread;

public class TestThread {

    public static void main(String[] args) {
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);
                }
            }
        };
        t1.start();
    }
}
```

### 加入到当前线程中

首先解释一下**主线程**的概念

所有进程，至少会有一个线程即主线程，即main方法开始执行，就会有一个看不见的主线程存在。

在行执行t.join，即表明在主线程中加入该线程。

主线程会等待该线程结束完毕， 才会往下运行。

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                brickea.giveMoney(sandy);
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                charly.giveMoney(echo);
            }
        };

        thread1.start();
        thread2.start();
        try{
            thread2.join();
            thread1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}

```
charly先转完钱才会brickea转钱
```
charly正在给echo转钱
brickea正在给sandy转钱
结束
```

### 线程优先级

当线程处于竞争关系的时候，优先级高的线程会有更大的几率获得CPU资源

为了演示该效果，要把暂停时间去掉，多条线程各自会尽力去占有CPU资源

如图可见，线程1的优先级是MAX_PRIORITY，所以它争取到了更多的CPU资源执行代码

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

//        Transfer brickeaTsandy = new Transfer(brickea,sandy);
//        brickeaTsandy.start();
//
//        Transfer echoTcharly = new Transfer(echo,charly);
//        echoTcharly.start();

//        Transfer brickeaTsandy = new Transfer(brickea, sandy);
//        new Thread(brickeaTsandy).start();
//
//        Transfer echoTcharly = new Transfer(echo, charly);
//        new Thread(echoTcharly).start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (!brickea.hasMoney()) {
                    brickea.giveMoney(sandy);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (!charly.hasMoney()) {
                    charly.giveMoney(echo);
                }
            }
        };

        thread1.start();
        thread2.start();
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}

```


```
charly正在给echo转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
brickea正在给sandy转钱
charly正在给echo转钱
```

### 临时暂停线程

当前线程，临时暂停，使得其他线程可以有更多的机会占用CPU资源

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

//        Transfer brickeaTsandy = new Transfer(brickea,sandy);
//        brickeaTsandy.start();
//
//        Transfer echoTcharly = new Transfer(echo,charly);
//        echoTcharly.start();

//        Transfer brickeaTsandy = new Transfer(brickea, sandy);
//        new Thread(brickeaTsandy).start();
//
//        Transfer echoTcharly = new Transfer(echo, charly);
//        new Thread(echoTcharly).start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (!brickea.hasMoney()) {
                    brickea.giveMoney(sandy);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Thread.yield();
                while (!charly.hasMoney()) {
                    charly.giveMoney(echo);
                }
            }
        };

        thread1.start();
        thread2.start();
        System.out.println("结束");
    }
}
```

### 守护线程

守护线程的概念是： 当一个进程里，所有的线程都是守护线程的时候，结束当前进程。

就好像一个公司有销售部，生产部这些和业务挂钩的部门。

除此之外，还有后勤，行政等这些支持部门。

如果一家公司销售部，生产部都解散了，那么只剩下后勤和行政，那么这家公司也可以解散了。

守护线程就相当于那些支持部门，如果一个进程只剩下守护线程，那么进程就会自动结束。

守护线程通常会被用来做日志，性能统计等工作。

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

//        Transfer brickeaTsandy = new Transfer(brickea,sandy);
//        brickeaTsandy.start();
//
//        Transfer echoTcharly = new Transfer(echo,charly);
//        echoTcharly.start();

//        Transfer brickeaTsandy = new Transfer(brickea, sandy);
//        new Thread(brickeaTsandy).start();
//
//        Transfer echoTcharly = new Transfer(echo, charly);
//        new Thread(echoTcharly).start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (!brickea.hasMoney()) {
                    brickea.giveMoney(sandy);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Thread.yield();
                while (!charly.hasMoney()) {
                    charly.giveMoney(echo);
                }
            }
        };

//        thread1.setPriority(1);
//        thread2.setPriority(1);
//        thread1.start();
//        thread2.start();
        thread1.setDaemon(true);
        System.out.println("结束");
    }
}

```

```
结束

Process finished with exit code 0
```

## 同步

如果brickea在给sandy转钱的同时，有好几个人同时也给brickea转钱。假设加钱的线程先进入，brickea的余额计算结果变成101，但是如果还没有来得及修改余额减少的线程就来了，那此时计算结果是99，最后就是加钱把结果101赋给变量，减钱的线程把结果赋给变量，最后得到了错误的结果99

### 同步思路

总体解决思路是： 在增加线程访问money期间，其他线程不可以访问money
1. 增加线程获取到money的值，并进行运算
2. 在运算期间，减少线程试图来获取money的值，但是不被允许
3. 增加线程运算结束，并成功修改money的值为101
4. 减少线程，在增加线程做完后，才能访问money的值，即101
5. 减少线程运算，并得到新的值100

### ```synchronized``` 同步对象概念

解决上述问题之前，先理解
```synchronized```关键字的意义

```java
Object someObject =new Object();
synchronized (someObject){
  //此处的代码只有占有了someObject后才可以执行
}
```

**synchronized表示当前线程，独占 对象 someObject  **
当前线程**独占**了对象someObject，如果有**其他线程试图占有**对象someObject，**就会等待**，直到当前线程释放对someObject的占用。  
someObject 又叫同步对象，所有的对象，都可以作为同步对象  
为了达到同步的效果，必须使用同一个同步对象  

**释放同步对象**的方式： synchronized 块自然结束，或者有异常抛出

所有需要修改hp的地方，有要```建立在占有someObject的基础上```。  
而对象 someObject在同一时间，只能被一个线程占有。 间接地，导致同一时间，money只能被一个线程修改。

```java
package JavaDayDayUp.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Person brickea = new Person("brickea");
        Person sandy = new Person("sandy");


        Person echo = new Person("echo");
        Person charly = new Person("charly");

//        Transfer brickeaTsandy = new Transfer(brickea,sandy);
//        brickeaTsandy.start();
//
//        Transfer echoTcharly = new Transfer(echo,charly);
//        echoTcharly.start();

//        Transfer brickeaTsandy = new Transfer(brickea, sandy);
//        new Thread(brickeaTsandy).start();
//
//        Transfer echoTcharly = new Transfer(echo, charly);
//        new Thread(echoTcharly).start();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (brickea) {
                    for (int i = 0; i < 100; i++) {
                        brickea.giveMoney(sandy);
                        System.out.println(brickea);
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (brickea) {
                    for (int i = 0; i < 100; i++) {
                        charly.giveMoney(brickea);
                    }
                }

            }
        };

//        thread1.setPriority(1);
//        thread2.setPriority(1);
        thread1.start();
        thread2.start();
        System.out.println("结束");
    }
}
```

### 在方法上加 ```synchronized```

在sendMoney前，直接加上synchronized ，其所对应的同步对象，就是this  
外部线程访问brickea的方法，就不需要额外使用synchronized 了

```java
package JavaDayDayUp.ThreadTest;

public class Person {
    private int money;
    private String name;

    public Person(String name) {
        this.name = name;
        this.money = 100;
    }
    
    // 同步当前对象
    public void giveMoney(Person p) {
        synchronized (this) {
            System.out.println(this.name + "正在给" + p.getName() + "转钱");
            if (!this.hasMoney()) {
                this.money -= 1;
                p.setMoney(p.getMoney() + 1);
            }
        }
    }

    // 或者在方法前加 synchronized
    public void synchronized giveMoney(Person p) {
        System.out.println(this.name + "正在给" + p.getName() + "转钱");
        if (!this.hasMoney()) {
            this.money -= 1;
            p.setMoney(p.getMoney() + 1);
        }
        
    }
}
```

如果一个类，其方法都是有```synchronized```修饰的，那么该类就叫做线程安全的类

同一时间，只有一个线程能够进入 这种类的一个实例 的去修改数据，进而保证了这个实例中的数据的安全(不会同时被多线程修改而变成脏数据)

比如```StringBuffer```和```StringBuilder```的区别
```StringBuffer```的方法都是有synchronized修饰的，```StringBuffer```就叫做线程安全的类
而```StringBuilder```就不是线程安全的类

## 线程安全的类 - 面试

### HashMap 和 Hashtable的区别

HashMap和Hashtable都实现了Map接口，都是键值对保存数据的方式

* 区别1：
HashMap可以存放 null  
Hashtable不能存放null
* 区别2：
HashMap不是线程安全的类  
Hashtable是线程安全的类

### StringBuffer和StringBuilder的区别

StringBuffer 是线程安全的  
StringBuilder 是非线程安全的

所以当进行大量字符串拼接操作的时候，如果是单线程就用StringBuilder会更快些，如果是多线程，就需要用StringBuffer 保证数据的安全性

### ArrayList和Vector的区别

ArrayList类的声明：
```java
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

Vector类的声明：
```java
public class Vector<E>    extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

一模一样的~

他们的区别也在于，Vector是线程安全的类，而ArrayList是非线程安全的。

### 把非线程安全的集合转换为线程安全

ArrayList是非线程安全的，换句话说，多个线程可以同时进入一个ArrayList对象的add方法

借助Collections.synchronizedList，可以把ArrayList转换为线程安全的List。

与此类似的，还有HashSet,LinkedList,HashMap等等非线程安全的类，都通过工具类Collections转换为线程安全的

## 死锁

![](https://stepimagewm.how2j.cn/794.png)

## 线程交互

线程之间有交互通知的需求，考虑如下情况：  
有两个线程，处理同一个英雄。  
一个加血，一个减血。  

减血的线程，发现血量=1，就停止减血，直到加血的线程为英雄加了血，才可以继续减血

### 直观的解决方案

故意设计减血线程频率更高，盖伦的血量迟早会到达1  
减血线程中使用while循环判断是否是1，如果是1就不停的循环,直到加血线程回复了血量  
这是不好的解决方式，因为会大量占用CPU,拖慢性能

### 使用wait 和 notify进行交互

在Hero类中：hurt()减血方法：当hp=1的时候，执行this.wait().  
this.wait()表示 让占有this的线程等待，并临时释放占有  
进入hurt方法的线程必然是减血线程，this.wait()会让减血线程临时释放对this的占有。 这样加血线程，就有机会进入recover()加血方法了。


recover() 加血方法：增加了血量，执行this.notify();  
this.notify() 表示通知那些等待在this的线程，可以苏醒过来了。 等待在this的线程，恰恰就是减血线程。 一旦recover()结束， 加血线程释放了this，减血线程，就可以重新占有this，并执行后面的减血工作。

![](https://stepimagewm.how2j.cn/796.png)

### 注意 wait notify notifyAll 的位置

留意wait()和notify() 这两个方法是什么对象上的？

```java
public synchronized void hurt() {
。。。
this.wait();
。。。
}
```

```java
public synchronized void recover() {
。。。
this.notify();
}
```


这里需要强调的是，wait方法和notify方法，并不是Thread线程上的方法，它们是Object上的方法。

因为所有的Object都可以被用来作为同步对象，所以准确的讲，wait和notify是同步对象上的方法。

wait()的意思是： 让占用了这个同步对象的线程，临时释放当前的占用，并且等待。 所以调用wait是有前提条件的，一定是在synchronized块里，否则就会出错。

notify() 的意思是，通知一个等待在这个同步对象上的线程，你可以苏醒过来了，有机会重新占用当前对象了。

notifyAll() 的意思是，通知所有的等待在这个同步对象上的线程，你们可以苏醒过来了，有机会重新占用当前对象了。

## 线程池

每一个线程的启动和结束都是比较消耗时间和占用资源的。

如果在系统中用到了很多的线程，大量的启动和结束动作会导致系统的性能变卡，响应变慢。

为了解决这个问题，引入线程池这种设计思想。

线程池的模式很像生产者消费者模式，消费的对象是一个一个的能够运行的任务

### 线程池的设计思路

线程池的思路和生产者消费者模型是很接近的。
1. 准备一个任务容器
2. 一次性启动10个 消费者线程
3. 刚开始任务容器是空的，所以线程都wait在上面。
4. 直到一个外部线程往这个任务容器中扔了一个“任务”，就会有一个消费者线程被唤醒notify
5. 这个消费者线程取出“任务”，并且执行这个任务，执行完毕后，继续等待下一次任务的到来。
6. 如果短时间内，有较多的任务加入，那么就会有多个线程被唤醒，去执行这些任务。

在整个过程中，都不需要创建新的线程，而是循环使用这些已经存在的线程

![](https://stepimagewm.how2j.cn/2600.png)

```java

package JavaDayDayUp.ThreadTest;

import java.util.LinkedList;

public class ThreadPoolTest {
    // 任务容器
    LinkedList<Runnable> tasks = new LinkedList<>();

    public ThreadPoolTest() {
        // 试图消费任务的线程
        for (int i = 0; i < 10; i++) {
            // 启动10个任务消费者线程
            CustomerThread customerThread = new CustomerThread("消费者" + i);
            customerThread.start();
        }
    }

    public void addTask(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            // 唤醒等待的任务消费者线程
            tasks.notifyAll();
        }
    }

    class CustomerThread extends Thread {
        String name;

        CustomerThread(String name) {
            this.name = name;
        }

        Runnable task;

        @Override
        public void run() {
            System.out.println(this.name + " 消费者进程启动！");
            while (true) {
                // 等待任务池的任务
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    // 允许添加任务的线程可以继续添加任务
                    tasks.notifyAll();
                }
                System.out.println(this.name + " 取到任务");
                task.run();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolTest test = new ThreadPoolTest();

        for (int i = 0; i < 100; i++) {
            test.addTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务");
                    //任务可能是打印一句话
                    //可能是访问文件
                    //可能是做排序
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}

```

### 使用Java内置线程池

java提供自带的线程池，而不需要自己去开发一个自定义线程池了。

线程池类ThreadPoolExecutor在包java.util.concurrent下

```java
ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
```


* 第一个参数10 表示这个线程池初始化了10个线程在里面工作
* 第二个参数15 表示如果10个线程不够用了，就会自动增加到最多15个线程
* 第三个参数60 结合第四个参数TimeUnit.SECONDS，表示经过60秒，多出来的线程还没有接到活儿，就会回收，最后保持池子里就10个
* 第四个参数TimeUnit.SECONDS 如上
* 第五个参数 new LinkedBlockingQueue() 用来放任务的集合

execute方法用于添加新的任务

## 锁 lock

首先回忆一下 synchronized 同步对象的方式

当一个线程占用 synchronized 同步对象，其他线程就不能占用了，直到释放这个同步对象为止

![](https://stepimagewm.how2j.cn/2610.png)

### 使用Lock对象实现同步效果

Lock是一个接口，为了使用一个Lock对象，需要用到
```java
Lock lock = new ReentrantLock();
```

与 synchronized (someObject) 类似的，lock()方法，表示当前线程占用lock对象，一旦占用，其他线程就不能占用了。  
与 synchronized 不同的是，一旦synchronized 块结束，就会自动释放对someObject的占用。 lock却必须调用unlock方法进行手动释放，为了保证释放的执行，往往会把unlock() 放在finally中进行。

```java
package JavaDayDayUp.ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    log("线程启动寻找lock");
                    log("试图占有lock");

                    lock.lock(); // 如果锁不可用，则线程一直处于wait的状态

                    log("占有成功，开始任务");
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    System.out.println("释放lock");
                    lock.unlock();
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    log("线程启动寻找lock");
                    log("试图占有lock");

                    lock.lock(); // 如果锁不可用，则线程一直处于wait的状态

                    log("占有成功，开始任务");
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    System.out.println("释放lock");
                    lock.unlock();
                }
            }
        };

        thread2.start();
        thread1.start();


    }
}
```

```
20:07:52 Thread-0 线程启动寻找lock 
20:07:52 Thread-1 线程启动寻找lock 
20:07:52 Thread-0 试图占有lock 
20:07:52 Thread-1 试图占有lock 
20:07:52 Thread-0 占有成功，开始任务 
释放lock
20:07:57 Thread-1 占有成功，开始任务 
释放lock
```

### trylock 方法

synchronized 是不占用到手不罢休的，会一直试图占用下去。  
与 synchronized 的钻牛角尖不一样，Lock接口还提供了一个trylock方法。  
trylock会在指定时间范围内试图占用，占成功了，就啪啪啪。 如果时间到了，还占用不成功，扭头就走~

注意： 因为使用trylock有可能成功，有可能失败，所以后面unlock释放锁的时候，需要判断是否占用成功了，如果没占用成功也unlock,就会抛出异常

```java
package JavaDayDayUp.ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                boolean locked=false;
                try {
                    locked = lock.tryLock(1,TimeUnit.SECONDS);
                    if(locked){
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(locked){
                        System.out.println("释放lock");
                        lock.unlock();
                    }

                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                boolean locked=false;
                try {
                    locked = lock.tryLock(1,TimeUnit.SECONDS);
                    if(locked){
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(locked){
                        System.out.println("释放lock");
                        lock.unlock();
                    }

                }
            }
        };

        thread2.start();
        thread1.start();


    }
}

```

```
20:12:42 Thread-0 占有对象：lock 
20:12:42 Thread-0 进行5秒的业务操作 
20:12:43 Thread-1 经过1秒钟的努力，还没有占有对象，放弃占有 
释放lock

Process finished with exit code 0
```

### Lock的通讯

使用synchronized方式进行线程交互，用到的是同步对象的wait,notify和notifyAll方法

Lock也提供了类似的解决办法，首先通过lock对象得到一个Condition对象，然后分别调用这个Condition对象的：await, signal,signalAll 方法

注意： 不是Condition对象的wait,nofity,notifyAll方法,是await,signal,signalAll

```java
package JavaDayDayUp.ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                boolean locked=false;
                try {
                    locked = lock.tryLock(1,TimeUnit.SECONDS);
                    if(locked){
                        log("占有对象：lock");
                        condition.signal();
                        condition.await();
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(locked){
                        System.out.println("释放lock");
                        lock.unlock();
                    }

                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                boolean locked=false;
                try {
                    locked = lock.tryLock(1,TimeUnit.SECONDS);
                    if(locked){
                        condition.await();
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                        condition.signal();
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(locked){
                        System.out.println("释放lock");
                        lock.unlock();
                    }

                }
            }
        };

        thread2.start();
        thread1.start();


    }
}

```

```
21:03:32 Thread-0 占有对象：lock 
21:03:32 Thread-1 占有对象：lock 
21:03:32 Thread-1 进行5秒的业务操作 
释放lock
21:03:37 Thread-0 进行5秒的业务操作 
释放lock
```

### 总结Lock和synchronized的区别总结

1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现，Lock是代码层面的实现。

2. Lock可以选择性的获取锁，如果一段时间获取不到，可以放弃。synchronized不行，会一根筋一直获取下去。 借助Lock的这个特性，就能够规避死锁，synchronized必须通过谨慎和良好的设计，才能减少死锁的发生。

3. synchronized在发生异常和同步块结束的时候，会自动释放锁。而Lock必须手动释放， 所以如果忘记了释放锁，一样会造成死锁。

## 原子操作

所谓的原子性操作即不可中断的操作，比如赋值操作
```
int i = 5;
```

原子性操作本身是线程安全的  
但是 i++ 这个行为，事实上是有3个原子性操作组成的。  
步骤 1. 取 i 的值  
步骤 2. i + 1  
步骤 3. 把新的值赋予i  
这三个步骤，每一步都是一个原子操作，但是合在一起，就不是原子操作。就不是线程安全的。  
换句话说，一个线程在步骤1 取i 的值结束后，还没有来得及进行步骤2，另一个线程也可以取 i的值了。  
这也是分析同步问题产生的原因 中的原理。  
i++ ，i--， i = i+1 这些都是非原子性操作。 
只有int i = 1,这个赋值操作是原子性的。  

### AtomicInteger

JDK6 以后，新增加了一个包java.util.concurrent.atomic，里面有各种原子类，比如AtomicInteger。  
而AtomicInteger提供了各种自增，自减等方法，这些方法都是原子性的。 换句话说，自增方法 incrementAndGet 是线程安全的，同一个时间，只有一个线程可以调用这个方法。  