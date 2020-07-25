# Comparable 和 Comparator 的区别 <!-- omit in toc -->

## 概括

Comparable和Comparator都是用来实现集合中元素的比较、排序的。

Comparable是在集合内部定义的方法实现的排序，位于java.util下。

Comparator是在集合外部实现的排序，位于java.lang下。

Comparable是一个对象本身就已经支持自比较所需要实现的接口，如String、Integer自己就实现了
Comparable接口，可完成比较大小操作。自定义类要在加入list容器中后能够排序，也可以实现
Comparable接口，在用Collections类的sort方法排序时若不指定Comparator，那就以自然顺序排序。所谓自然顺序就是实现Comparable接口设定的排序方式。

Comparator是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足要求时，可写一个比较器来完成两个对象之间大小的比较。Comparator体现了一种策略模式(strategy design pattern)，就是不改变对象自身，而用一个策略对象(strategy object)来改变它的行为。

总而言之Comparable是自已完成比较，Comparator是外部程序实现比较。

## Comparator 的使用

在使用```Collections.sort```的时候作为一种排序策略传入

```java
package JavaDayDayUp.CollectionPractice;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(100);
        Person p2 = new Person(200);
        Person p3 = new Person(300);

        List<Person> test = new ArrayList<>();

        test.add(p2);
        test.add(p3);
        test.add(p1);

        Collections.sort(test, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getMoney()-o2.getMoney();
            }
        });

        for(Person item : test){
            System.out.println(item.getMoney());
        }

    }
}

```

## Comparable 的使用

```java

package JavaDayDayUp.CollectionPractice;

public class Person implements Comparable<Person>{
    private int money;

    public Person(){
        this.money = 0;
    }

    public Person(int money){
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int compareTo(Person p) {
        return this.getMoney() - p.getMoney();
    }
}


package JavaDayDayUp.CollectionPractice;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(100);
        Person p2 = new Person(200);
        Person p3 = new Person(300);

        List<Person> test = new ArrayList<>();

        test.add(p2);
        test.add(p3);
        test.add(p1);

        Collections.sort(test);

        for(Person item : test){
            System.out.println(item.getMoney());
        }

    }
}

```

## 注意

两者在使用的时候注意泛型的传入