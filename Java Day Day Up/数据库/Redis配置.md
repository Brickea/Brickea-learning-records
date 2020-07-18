# Redis 配置 <!-- omit in toc -->

## Windows下的安装

可以直接下载适用于windows的压缩包

[下载](https://github.com/dmajkic/redis/downloads)

但是最好使用```make```命令构建[官网的版本](https://redis.io/download)

### Windows 下使用 ```make```命令

Windows下需要安装 GNU 编译器

1. 下载[MinGW](https://sourceforge.net/projects/mingw/)

下载后，运行程序：mingw-get-inst-20120426.exe，选择download latest repository catalogues. 选择编译器是勾选C Compiler 与C++ Compiler，点击next进行下载及安装。

2. 设置环境变量

右击计算机->属性->高级系统设置->环境变量，在系统变量中找到PATH，将MinGW安装目录里的bin文件夹的地址添加到PATH里面，（注意：PATH里两个目录之间以英文的;隔开）。打开MinGW的安装目录，打开bin文件夹，将mingw32-make.exe重命名为make.exe。