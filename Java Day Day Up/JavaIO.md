# Java IO<!--omit in toc -->

- [Java IO](#java-io)
  - [IO 文件对象](#io-文件对象)
    - [创建一个文件对象](#创建一个文件对象)
    - [常用方法](#常用方法)
  - [Stream 流](#stream-流)
    - [文件输入流](#文件输入流)
    - [字节流](#字节流)
      - [读取和写入文件](#读取和写入文件)
    - [在 finally 中关闭流](#在-finally-中关闭流)
    - [把流定义在try()里](#把流定义在try里)
    - [字符流](#字符流)
    - [缓存流](#缓存流)
      - [```flush``` 直接写入](#flush-直接写入)
    - [数据流](#数据流)
    - [对象流](#对象流)
      - [序列化一个对象](#序列化一个对象)
  - [流之间的关系](#流之间的关系)

## IO 文件对象

文件对象都用```File```来表示

### 创建一个文件对象

使用绝对路径或者相对路径均可

```java
package FileTest;

import java.io.File;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class FIleTest {
    public static void main(String[] args) {
        // 相对路径
        File Brickea = new File("Brickea.txt");
        System.out.println("绝对路径"+Brickea.getAbsolutePath());
    }
}

```

### 常用方法

```java
package FileTest;

import java.io.File;
import java.util.Date;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class FIleTest {
    public static void main(String[] args) {
        // 相对路径
        File Brickea = new File("./src/FileTest/Brickea.txt");
        System.out.println("绝对路径"+Brickea.getAbsolutePath());

        // 常用方法

        // 是否存在
        System.out.println(Brickea.exists());

        // 是否是文件夹
        System.out.println(Brickea.isDirectory());

        // 是否是文件
        System.out.println(Brickea.isFile());

        // 文件长度
        System.out.println(Brickea.length());

        // 获取文件最后修改时间
        long timeModify = Brickea.lastModified();
        Date dateTime = new Date(timeModify);
        System.out.println(dateTime);

        // 文件重命名
        File Sandy = new File("./src/FileTest/Sandy.txt");
        Brickea.renameTo(Sandy);

        System.out.println(Brickea.getAbsolutePath());
    }
}
```

```
绝对路径/Users/brickeawang/Code/java_practice/./src/FileTest/Brickea.txt
true
false
true
7
Tue Jul 14 20:04:31 EDT 2020
/Users/brickeawang/Code/java_practice/./src/FileTest/Brickea.txt
```

```java
package FileTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class FIleTest {
    public static void main(String[] args) throws IOException {
        // 相对路径
        File Brickea = new File("src/FileTest/Brickea.txt");

        // 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
        Brickea.list();

        // 以文件数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
        File[]fs= Brickea.listFiles();

        // 以字符串形式返回获取所在文件夹
        Brickea.getParent();

        // 以文件形式返回获取所在文件夹
        Brickea.getParentFile();
        // 创建文件夹，如果父文件夹skin不存在，创建就无效
        Brickea.mkdir();

        // 创建文件夹，如果父文件夹skin不存在，就会创建父文件夹
        Brickea.mkdirs();

        // 创建一个空文件,如果父文件夹skin不存在，就会抛出异常
        Brickea.createNewFile();
        // 所以创建一个空文件之前，通常都会创建父目录
        Brickea.getParentFile().mkdirs();

        // 列出所有的盘符c: d: e: 等等
        Brickea.listRoots();

        // 刪除文件
        Brickea.delete();

        // JVM结束的时候，刪除文件，常用于临时文件的删除
        Brickea.deleteOnExit();
    }
}

```

## Stream 流

流就是一系列的数据。不同介质之间的数据交互，Java就是用流来实现

数据来源可是文件，数据库，网络

### 文件输入流

```Java
package FileTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class StreamTest {
    public static void main(String[] args) {
        try{
            File Brickea = new File("src/FileTest/Brickea.txt");
            // 创建基于文件对象的输入流
            FileInputStream inputStream = new FileInputStream(Brickea);
            // 创建输入流
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
```

### 字节流

用于以字节的形式读取和写入
* ```InputStream```输入流
* ```OutputStream```输出流

#### 读取和写入文件


```java
package FileTest;

import java.io.*;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class StreamTest {
    public static void main(String[] args) {
        try{
            // 创建基于文件对象的输入流
            File Brickea = new File("src/FileTest/Brickea.txt");
            // 创建输入流
            FileInputStream inputStream = new FileInputStream(Brickea);
            // 创建字节数组，长度为文件长度
            byte[] inputByte = new byte[(int)Brickea.length()];
            // 以字节流的形式读取文件内容
            inputStream.read(inputByte);
            // 输出结果
            for(byte b : inputByte){
                System.out.println(b);
            }
            // 使用结束，关闭流
            inputStream.close();

            // 创建基于文件对象的输出流
            File Sandy = new File("src/FileTest/Sandy.txt");
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(Sandy);
            // 创建输出字节数组 X,Y
            byte[] outputByte = {88, 89};
            // 写入文件
            outputStream.write(outputByte);
            // 关闭流
            outputStream.close();


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

```

### 在 finally 中关闭流

在try的作用域里关闭文件输入流，在前面的示例中都是使用这种方式，这样做有一个弊端；

如果文件不存在，或者读取的时候出现问题而抛出异常，那么就不会执行这一行关闭流的代码，存在巨大的资源占用隐患。 不推荐使用

应该在finally中关闭流

```java
package FileTest;

import java.io.*;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class StreamTest {
    public static void main(String[] args) {
        // 创建基于文件对象的输入流
        File Brickea = new File("src/FileTest/Brickea.txt");
        // 创建输入流
        FileInputStream inputStream = null;
        // 创建基于文件对象的输出流
        File Sandy = new File("src/FileTest/Sandy.txt");
        // 创建输出流
        FileOutputStream outputStream = null;
        try {

            inputStream = new FileInputStream(Brickea);
            // 创建字节数组，长度为文件长度
            byte[] inputByte = new byte[(int) Brickea.length()];
            // 以字节流的形式读取文件内容
            inputStream.read(inputByte);
            // 输出结果
            for (byte b : inputByte) {
                System.out.println(b);
            }

            outputStream = new FileOutputStream(Sandy);
            // 创建输出字节数组 X,Y
            byte[] outputByte = {88, 89};
            // 写入文件
            outputStream.write(outputByte);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                // 使用结束，关闭流
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                // 关闭流
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

```

### 把流定义在try()里

try,catch或者finally结束的时候，会自动关闭

这种编写代码的方式叫做 try-with-resources， 这是从JDK7开始支持的技术

所有的流，都实现了一个接口叫做 AutoCloseable，任何类实现了这个接口，都可以在try()中进行实例化。 并且在try, catch, finally结束的时候自动关闭，回收相关资源。

```java
package stream;
  
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
  
public class TestStream {
  
    public static void main(String[] args) {
        File f = new File("src/FileTest/Brickea.txt");
  
        //把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
        try (FileInputStream fis = new FileInputStream(f)) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
}
```

### 字符流

专门用于字符的形式读取和写入数据
* ```Reader```字符输入流
* ```Writer```字符输出流

```java
package FileTest;

import java.io.*;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class FileReaderTest {
    public static void main(String[] args) {

        // 字符流读入
        File brickea = new File("src/FileTest/Brickea.txt");
        try(FileReader inputChar = new FileReader(brickea)){
            char[] inputChars = new char[(int) brickea.length()];
            inputChar.read(inputChars);
            for(char c:inputChars){
                System.out.println(c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // 字符流输出
        File sandy = new File("src/FileTest/Sandy.txt");
        try(FileWriter outputChars = new FileWriter(sandy)){
            String outputString = "Sandy";
            char [] outputArray = outputString.toCharArray();
            outputChars.write(outputArray);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

### 缓存流


以介质是硬盘为例，字节流和字符流的弊端：
在每一次读写的时候，都会访问硬盘。 如果读写的频率比较高的时候，其性能表现不佳。

为了解决以上弊端，采用缓存流。
缓存流在读取的时候，会一次性读较多的数据到缓存中，以后每一次的读取，都是在缓存中访问，直到缓存中的数据读取完毕，再到硬盘中读取。

就好比吃饭，不用缓存就是每吃一口都到锅里去铲。用缓存就是先把饭盛到碗里，碗里的吃完了，再到锅里去铲

缓存流在写入数据的时候，会先把数据写入到缓存区，直到缓存区达到一定的量，才把这些数据，一起写入到硬盘中去。按照这种操作模式，就不会像字节流，字符流那样每写一个字节都访问硬盘，从而减少了IO操作

```java
package FileTest;

import java.io.*;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class BufferTest {
    public static void main(String[] args) {
        File manyLine = new File("src/FileTest/lines.txt");

        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
                FileReader fileReader = new FileReader(manyLine);
                BufferedReader br = new BufferedReader(fileReader);
        ) {
            String line = br.readLine();
            while(line != null){
                // 一次读一行
                System.out.println(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 向文件lol2.txt中写入三行语句
        File f = new File("src/FileTest/outputLines.txt");

        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println("Brickea");
            pw.println("Sandy");
            pw.println("Love");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

#### ```flush``` 直接写入

有的时候，需要立即把数据写入到硬盘，而不是等缓存满了才写出去。 这时候就需要用到flush

```java
package stream;
    
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class TestStream {
    public static void main(String[] args) {
        //向文件lol2.txt中写入三行语句
        File f =new File("src/FileText/outputLines.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
            pw.println("Brickea");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
                pw.flush();           
            pw.println("Sandy");
                pw.flush();
            pw.println("Love");
                pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```

### 数据流

使用数据流的writeUTF()和readUTF() 可以进行数据的格式化顺序读写
如本例，通过DataOutputStream 向文件顺序写出 布尔值，整数和字符串。 然后再通过DataInputStream 顺序读入这些数据。

注： 要用DataInputStream 读取一个文件，这个文件必须是由DataOutputStream 写出的，否则会出现EOFException，因为DataOutputStream 在写出的时候会做一些特殊标记，只有DataInputStream 才能成功的读取。

数据流同样也有缓冲区

```java
package stream;
      
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
      
public class TestStream {
      
    public static void main(String[] args) {
        write();
        read();
    }
 
    private static void read() {
        File f =new File("d:/lol.txt");
        try (
                FileInputStream fis  = new FileInputStream(f);
                DataInputStream dis =new DataInputStream(fis);
        ){
            boolean b= dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();
             
            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);
 
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    private static void write() {
        File f =new File("d:/lol.txt");
        try (
                FileOutputStream fos  = new FileOutputStream(f);
                DataOutputStream dos =new DataOutputStream(fos);
        ){
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
}
```

### 对象流 

对象流指的是可以直接把一个对象以流的形式传输给其他的介质，比如硬盘

一个对象以流的形式进行传输，叫做序列化。 该对象所对应的类，必须是实现Serializable接口

#### 序列化一个对象

创建一个Person对象，设置其名称为brickea。  
把该对象序列化到一个文件brickea.lol。  
然后再通过序列化把该文件转换为一个Person对象

注：把一个对象序列化有一个前提是：这个对象的类，必须实现了Serializable接口

```java
package FileTest;

import java.io.*;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/14/20
 *  Description:
 *************************************************************************/
public class Person implements Serializable{
    //表示这个类当前的版本，如果有了变化，比如新设计了属性，就应该修改这个版本号
    private static final long serialVersionUID = 1L;
    private String name = "brickea";

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // 准备一个文件来存储
        File brickeaStorage = new File("src/FileTest/brickea.person");

        // 对象流
        try (
                FileOutputStream outputStream = new FileOutputStream(brickeaStorage);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                FileInputStream inputStream = new FileInputStream(brickeaStorage);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            objectOutputStream.writeObject(new Person());

            Person brickea = (Person)objectInputStream.readObject();
            System.out.println(brickea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
Person{name='brickea'}

Process finished with exit code 0
```

## 流之间的关系

![](https://stepimagewm.how2j.cn/5678.png)

* 字节流 ```FileInputStream``` ```FileOutputStream```
* 字符流 ```FileReader``` ```FileWriter```
* 缓存流 ```BufferedReader``` ```BufferedWriter```
* 数据流 ```DataInputStream``` ```DataOutputStream```
* 对象流 ```ObjectOutputStream``` ```ObjectInputStream```