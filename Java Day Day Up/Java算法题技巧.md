# Java算法题技巧 <!-- omit in toc -->

- [输入的相关处理](#输入的相关处理)
  - [有间隔的转数组处理](#有间隔的转数组处理)
- [正负无穷的表示](#正负无穷的表示)

## 输入的相关处理

### 有间隔的转数组处理

```java
Scanner input = new Scanner(System.in);
String res = input.nextLine();

String[] midRes = res.split(",");
int[] array = new int[midRes.length];

for(int i=0;i<midRes.length;i++){
    array[i]=(int)Integer.parseInt(midRes[i]);
}

for(int i:array){
    System.out.println(i);
}
```
```
1,2,3,4
1
2
3
4
```

## 正负无穷的表示

这里是因为有时候算法需要用正负无穷来初始化比较条件

对于整型 Integer, 可以考虑用
* Integer.MAX_VALUE
* Integer.MIN_VALUE

对于浮点数 Float 或者 Double，可以考虑用
* POSITIVE_INFINITY
* NEGATIVE_INFINITY
