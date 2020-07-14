# 反射机制<!-- omit in toc -->

- [什么是类对象](#什么是类对象)
- [获取类对象](#获取类对象)
- [获取类对象的时候，会导致类属性被初始化](#获取类对象的时候会导致类属性被初始化)
- [通过反射机制创建对象](#通过反射机制创建对象)
- [通过反射机制修改对象的属性](#通过反射机制修改对象的属性)
  - [```getField```和```getDeclaredField```的区别](#getfield和getdeclaredfield的区别)
- [通过反射机制调用方法](#通过反射机制调用方法)

## 什么是类对象

先理解一下对象之间的区别

```brickea``` 和 ```zixiao``` 都是 ```Person```对象，他们的区别在于，各自有不同的名称，各自的类属性的值不一样

然后说一下类之间的区别

```Person```和```Item```都是类，他们的区别在于有**不同的方法**和**不同的属性**

类对象就是用来描述这种类，都有什么属性什么方法的

## 获取类对象

有三种方式

* ```Class.forName```
* ```Person.class```
* ```new Person().getClass()```

在一个JVM中，一种类，只会有一个类对象存在，所以以上三种方式取出来的类对象都是一样的

> 准确地讲是一个```ClassLoader```下，一种类，只会有一个类对象存在，通常一个JVM下，只会有一个```ClassLoader```

```java
package JavaDayDayUp.Reflect;

public class Person {

    private String name;
    public Person(String name){
        this.name = name;
    }
    public static void main(String[] args) {
        Person test = new Person("zixiao");
        System.out.println(test.getClass());
        System.out.println(Person.class);
        try{
            System.out.println(Class.forName("JavaDayDayUp.Reflect.Person"));
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

```

```
class JavaDayDayUp.Reflect.Person
class JavaDayDayUp.Reflect.Person
class JavaDayDayUp.Reflect.Person

Process finished with exit code 0
```

## 获取类对象的时候，会导致类属性被初始化

为```Person```增加一个静态属性，并且在静态初始化块里面进行初始化

```java
static String copyright;
static {
    System.out.println("初始化 copyright");
    copyright="版权";
}
```

无论什么途径获取类对象，都会导致静态属性被初始化，而且只会执行一次。（除了直接使用 Class c = Hero.class 这种方式，这种方式不会导致静态属性被初始化）

## 通过反射机制创建对象

与传统的通过new 来获取对象的方式不同

反射机制，会先拿到```Person```的“类对象”,然后通过类对象获取“构造器对象”

再通过构造器对象创建一个对象

关键方法
* 先获取```Class```，使用```Class.forName(className)```或者```Person.class```
* 获取构造器```Constructor```,使用```pClass.getConstructor()```
* 通过构造器实例化```Person p = (Person) c.newInstance()```

```java
package JavaDayDayUp.Reflect;

import java.lang.reflect.Constructor;

public class Person {

    private String name;
    static String copyright;
    static {
        System.out.println("初始化copyright");
        copyright="版权";
    }
    public Person(){
        this.name="test";
    }
    public Person(String name){
        this.name = name;
    }
    public static void main(String[] args) {
        Class personClass = Person.class;
        try{
            Constructor c = personClass.getConstructor();
            Person test = (Person) c.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
```

```
初始化copyright

Process finished with exit code 0

```

## 通过反射机制修改对象的属性

为了访问属性，把name修改为public。

对于private修饰的成员，需要使用setAccessible(true)才能访问和修改。不在此知识点讨论。

```java
package JavaDayDayUp.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Person {

    private String name;
    static String copyright;
    static {
        System.out.println("初始化copyright");
        copyright="版权";
    }
    public Person(){
        this.name="test";
    }
    public Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {
        Person test = new Person("Zixiao");
        try{
            Field name = test.getClass().getDeclaredField("name");
            name.set(test,"brickea");
            System.out.println(test);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
```
```
初始化copyright
brickea

Process finished with exit code 0
```
### ```getField```和```getDeclaredField```的区别
这两个方法都是用于获取字段

```getField``` 只能获取```public```的，包括从父类继承来的字段。

```getDeclaredField``` 可以获取本类所有的字段，包括```private```的，但是不能获取继承来的字段。 (注： 这里只能获取到```private```的字段，但并不能访问该```private```字段的值,除非加上```setAccessible(true)```)

## 通过反射机制调用方法

```java
package JavaDayDayUp.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person {

    private String name;
    static String copyright;
    static {
        System.out.println("初始化copyright");
        copyright="版权";
    }
    public Person(){
        this.name="test";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {

        Person test = new Person("zixiao");
        try{
            // 获取这个名字叫做setName，参数类型是String的方法
            Method m = test.getClass().getMethod("setName", String.class);
            // 针对test对象使用该方法
            m.invoke(test,"Brickea");
            System.out.println(test);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
```

```
初始化copyright
Brickea

Process finished with exit code 0
```