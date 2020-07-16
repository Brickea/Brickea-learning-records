# Lambda 表达式 <!-- omit in toc -->

- [Lambda的使用](#lambda的使用)
  - [普通方法](#普通方法)
  - [匿名类](#匿名类)
  - [Lambda 风格](#lambda-风格)
    - [过程](#过程)
  - [Lambda只支持函数式接口](#lambda只支持函数式接口)
- [Lambda 方法引用](#lambda-方法引用)
  - [引用静态方法](#引用静态方法)
  - [引用对象方法](#引用对象方法)
  - [引用容器中的对象的方法](#引用容器中的对象的方法)
  - [引用构造器](#引用构造器)
- [聚合操作](#聚合操作)
  - [Stream 和管道](#stream-和管道)
  - [管道源](#管道源)
  - [中间操作](#中间操作)
  - [结束操作](#结束操作)

## Lambda的使用

**情景**：找出Person钱在50以内的人并打印姓名

### 普通方法

```java
package JavaDayDayUp.lambdaTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person {
    String name;
    int money;
    public Person(String name){
        this.name = name;
        this.money = new Random().nextInt(100);
    }

    private static void filter(List<Person> list){
        for(Person item : list){
            if(item.money<50){
                System.out.println(item);
            }
        }
    }

    @Override
    public String toString() {
        return this.name+" "+this.money;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Person("name"+new Random().nextInt(100)));
        }
        filter(list);

    }
}

```

```
name52 36
name47 19
name1 48
name14 28
name1 38
name48 22
name19 33
```

### 匿名类

```java
package JavaDayDayUp.lambdaTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person {
    String name;
    int money;
    public Person(String name){
        this.name = name;
        this.money = new Random().nextInt(100);
    }

    private static void filter(List<Person> list){
        for(Person item : list){
            if(item.money<50){
                System.out.println(item);
            }
        }
    }

    @Override
    public String toString() {
        return this.name+" "+this.money;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Person("name"+new Random().nextInt(100)));
        }
//        filter(list);
        CheckPersonMoney checkOut = new CheckPersonMoney() {
            @Override
            public void check(List<Person> list) {
                for(Person item : list){
                    if(item.money<50){
                        System.out.println(item);
                    }
                }
            }
        };
        checkOut.check(list);

    }
}

```

```
name69 10
name89 36
name69 30
name52 44
```

### Lambda 风格

```java
package JavaDayDayUp.lambdaTest;

import java.util.List;

public interface CheckPersonMoney {
//    public void check(List<Person> list);
    public boolean test(Person p);
}


package JavaDayDayUp.lambdaTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person {
    String name;
    int money;
    public Person(String name){
        this.name = name;
        this.money = new Random().nextInt(100);
    }

    private static void filter(List<Person> list, CheckPersonMoney check){
        for(Person item : list){
            if(check.test(item)){
                System.out.println(item);
            }
        }
    }

    @Override
    public String toString() {
        return this.name+" "+this.money;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Person("name"+new Random().nextInt(100)));
        }
//        filter(list);
//        CheckPersonMoney checkOut = new CheckPersonMoney() {
//            @Override
//            public void check(List<Person> list) {
//                for(Person item : list){
//                    if(item.money<50){
//                        System.out.println(item);
//                    }
//                }
//            }
//        };
//        checkOut.check(list);
        filter(list,p-> p.money>50);

    }
}

```

```
name53 78
name41 91
name2 95
name97 61

Process finished with exit code 0
```

#### 过程

Lambda可以看成是从匿名类一点一点演变过来的

1. 匿名类的写法
```java
CheckoutPersonMoney checkOut = new CheckoutPersonMoney(){
    public boolean check(Person p){
        return (p.money>50)
    }
}
```
2. 去掉外面的壳子，保留方法参数和方法体，参数和方法体之间加上符号```->```
```java
CheckoutPersonMoney checkOut = (Person p) -> {
    return (p.money>50)
}
```
3. 去掉return和大括号
```java
CheckoutPersonMoney checkOut = (Person p) -> (p.money>50)
```
4. 把 参数类型和圆括号去掉(只有一个参数的时候，才可以去掉圆括号)
```java
CheckoutPersonMoney checkOut = p-> p.money>50
```

与匿名类 概念相比较，
Lambda 其实就是匿名方法，这是一种把方法作为参数进行传递的编程思想。

虽然代码是这么写
```java
CheckoutPersonMoney checkOut = p-> p.money>50
```

但是，Java会在背后，悄悄的，把这些都还原成匿名类方式。
引入Lambda表达式，会使得代码更加紧凑，而不是各种接口和匿名类到处飞。

### Lambda只支持函数式接口

Lambda表达式只支持函数式接口  也就是只有一个抽象方法的接口

可以使用@FunctionalInterface标注函数式接口，在编译时提前发现错误。

例子：

```java
package test;
@FunctionalInterface
public interface IParmas1<A> {  
    void call(A a); 
    void call2(A a); 
}  
```

编译时会报错：

```
Invalid '@FunctionalInterface' annotation; IParmas1<A> is not a functional interface
```

就是因为使用了这个注解 接口只能写一个抽象方法

## Lambda 方法引用

### 引用静态方法

在Person中添加静态方法

```java
private static boolean testMoney(Person p){
    return p.money<50;
}
```

可以用lambda调用

```java
filter(list,p->Person.testMoney(p));
// or
filter(list, Person::testMoney);
```

```java
package JavaDayDayUp.lambdaTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person {
    String name;
    int money;
    public Person(String name){
        this.name = name;
        this.money = new Random().nextInt(100);
    }

    private static void filter(List<Person> list, CheckPersonMoney check){
        for(Person item : list){
            if(check.test(item)){
                System.out.println(item);
            }
        }
    }

    private static boolean testMoney(Person p){
        return p.money<50;
    }

    @Override
    public String toString() {
        return this.name+" "+this.money;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Person("name"+new Random().nextInt(100)));
        }
        filter(list,p->Person.testMoney(p));

    }
}
```

### 引用对象方法

与引用静态方法很类似，只是传递方法的时候，需要一个对象的存在

```java
Person test = new Person("test");
filter(list,test::checkMoney);
```

```java
package JavaDayDayUp.lambdaTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Person {
    String name;
    int money;
    public Person(String name){
        this.name = name;
        this.money = new Random().nextInt(100);
    }

    private static void filter(List<Person> list, CheckPersonMoney check){
        for(Person item : list){
            if(check.test(item)){
                System.out.println(item);
            }
        }
    }

    private static boolean testMoney(Person p){
        return p.money<50;
    }

    private  boolean checkMoney(Person p){
        return p.money<50;
    }

    @Override
    public String toString() {
        return this.name+" "+this.money;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Person("name"+new Random().nextInt(100)));
        }

        Person test = new Person("test");
        filter(list,test::checkMoney);

    }
}

```

```
name88 35
name27 35
name11 9
name56 23
name97 18
name87 31
name38 46
```

### 引用容器中的对象的方法

类似与引用静态方法

### 引用构造器

有的接口中的方法会返回一个对象，比如java.util.function.Supplier提供
了一个get方法，返回一个对象。
```java
public interface Supplier<T> {
    T get();
}
```

设计一个方法，参数是这个接口

```java
public static List getList(Supplier<List> s){
  return s.get();
}
```

为了调用这个方法，有3种方式
第一种匿名类：

```java
Supplier<List> s = new Supplier<List>() {
	public List get() {
		return new ArrayList();
	}
};
List list1 = getList(s);
```


第二种：Lambda表达式

```java
List list2 = getList(()->new ArrayList());
```


第三种：引用构造器

```java
List list3 = getList(ArrayList::new);
```

## 聚合操作

传统方式
```java
for (Hero h : heros) {
   if (h.hp > 100 && h.damage < 50)
      System.out.println(h.name);
}
```

使用聚合操作方式，画风就发生了变化

```java
heros
	.stream()
	.filter(h -> h.hp > 100 && h.damage < 50)
	.forEach(h -> System.out.println(h.name));
```

### Stream 和管道

要了解聚合操作，首先要建立Stream和管道的概念

Stream 和Collection结构化的数据不一样，Stream是一系列的元素，就像是生产线上的罐头一样，一串串的出来。

管道指的是一系列的聚合操作。

管道又分3个部分
* **管道源**：在这个例子里，源是一个List
* **中间操作**： 每个中间操作，又会返回一个Stream，比如.filter()又返回一个Stream, 中间操作是“懒”操作，并不会真正进行遍历。
* **结束操作**：当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。 结束操作不会返回Stream，但是会返回int、float、String、 Collection或者像forEach，什么都不返回, 结束操作才进行真正的遍历行为，在遍历的时候，才会去进行中间操作的相关判断

注： 这个Stream和I/O章节的InputStream,OutputStream是不一样的概念。

### 管道源

把Collection切换成管道源很简单，调用stream()就行了。

```java
heros.stream()
```

但是数组却没有stream()方法，需要使用
```java
Arrays.stream(hs)
```

或者
```
Stream.of(hs)
```

### 中间操作

每个中间操作，又会返回一个Stream，比如.filter()又返回一个Stream, 中间操作是“懒”操作，并不会真正进行遍历。
中间操作比较多，主要分两类
对元素进行筛选 和 转换为其他形式的流
对元素进行筛选：
* filter 匹配
* distinct 去除重复(根据equals判断)
* sorted 自然排序
* sorted(Comparator<T>) 指定排序
* limit 保留
* skip 忽略

转换为其他形式的流
* mapToDouble 转换为double的流
* map 转换为任意类型的流

### 结束操作

当进行结束操作后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。 结束操作不会返回Stream，但是会返回int、float、String、 Collection或者像forEach，什么都不返回,。

结束操作才真正进行遍历行为，前面的中间操作也在这个时候，才真正的执行。
常见结束操作如下：

* forEach() 遍历每个元素
* toArray() 转换为数组
* min(Comparator<T>) 取最小的元素
* max(Comparator<T>) 取最大的元素
* count() 总数
* findFirst() 第一个元素