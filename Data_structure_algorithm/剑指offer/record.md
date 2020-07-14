# 剑指offer题解 <!-- omit in toc -->

- [03 数组中重复的数字](#03-数组中重复的数字)
  - [Solution 1](#solution-1)
  - [Solution 2](#solution-2)
- [04 二维数组中的查找](#04-二维数组中的查找)
  - [Solution 1](#solution-1-1)

## 03 数组中重复的数字

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

### Solution 1

思路是建一个数组，按照数组里面数字作为下标进行布尔存储，如果出现了重复的则返回重复的那个数字。布尔数组也会小一些。

```
运行时间：14ms

占用内存：9556k
```

```java
public class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        boolean[] unique = new boolean[length];
        for(int i=0;i<length;i++){
            if(!unique[numbers[i]]){
                unique[numbers[i]]=true;
            }else{
                duplication[0]=numbers[i];
                return true;
            }
        }
        return false;
    }
}
```

Solution 1 时间复杂度是$O(n)$但是空间复杂度也是$O(n)$，可以考虑将空间再压缩到$O(1)$

### Solution 2

因为数字是从$0\to n-1$，所以我们可以从头到尾扫描该数组，当扫描到下标为$i$的数组的时候(用$m$表示其数值)，跳到下标为$m$的位置上，查看两者是否相等，相等则返回结果，不相等则将两者交换，这时候数值为$m$的数字就在自己对应下标的位置上。之后再重复这个过程直到找到重复的数字

```
运行时间：13ms

占用内存：9584k
```

```java
public class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        int i=0;
        while(i<length){
            if(i==numbers[i]){
                i++;
            }
            else if(numbers[numbers[i]]==numbers[i]){
                duplication[0]=numbers[i];
                return true;
            }else{
                int temp = numbers[numbers[i]];
                numbers[numbers[i]]=numbers[i];
                numbers[i]=temp;
            }
        }
        return false;
    }
}
```

## 04 二维数组中的查找

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

### Solution 1

思路是从右上角一步一步缩小范围，如果右上角的元素大于目标元素，则这一列剩下的都比目标大，将这一列去除。如果小于目标元素，则这一行都比目标小，将这一行去除。

```
运行时间：105ms

占用内存：16548k
```

```java
public class Solution {
    public boolean Find(int target, int [][] array) {
        int targetColumn = array[0].length-1;
        int targetRow = 0;
        while(targetRow<array.length && targetColumn>-1){
            if(array[targetRow][targetColumn]==target){
                return true;
            }
            if(array[targetRow][targetColumn]>target){
                targetColumn--;
            }else{
                targetRow++;
            }
        }
        return false;
        
    }
}
```