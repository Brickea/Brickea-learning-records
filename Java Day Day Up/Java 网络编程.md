# Java 网络编程 <!-- omit in toc -->

- [IP 地址和端口](#ip-地址和端口)
  - [IP 地址](#ip-地址)
  - [端口](#端口)
  - [使用 Java 获取本机 IP](#使用-java-获取本机-ip)
  - [使用 ping 判断一个IP是否能到达](#使用-ping-判断一个ip是否能到达)
  - [使用 Java 执行 ping 命令](#使用-java-执行-ping-命令)
  - [应用 - 使用 java 执行 ping 判断当前可用 ip](#应用---使用-java-执行-ping-判断当前可用-ip)
- [使用 socket 套接字进行通讯](#使用-socket-套接字进行通讯)
  - [建立连接](#建立连接)
  - [收发数字](#收发数字)
  - [收发字符](#收发字符)
- [实现聊天](#实现聊天)
- [多线程实时聊天玩法](#多线程实时聊天玩法)

## IP 地址和端口

### IP 地址

在网络中每台计算机都必须有一个的IP地址；  
32位，4个字节，常用点分十进制的格式表示，例如：192.168.1.100  
127.0.0.1 是固定ip地址，代表当前计算机，相当于面向对象里的 "this"  

### 端口

两台计算机进行连接，总有一台服务器，一台客户端。  
服务器和客户端之间的通信通过端口进行。如图：  

![](https://stepimagewm.how2j.cn/881.png)

ip地址是 192.168.1.100的服务器通过端口 8080  
与ip地址是192.168.1.189的客户端 的1087端口通信  

### 使用 Java 获取本机 IP

```java
package NetworkTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: None
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description: 网络编程测试
 *************************************************************************/
public class Main {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String localhost = inetAddress.getHostAddress();

        System.out.println("localhost address: "+localhost);
    }
}
```

### 使用 ping 判断一个IP是否能到达

ping一下刚才获得的本地IP

```
$ ping 192.168.99.113
PING 192.168.99.113 (192.168.99.113): 56 data bytes
64 bytes from 192.168.99.113: icmp_seq=0 ttl=64 time=0.051 ms
64 bytes from 192.168.99.113: icmp_seq=1 ttl=64 time=0.052 ms
64 bytes from 192.168.99.113: icmp_seq=2 ttl=64 time=0.068 ms
64 bytes from 192.168.99.113: icmp_seq=3 ttl=64 time=0.094 ms
```

### 使用 Java 执行 ping 命令

```java
package NetworkTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: None
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description: 网络编程测试
 *************************************************************************/
public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String localhost = inetAddress.getHostAddress();

        System.out.println("localhost address: "+localhost);


        // 执行系统命令
        Process p = Runtime.getRuntime().exec("ping "+localhost);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = null;
        StringBuffer res = new StringBuffer();

        while((line = bufferedReader.readLine())!=null){
            if(line.length()!=0){
                res.append(line+'\n');
            }
        }

        System.out.println("指令执行结果: "+ res);
    }
}
```

```
localhost address: 192.168.99.113
指令执行结果: PING 192.168.99.113 (192.168.99.113): 56 data bytes
64 bytes from 192.168.99.113: icmp_seq=0 ttl=64 time=0.041 ms
64 bytes from 192.168.99.113: icmp_seq=1 ttl=64 time=0.099 ms
64 bytes from 192.168.99.113: icmp_seq=2 ttl=64 time=0.089 ms
64 bytes from 192.168.99.113: icmp_seq=3 ttl=64 time=0.095 ms
64 bytes from 192.168.99.113: icmp_seq=4 ttl=64 time=0.097 ms
64 bytes from 192.168.99.113: icmp_seq=5 ttl=64 time=0.104 ms
64 bytes from 192.168.99.113: icmp_seq=6 ttl=64 time=0.096 ms
64 bytes from 192.168.99.113: icmp_seq=7 ttl=64 time=0.062 ms
64 bytes from 192.168.99.113: icmp_seq=8 ttl=64 time=0.046 ms
64 bytes from 192.168.99.113: icmp_seq=9 ttl=64 time=0.046 ms
64 bytes from 192.168.99.113: icmp_seq=10 ttl=64 time=0.095 ms
--- 192.168.99.113 ping statistics ---
11 packets transmitted, 11 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 0.041/0.079/0.104/0.024 ms
```

### 应用 - 使用 java 执行 ping 判断当前可用 ip

```java
package NetworkTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: None
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description: 网络编程测试
 *************************************************************************/
public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String localhost = inetAddress.getHostAddress();

        System.out.println("localhost address: "+localhost);

        String prefixAddress = localhost.substring(0,11);


        String line = null;
        for(int i=0;i<255;i++){
            String testAddress = prefixAddress+i;
            Process p = Runtime.getRuntime().exec("ping "+testAddress);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            line = bufferedReader.readLine();
            if(!line.contains("Request timeout")){
                System.out.println("可用: "+testAddress);
            }

        }
    }
}
```

```
localhost address: 192.168.99.113
可用: 192.168.99.0
可用: 192.168.99.1
可用: 192.168.99.2
可用: 192.168.99.3
可用: 192.168.99.4
可用: 192.168.99.5
可用: 192.168.99.6
可用: 192.168.99.7
可用: 192.168.99.8
可用: 192.168.99.9
可用: 192.168.99.10
...
```

## 使用 socket 套接字进行通讯

### 建立连接

1. 服务端开启8888端口，并监听着，时刻等待着客户端的连接请求
2. 客户端知道服务端的ip地址和监听端口号，发出请求到服务端  

客户端的端口地址是系统分配的，通常都会大于1024

一旦建立了连接，服务端会得到一个新的Socket对象，该对象负责与客户端进行通信。 

注意： 在开发调试的过程中，如果修改过了服务器Server代码，要关闭启动的Server,否则新的Server不能启动，因为8888端口被占用了

```java
package NetworkTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(6666)){
            System.out.println("监听端口");

            Socket socket = server.accept();// 等待
            System.out.println("有客户端来访");

            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

```

```
监听端口
有客户端来访
```

```java
package NetworkTest;

import java.io.IOException;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Clinet {
    // 访问本机6666端口
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1",6666)){
            System.out.println("访问成功"+socket);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

```

```
访问成功Socket[addr=/127.0.0.1,port=6666,localport=57921]
```

### 收发数字

一旦建立了连接，服务端和客户端就可以通过Socket进行通信了
1. 客户端打开输出流，并发送数字 110
2. 服务端打开输入流，接受数字 110，并打印

```java
package NetworkTest;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(6666)){
            System.out.println("监听端口");

            Socket socket = server.accept();// 等待
            System.out.println("有客户端来访");

            InputStream inputStream = socket.getInputStream();

            int res = inputStream.read();
            System.out.println("收到结果: "+ res);

            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

```
监听端口
有客户端来访
收到结果: 222
```

```java
package NetworkTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Clinet {
    // 访问本机6666端口
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1",6666)){
            System.out.println("访问成功"+socket);

            OutputStream outputStream = socket.getOutputStream();

            System.out.println("发送数字");
            outputStream.write(222);

            outputStream.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
```

```
访问成功Socket[addr=/127.0.0.1,port=6666,localport=57966]
发送数字
```

### 收发字符

直接使用字节流收发字符串比较麻烦，使用数据流对字节流进行封装，这样收发字符串就容易了
1. 把输出流封装在DataOutputStream中
使用writeUTF发送字符串 "Legendary!"
2. 把输入流封装在DataInputStream
使用readUTF读取字符串,并打印

```java
package NetworkTest;

import jdk.internal.util.xml.impl.Input;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(6666)){
            System.out.println("监听端口");

            Socket socket = server.accept();// 等待
            System.out.println("有客户端来访");

            InputStream inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String res = dataInputStream.readUTF();

            System.out.println("收到结果: "+ res);

            socket.close();
            inputStream.close();
            dataInputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

```

```
监听端口
有客户端来访
收到结果: hi 你好
```

```java
package NetworkTest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Clinet {
    // 访问本机6666端口
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1",6666)){
            System.out.println("访问成功"+socket);

            OutputStream outputStream = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("发送字符流");

            dataOutputStream.writeUTF("hi 你好");

            outputStream.close();
            dataOutputStream.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

```

```
访问成功Socket[addr=/127.0.0.1,port=6666,localport=58002]
发送字符流
```

## 实现聊天

```java
package NetworkTest;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(6666)){
            System.out.println("监听端口");
            Socket socket = server.accept();// 等待

            // 建立字符输入流
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String message = "";

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            Scanner input = new Scanner(System.in);

            while(!message.equals("exit")){
                System.out.println("监听端口");

                System.out.println("有客户端来访");
                message = dataInputStream.readUTF();
                System.out.println("收到结果: "+ message);

                System.out.println("传给客户端数据(exit结束): ");
                message = input.nextLine();
                dataOutputStream.writeUTF(message);


            }

            socket.close();
            inputStream.close();
            dataInputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

```
监听端口
监听端口
有客户端来访
收到结果: 2
传给客户端数据(exit结束): 
hi
监听端口
有客户端来访
收到结果: 666
传给客户端数据(exit结束): 
exit
```

```java
package NetworkTest;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Clinet {
    // 访问本机6666端口
    public static void main(String[] args) {
        // 建立连接
        try (Socket socket = new Socket("127.0.0.1", 6666)) {
            System.out.println("访问成功" + socket);

            // 建立字符输出流
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // 建立字符输入流
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            // 要求输入
            Scanner input = new Scanner(System.in);

            String message = "";

            while (!message.equals("exit")) {
                System.out.println("客户端请输入发送字符流(exit退出): ");
                message = input.nextLine();
                dataOutputStream.writeUTF(message);

                message = dataInputStream.readUTF();
                System.out.println("收到服务端数据: "+ message);
            }


            outputStream.close();
            dataOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

```
访问成功Socket[addr=/127.0.0.1,port=6666,localport=58171]
客户端请输入发送字符流(exit退出): 
2
收到服务端数据: hi
客户端请输入发送字符流(exit退出): 
666
收到服务端数据: exit
```

## 多线程实时聊天玩法

不用多线程的话发消息和接收消息不能同时进行，那现在用多线程写一个可以同时收发消息的实时聊天功能

```java
package NetworkTest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/

public class RecieveThread extends Thread {

    private Socket s;

    public RecieveThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            InputStream is = s.getInputStream();

            DataInputStream dis = new DataInputStream(is);
            while (true) {
                String msg = dis.readUTF();
                System.out.println(msg);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
```

```java
package NetworkTest;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {

    private Socket s;

    public SendThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            while (true) {
                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                dos.writeUTF(str);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

```

```java
package NetworkTest;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);

            System.out.println("监听在端口号:8888");
            Socket s = ss.accept();

            //启动发送消息线程
            new SendThread(s).start();
            //启动接受消息线程
            new RecieveThread(s).start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

```

```java
package NetworkTest;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*************************************************************************
 *  Project: java_practice
 *  Dependencies: none
 *  Author: Zixiao Wang
 *  Create: 7/18/20
 *  Description:
 *************************************************************************/
public class Clinet {
    // 访问本机6666端口
    public static void main(String[] args) {
        // 建立连接
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            // 启动发送消息线程
            new SendThread(s).start();
            // 启动接受消息线程
            new RecieveThread(s).start();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

```