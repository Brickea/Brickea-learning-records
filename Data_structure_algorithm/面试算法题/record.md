# 没有出现在题库的面试算法题<!-- omit in toc -->

- [字节跳动](#字节跳动)
  - [两个线程交替打印奇数偶数](#两个线程交替打印奇数偶数)

## 字节跳动

### 两个线程交替打印奇数偶数

```notify```，```wait```方法

```java
package JavaDayDayUp.ThreadTest;

/*
 * 使用两个线程交替打印奇数和偶数
 */


public class OddAndEven {

    public static void main(String[] args) {
        Object l = new Object();

        new Thread(new Runnable() {
            int count = 0;
            // 偶数线程
            @Override
            public void run() {
                while (true) {
                    synchronized (l) {
                        System.out.println("偶数线程" + Thread.currentThread().getName());
                        System.out.println(count);
                        count += 2;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        l.notifyAll();
                        try {
                            l.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            int count = 1;

            // 奇数线程
            @Override
            public void run() {
                while (true) {
                    synchronized (l) {
                        System.out.println("奇数线程" + Thread.currentThread().getName());
                        System.out.println(count);
                        count += 2;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        l.notifyAll();
                        try {
                            l.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}

```

```
偶数线程Thread-0
0
奇数线程Thread-1
1
偶数线程Thread-0
2
奇数线程Thread-1
3
偶数线程Thread-0
4
奇数线程Thread-1
5
偶数线程Thread-0
6
奇数线程Thread-1
7
偶数线程Thread-0
8
奇数线程Thread-1
9
```