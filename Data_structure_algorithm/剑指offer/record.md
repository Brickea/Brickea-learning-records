# 剑指offer题解 <!-- omit in toc -->

- [03 数组中重复的数字](#03-数组中重复的数字)
  - [Solution 1](#solution-1)
  - [Solution 2](#solution-2)
- [04 二维数组中的查找](#04-二维数组中的查找)
  - [Solution 1](#solution-1-1)
- [05替换空格](#05替换空格)
  - [Solution 1](#solution-1-2)
  - [Solution 2](#solution-2-1)
- [06从尾到头打印链表](#06从尾到头打印链表)
  - [Solution 1](#solution-1-3)
- [07 重建二叉树](#07-重建二叉树)
  - [Solution 1](#solution-1-4)
- [08 二叉树的下一个节点](#08-二叉树的下一个节点)
  - [Solution 1](#solution-1-5)
- [09 用两个栈实现队列](#09-用两个栈实现队列)
  - [Solution 1](#solution-1-6)
- [10 斐波那契数列](#10-斐波那契数列)
  - [Solution 1](#solution-1-7)
- [11 旋转数组的最小数字](#11-旋转数组的最小数字)
  - [Solution 1](#solution-1-8)
- [12 矩阵中的路径](#12-矩阵中的路径)
  - [Solution 1](#solution-1-9)
- [13 机器人运动范围](#13-机器人运动范围)
  - [Solution 1](#solution-1-10)
- [14 切绳子](#14-切绳子)
  - [Solution 1 - 动态规划](#solution-1---动态规划)
- [15 二进制中1的个数](#15-二进制中1的个数)
  - [Solution 1](#solution-1-11)
- [18 数值的整数次方](#18-数值的整数次方)
  - [Solution 1](#solution-1-12)
- [18 删除链表的节点](#18-删除链表的节点)
  - [Solution 1](#solution-1-13)
- [19 正则匹配](#19-正则匹配)
  - [Solution 1](#solution-1-14)
- [20 表示数值的字符串](#20-表示数值的字符串)
  - [Solution 1](#solution-1-15)
- [21 调整数组顺序](#21-调整数组顺序)
  - [Solution 1](#solution-1-16)
- [22 链表中倒数第k个节点](#22-链表中倒数第k个节点)
  - [Solution 1](#solution-1-17)
- [23 链表中环的入口节点](#23-链表中环的入口节点)
  - [Solution 1](#solution-1-18)
- [24 反转链表](#24-反转链表)
  - [Solution 1](#solution-1-19)
- [25 合并两个排序的链表](#25-合并两个排序的链表)
  - [Solution 1](#solution-1-20)
- [26 树的子结构](#26-树的子结构)
  - [Solution 1](#solution-1-21)
- [27 二叉树的镜像](#27-二叉树的镜像)
  - [Solution 1](#solution-1-22)
- [28 对称的二叉树](#28-对称的二叉树)
  - [Solution 1](#solution-1-23)
- [29 顺时针打印矩阵](#29-顺时针打印矩阵)
  - [Solution 1](#solution-1-24)
  - [Solution 2](#solution-2-2)
- [30 包含min函数的栈](#30-包含min函数的栈)
  - [Solution 1](#solution-1-25)
- [31 栈的压入，弹出顺序](#31-栈的压入弹出顺序)
  - [Solution 1](#solution-1-26)
- [32 从上到下打印二叉树](#32-从上到下打印二叉树)
  - [Solution 1](#solution-1-27)
- [33 按之字形顺序打印二叉树](#33-按之字形顺序打印二叉树)
  - [Solution 1](#solution-1-28)
- [34 二叉树中和为某一值的路径](#34-二叉树中和为某一值的路径)
  - [Solution 1](#solution-1-29)
- [35 复杂链表复制](#35-复杂链表复制)
  - [Solution 1](#solution-1-30)
- [36 序列化二叉树](#36-序列化二叉树)
- [37 字符串的排列](#37-字符串的排列)
- [38 数组中出现次数超过一半的数字](#38-数组中出现次数超过一半的数字)
  - [Solution 1 On](#solution-1-on)
  - [Solution 2 On](#solution-2-on)
- [39 最小的k个数字](#39-最小的k个数字)
  - [Solution 1](#solution-1-31)
  - [Solution 2](#solution-2-3)

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

## 05替换空格

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

```
运行时间：11ms

占用内存：9488k
```

### Solution 1

这个思路这个没什么好说的

```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
    	for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                str.replace(i,i+1,"%20");
            }
        }
        return str.toString();
    }
}
```

但是这里有个问题就是时间复杂度比较高，看一下```replace```函数的源码。每一次```replace```的时候，都需要复制字符串后面的字符，这样的话会造成时间复杂度为$o(n^2)$

```java
    /**
     * Replaces the characters in a substring of this sequence
     * with characters in the specified {@code String}. The substring
     * begins at the specified {@code start} and extends to the character
     * at index {@code end - 1} or to the end of the
     * sequence if no such character exists. First the
     * characters in the substring are removed and then the specified
     * {@code String} is inserted at {@code start}. (This
     * sequence will be lengthened to accommodate the
     * specified String if necessary.)
     *
     * @param      start    The beginning index, inclusive.
     * @param      end      The ending index, exclusive.
     * @param      str   String that will replace previous contents.
     * @return     This object.
     * @throws     StringIndexOutOfBoundsException  if {@code start}
     *             is negative, greater than {@code length()}, or
     *             greater than {@code end}.
     */
    public AbstractStringBuilder replace(int start, int end, String str) {
        if (start < 0)
            throw new StringIndexOutOfBoundsException(start);
        if (start > count)
            throw new StringIndexOutOfBoundsException("start > length()");
        if (start > end)
            throw new StringIndexOutOfBoundsException("start > end");

        if (end > count)
            end = count;
        int len = str.length();
        int newCount = count + len - (end - start);
        ensureCapacityInternal(newCount);

        System.arraycopy(value, end, value, start + len, count - end);
        str.getChars(value, start);
        count = newCount;
        return this;
    }
```

### Solution 2

改进思路是，先遍历一遍原字符串，获取空格的数量，然后创建新的字符串，长度为空格替换后的数量。之后从尾部开始遍历原字符串，这样可以避免因为长度增加而导致的遍历复制的问题。这样相当于便利了两边字符串，时间复杂度为$O(n)$

```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
        int spaceNumber = 0;
        // 统计空格的个数
    	for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                spaceNumber++;
            }
        }
        // 创建增长了的字符数组
        int resIndex = str.length()+spaceNumber*2;
        char[] res = new char[resIndex];
        resIndex--;
        // 从尾部开始遍历
        for(int i=str.length()-1;i>-1;i--){
            if(str.charAt(i)==' '){
                res[resIndex--]='0';
                res[resIndex--]='2';
                res[resIndex--]='%';
            }else{
                res[resIndex--]=str.charAt(i);
            }
        }
        return new String(res);
    }
}
```

```
运行时间：14ms

占用内存：9520k
```

## 06从尾到头打印链表

输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

### Solution 1

思路就是一遍遍历，然后把链表的元素插入到```ArrayList```的首位

```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode currentNode = listNode;
        ArrayList<Integer> res = new ArrayList<>();
        while(currentNode!=null){
            res.add(0,currentNode.val);
            currentNode=currentNode.next;
        }
        return res;
    }
}
```

```
运行时间：13ms

占用内存：9604k
```

## 07 重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

### Solution 1

通过先序遍历数组找到根节点，再通过中序遍历得到左右子树的长度，之后将左右子树递归即可

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int[] copyRangeOf(int[] array, int startIndex, int endIndex){
        // 数组切片函数
        if(array.length==0||array.length<endIndex){
            return null;
        }
        int[] res = new int[endIndex - startIndex];
        int j=0;
        for(int i = startIndex;i<endIndex;i++){
            res[j] = array[i];
            j++;
        }
        return res;
    }
    
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int rootValue = pre[0];
        TreeNode root = new TreeNode(rootValue);
        // 获取根节点在中序遍历中的下标，以此获取左右子树
        int inRootIndex = 0;
        for(int i=0;i<in.length;i++){
            System.out.println(in[i]);
            if(in[i]==rootValue){
                inRootIndex=i;
                break;
            }
        }
        // 左右子树递归
        
        // 获取左子树长度
        int leftLen = inRootIndex;
        // 获取右子树长度
        int rightLen = in.length - inRootIndex - 1;
        if(leftLen==0){
            // 没有左子树
            root.left = null;
        }else{
            root.left = this.reConstructBinaryTree(this.copyRangeOf(pre,1,leftLen+1),this.copyRangeOf(in,0,inRootIndex));
        }
        if(rightLen==0){
            // 没有右子树
            root.right = null;
        }else{
            root.right = this.reConstructBinaryTree(this.copyRangeOf(pre,leftLen+1,pre.length),this.copyRangeOf(in,inRootIndex+1,in.length));
        }
        return root;
    }
}
```

```
运行时间：224ms

占用内存：24948k
```

## 08 二叉树的下一个节点

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

### Solution 1

先查看有没有右节点，有右节点直接中序遍历右子树到最左子节点

没有右节点，查看是否是父节点的左子树，是左子树，返回父节点

没有右节点，且是父节点的右子树，向上地查找，是否存在一节点是父节点的左子节点，此时该节点的父节点为我们要找的点

```java
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        // 先查看是否有右节点，有右节点直接中序遍历右子树得到最左节点
        if(pNode.right != null){
            return this.inNavigation(pNode.right);
        }
        // 没有右节点，查看是否是父节点的左子树
        if(pNode.next!=null&&pNode.next.left == pNode){
            // 是左子树，返回父节点
            return pNode.next;
        }
        // 没有右节点，且不是父节点的左子树，则查找一个节点是其父节点的左节点，若有，则该节点的父节点是我们要找的
        TreeLinkNode target = pNode.next;
        while(target!=null&&target.next!=null){
            if(target.next.left==target){
                return target.next;
            }
            target = target.next;
        }
        return null;
    }
    
    public TreeLinkNode inNavigation(TreeLinkNode pNode){
        // 中序遍函数
        if(pNode.left == null){
            return pNode;
        }
        TreeLinkNode nextNode = this.inNavigation(pNode.left);
        return nextNode;
    }
}
```

```
运行时间：14ms

占用内存：9772k
```

## 09 用两个栈实现队列

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

### Solution 1

可以考虑一下队列pop的时候，在stack2中的影响，只要stack2不空就可以直接pop出去，如果stack1

```java
import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if(stack2.size()==0){
            while(stack1.size()!=0){
                int temp = stack1.pop();
                stack2.push(temp);
            }
        }
        return stack2.pop();
    }
}
```

## 10 斐波那契数列

### Solution 1

经典问题

```java
public class Solution {
    public int Fibonacci(int n) {
        int temp1 = 0;
        int temp2 = 1;
        while(n!=0){
            n--;
            int temp3 = temp2;
            temp2 = temp1+temp2;
            temp1 = temp3;
        }
        return temp1;
    }
}
```

```
运行时间：10ms

占用内存：9448k
```

## 11 旋转数组的最小数字

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。  
输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。  
例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。  
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

### Solution 1

因为旋转的特性，左边递增序列的第一个元素一定大于等于第二个递增序列的最后一个元素。如果没有出现这样的情况则说明数组本身没有旋转，可以直接返回第一个元素，即为最小的元素

使用二分法的思路，两个指针分别指向第一个和最后一个，如果中间元素比第一个指向的元素大，说明中间元素在前面的递增序列，将index1移动到中间元素的位置；如果中间元素比最后一个元素小，说明中间元素在第二个递增序列，将index2移动到中间元素的位置。最后得到的情况应该是，index2和index1之间差1，index2指向最小元素。

```java
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0){
            return 0;
        }
        int index1 = 0;
        int index2 = array.length-1;
        int midIndex = 0;
        
        while(array[index1]>=array[index2]){
            if(index2-index1==1){
                midIndex = index2;
                break;
            }
            // 数组确实旋转了
            midIndex = (index1+index2)/2;
            if(array[midIndex]>=array[index1]){
                // 中间位置的数字在前面的递增序列
                index1 = midIndex;
            }
            else if(array[midIndex]<=array[index2]){
                // 中间位置的数字在后面的递增序列
                index2 = midIndex;
            }
        }
        
        return array[midIndex];
    }
}
```

```
运行时间：194ms

占用内存：28708k
```

## 12 矩阵中的路径

题目描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
​
### Solution 1

回溯法

```java
import java.util.*;
     
public class Solution {
     
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix==null || matrix.length==0 || str==null || str.length==0 || matrix.length!=rows*cols || rows<=0 || cols<=0 || rows*cols < str.length) {
            return false ;
        }
 
        boolean[] visited = new boolean[rows*cols] ;
        int[] pathLength = {0} ;
 
        for(int i=0 ; i<=rows-1 ; i++) {
            for(int j=0 ; j<=cols-1 ; j++) {
                if(hasPathCore(matrix, rows, cols, str, i, j, visited, pathLength)) { return true ; }
            }
        }
 
        return false ;
    }
     
    public boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, int row, int col, boolean[] visited, int[] pathLength) {
        boolean flag = false ;
 
        if(row>=0 && row<rows && col>=0 && col<cols && !visited[row*cols+col] && matrix[row*cols+col]==str[pathLength[0]]) {
            pathLength[0]++ ;
            visited[row*cols+col] = true ;
            if(pathLength[0]==str.length) { return true ; }
            flag = hasPathCore(matrix, rows, cols, str, row, col+1, visited, pathLength)  ||
                   hasPathCore(matrix, rows, cols, str, row+1, col, visited, pathLength)  ||
                   hasPathCore(matrix, rows, cols, str, row, col-1, visited, pathLength)  ||
                   hasPathCore(matrix, rows, cols, str, row-1, col, visited, pathLength) ;
 
            if(!flag) {
                pathLength[0]-- ;
                visited[row*cols+col] = false ;
            }
        }
 
        return flag ;
    }
     
}
```

```
运行时间：12ms

占用内存：9280k
```

## 13 机器人运动范围


地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

### Solution 1

回溯法解决

```java
public class Solution {
    public int movingCount(int threshold, int rows, int cols){
        int res = 0;
        
        // 初始化访问数组 visited
        boolean[][] visited = new boolean[rows][cols];
        
        res = movingCore(threshold,0,0,visited,rows,cols);
        
        return res;
    }
    
    private int movingCore(int threshold, int row, int col, boolean[][] visited,int rows,int cols){
        int count = 0;
        
        if(unitCheck(threshold,row,col,visited,rows,cols)){
            visited[row][col] = true;
            
            count = 1 + movingCore(threshold,row+1,col,visited,rows,cols)+
                movingCore(threshold,row-1,col,visited,rows,cols)+
                movingCore(threshold,row,col+1,visited,rows,cols)+
                movingCore(threshold,row,col-1,visited,rows,cols);
        }
        
        return count;
    }
    
    private boolean unitCheck(int threshold, int row, int col, boolean[][] visited,int rows,int cols){
        if((row<0||row>=rows)||(col<0||col>=cols))
            return false;
        
        if(digitCheck(threshold,row,col)&&!visited[row][col]){
            return true;
        }
        
        return false;
    }
    
    private boolean digitCheck(int threshold,int row,int col){
        int rowInt = 0;
        int colInt = 0;
        
        while(row>0){
            rowInt += row%10;
            row /= 10;
        }
        while(col>0){
            colInt += col%10;
            col /= 10;
        }
        
        return (rowInt+colInt)<=threshold;
    }
}
```

```
运行时间：10ms 占用内存：9436KB
```

## 14 切绳子

给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

### Solution 1 - 动态规划

```java
public class Solution {
    public int cutRope(int target) {
        if(target==2)
            return 1;
        if(target==3)
            return 2;
        
        // 定义 dp 装载为 长度为i的时候最大的切割乘积
        // 因为一定会切一刀，所以自顶向下将切一刀的时候各个部分的最大乘积相乘
        int[] dp = new int[target+1];
        
        // 1,2,3长度是特殊的basecase，因为这三种长度自己本身作为子部分要比切割后最大的可能乘积大，所以这里特别设置。
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        for(int i=4;i<=target;i++){
            int tempMax = 0;
            for(int j = 1;j<=i/2;j++){
                // 因为超过 i/2之后结果是相同的
                tempMax = Math.max(tempMax,dp[i-j]*dp[j]);
            }
            dp[i] = tempMax;
        }
    
        return dp[target];
    }
}
```

## 15 二进制中1的个数

输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。

### Solution 1

这里要注意负数的二进制表示是其相反数的补码，所以使用以下这种方式会比较妥当

```java
public class Solution {
    public int NumberOf1(int n) {
        // 将输入与1做与运算，之后将1左移动一位再做与运算
        int count = 0;
        int compare = 1;
        
        for(int i=0;i<32;i++){
            count = (n&compare)!=0?count+1:count;
            compare<<=1;
        }
        
        return count;
    }
}
```

## 18 数值的整数次方

### Solution 1

给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

保证base和exponent不同时为0

```java
public class Solution {
    public double Power(double base, int exponent) {
        // basecase
        if(base==0)
            return base;
        if(exponent==0)
            return 1;
        
        double res = 1;
        int tempEx = exponent;
        if(exponent<0){
            tempEx = exponent*-1;
        }
        
        
        for(int i=0;i<tempEx;i++)
            res *= base;
         
        return (exponent<0)?1/res:res;
  }
}
```

```
运行时间：29ms 占用内存：10524KB
```


## 18 删除链表的节点

在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

### Solution 1

删除就完事了

```java
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead==null){
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = pHead;
        
        ListNode pre = fakeHead;
        ListNode current = pHead;
        
        int count = 1;
        
        while(current.next!=null){
            if(current.val==current.next.val){
                count++;
                current = current.next;
            }else{
                if(count>=2){
                    pre.next = current.next;
                    current = current.next;
                    count = 1;
                }else{
                    pre = current;
                    current = current.next;
                }
            }
            
        }
        if(count!=1){
            pre.next = current.next;
        }
        
        return fakeHead.next;
    }
}
```

```
运行时间：14ms 占用内存：9848KB
```

## 19 正则匹配

请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

### Solution 1

```java

public class Solution {
public boolean match(char[] str, char[] pattern)
    {
        return matchTwo(str,0,str.length,pattern,0,pattern.length);
         
    }
private boolean matchTwo(char[] str, int i, int length1, char[] pattern,
            int j, int length2) {
        if(i==length1&&j==length2) {
            return true;
        }
        if(i==length1&&j!=length2) {
            while(j!=length2){
                if(pattern[j]!='*'&&(j+1>=length2||pattern[j+1]!='*')){
                    return false;
                }
                j++;
            }
            return true;
        }
    if(i!=length1&&j==length2) {
            return false;
        }
        if(j+1==length2){
            if(str[i]==pattern[j]||pattern[j]=='.')
                return matchTwo(str, i+1, length1, pattern, j+1, length2);
            else {
                return false;
            }
        }
        if((str[i]==pattern[j]||pattern[j]=='.')&&pattern[j+1]!='*')
            return matchTwo(str, i+1, length1, pattern, j+1, length2);
        if((str[i]==pattern[j]||pattern[j]=='.')&&pattern[j+1]=='*')
            return matchTwo(str, i, length1, pattern, j+2, length2)||matchTwo(str, i+1, length1, pattern, j, length2);
        if(pattern[j+1]=='*')
            return matchTwo(str, i, length1, pattern, j+2, length2);
        return false;
    }
}
```

```
运行时间：11ms

占用内存：9912k
```

## 20 表示数值的字符串

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

### Solution 1

```java
public class Solution {
    private int i = 0;
    public boolean isNumeric(char[] str) {
        // 数值的表示分为三部分
        // A 为整数部分，B为小数点部分，C为指数部分
        // A [.[B]][e|EC] 此时有整数部分
        // [.[B]][e|EC] 此时没有整数部分

        if(str.length==0){
            return false;
        }

        boolean res = this.scanSignedInteger(str); // 首先判断有没有整数部分

        if(i<str.length&&str[this.i]=='.'){
            // 遇到小数部分
            // 使用||是因为整数部分和小数部分必须至少存在一个
            this.i++;
            res = this.scanUnsignedInteger(str)|| res;
        }

        if(i<str.length&&(str[this.i]=='e'||str[this.i]=='E')){
            // 遇到指数部分
            // 使用&& 是因为指数部分不能独立存在，整数和小数部分必须至少存在一个
            this.i++;
            res = this.scanSignedInteger(str) && res;
        }

        return res&&this.i==str.length; // 要保证判断到尾才可以
    }

    private boolean scanUnsignedInteger(char[] str){

        // 判断是否存在0-9的数字
        int temp = this.i;
        while(i<str.length&&str[i]>='0'&&str[i]<='9'){
            this.i++;
        }
        return i>temp;
    }

    private boolean scanSignedInteger(char[] str){
        if(i<str.length&&(str[i]=='-'||str[i]=='+')){
            // 先判断是否有符号
            this.i++;
        }
        return this.scanUnsignedInteger(str);
    }

}

```

## 21 调整数组顺序

### Solution 1

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

```java
public class Solution {
    private void exchangePosition(int [] array, int i , int j){
        // 将数字从j按照位置交替交换到i
        while(j!=i){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            j--;
        }
    }
    public void reOrderArray(int [] array) {
        int i=0;
       while(i<array.length){
           if(array[i]%2!=0){
               // 如果是奇数则上浮
               int j=i;
               while(j-1>-1&&array[j-1]%2==0){
                   int temp = array[j];
                   array[j] = array[j-1];
                   array[j-1] = temp;
                   j--;
               }
           }
           i++;
       }
    }
}
```

## 22 链表中倒数第k个节点

输入一个链表，输出该链表中倒数第k个结点。

### Solution 1

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head==null||k==0){
            return null;
        }
        
        ListNode first = head;
        ListNode last = head;
        
        int count = 1;
        while(last.next!=null){
            if(count==k){
                first = first.next;
            }else{
                count++;
            }
            last = last.next;
        }
        return count<k?null:first;
    }
}
```

## 23 链表中环的入口节点

给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

### Solution 1

```java
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {

    private ListNode findCircleNode(ListNode pHead){
        // 返回环中的某一个节点
        ListNode walker = pHead;
        ListNode runner = pHead;
        
        boolean flag = false; // 判断是否真正进入了环
        
        while(walker.next!=null&&runner.next!=null&&runner.next.next!=null){
            flag = true;
            walker = walker.next;
            runner=runner.next.next;
            if(walker==runner){
                break;
            }
        }
        
        return walker==runner&&flag?walker:null;
        
    }
    
    private int circleLen(ListNode circleNode){
        ListNode temp = circleNode;
        int len = 0;
        do{
            temp = temp.next;
            len++;
        }while(temp!=circleNode);
        
        return len;
    }
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead==null){
            return null;
        }
        ListNode circleNode = this.findCircleNode(pHead);
        int len = 0;
        if(circleNode!=null){
            len = this.circleLen(circleNode);
        }else{
            return null;
        }
        
        // 快指针先以环的长度进行移动，之后两指针以相同速度移动，最后碰撞的那个节点就是环的入口
        ListNode walker = pHead;
        ListNode runner = pHead;
        for(int i=len;i>0;i--){
            runner = runner.next;
        }
        while(walker!=runner){
            runner = runner.next;
            walker = walker.next;
        }
        
        return walker;
    }
}
```

## 24 反转链表

输入一个链表，反转链表后，输出新链表的表头。

### Solution 1

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }else if(head.next==null){
            return head;
        }
        
        ListNode pre = null;
        ListNode current = head;
        
        while(current!=null){
            ListNode temp = current.next; // temp = head.next.next
            current.next = pre;  //head.next.next = head
            pre = current; // head = head.next
            current = temp; // head.next = head.next.next
        }
        
        head.next = null;
        
        return pre;
    }
}


```

## 25 合并两个排序的链表

输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

### Solution 1

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }
        
        ListNode resHead = null;
        
        if(list1.val<list2.val){
            resHead = list1;
            resHead.next = Merge(list1.next,list2);
        }else{
            resHead = list2;
            resHead.next = Merge(list1,list2.next);
        }
        
        return resHead;
    }
}
```

## 26 树的子结构

输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

### Solution 1

这里选择递归排查

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    private boolean HasStructure(TreeNode root1,TreeNode root2){
        boolean res = false;
        
        if(root2==null){
            // 到达树2的叶子节点
            return true;
        }
        if(root1==null){
            // 没有到达树2 的叶子节点，但是到达了树1的叶子节点
            return false;
        }
        
        if(root1.val!=root2.val){
            return false;
        }
        
        return HasStructure(root1.left,root2.left)&&HasStructure(root1.right,root2.right);
    }
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean res = false;
        
        if(root1!=null&&root2!=null){
            if(root1.val==root2.val){
                res = HasStructure(root1,root2);
            }
            if(!res){
                res = HasSubtree(root1.left,root2);
            }
            if(!res){
                res = HasSubtree(root1.right,root2);
            }
        }
        
        return res;
    }
}
```

## 27 二叉树的镜像

操作给定的二叉树，将其变换为源二叉树的镜像。

```
二叉树的镜像定义：源二叉树 
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
```


### Solution 1

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    private void helper(TreeNode node){
        if(node!=null){
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            helper(node.left);
            helper(node.right);
        }
        
    }
    public void Mirror(TreeNode root) {
        helper(root);
    }
}
```

## 28 对称的二叉树

请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

### Solution 1

思路为使用先序遍历和对称先序遍历，即根左右和根右左，如果树是对称的则这两个遍历结果是相同的。

```java
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    private boolean helper(TreeNode root1,TreeNode root2){
        if(root1==null&&root2==null){
            return true;
        }
        if(root1==null||root2==null){
            // 先序和对称先序遍历碰到了不对称的null情况
            return false;
        }
        
        if(root1.val!=root2.val){
            return false;
        }
        
        return helper(root1.left,root2.right)&&helper(root1.right,root2.left);
    }
    
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot==null){
            return true;
        }
        return helper(pRoot.left,pRoot.right);
    }
}
```

## 29 顺时针打印矩阵

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

### Solution 1

小心判断边界条件

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        if(row==0){
            return null;
        }
        int col = matrix[0].length;
        if(col==0){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int startRow = 0;
        int endRow = row-1;
        int startCol = 0;
        int endCol = col-1;
        while(startRow<=endRow&&startCol<=endCol){
            //如果就剩下一行
            if(startRow==endRow){
                for(int i=startCol;i<=endCol;i++)
                    list.add(matrix[startRow][i]);
                return list;
            }
            //如果就剩下一列
            if(startCol==endCol){
                for(int i=startRow;i<=endRow;i++)
                    list.add(matrix[i][startCol]);
                return list;
            }
            //首行
            for(int i=startCol;i<=endCol;i++)
                list.add(matrix[startRow][i]);
            //末列
            for(int i=startRow+1;i<=endRow;i++)
                list.add(matrix[i][endCol]);
            //末行
            for(int i=endCol-1;i>=startCol;i--)
                list.add(matrix[endRow][i]);
            //首列
            for(int i=endRow-1;i>=startRow+1;i--)
                list.add(matrix[i][startCol]);
              
            startRow = startRow + 1;
            endRow = endRow - 1;
            startCol = startCol + 1;
            endCol = endCol - 1;
        }
        return list;
    }
}
```

### Solution 2

可以用一个额外矩阵存储，判断是否已经遍历过了，然后从左上角开始依次重复右、下、左、上的操作，如果没有遍历过就遍历，如果遍历过了就执行下一种操作

## 30 包含min函数的栈

定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

### Solution 1

使用一个辅助栈，每次压入元素都存当前最小的元素

```java
import java.util.LinkedList;

public class Solution {

    private LinkedList<Integer> stack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<>();
    
    public void push(int node) {
        if(stack.size()!=0){
            if(minStack.peekFirst() < node){
                minStack.addFirst(minStack.peekFirst());
            }else{
                minStack.addFirst(node);
            }
        }else{
            minStack.addFirst(node);
        }
        stack.addFirst(node);
    }
    
    public void pop() {
        minStack.removeFirst();
        stack.removeFirst();
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int min() {
        return minStack.peekFirst();
    }
}
```

## 31 栈的压入，弹出顺序

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

### Solution 1

使用一个辅助栈，每当不相同的时候就压入压入序列的内容，如果相同则双指针后移，最终判断弹出指针是否到最后，如果没有则说明还有弹出操作要进行，此时就按照弹出序列一步一步弹出，如果都相同则说明true

```java
import java.util.ArrayList;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(popA.length==0){
            return true;
        }
        int i=0,j=0;
        
        ArrayList<Integer> stack = new ArrayList<>();
        
        while(i<pushA.length&&j<popA.length){
            if(pushA[i]!=popA[j]){
                stack.add(0,pushA[i]);
                i++;
            }else if(pushA[i]==popA[j]){
                i++;
                j++;
            }
        }
        if(j==popA.length){
            return true;
        }
        while(j<popA.length){
            if(stack.remove(0)!=popA[j]){
                return false;
            }
            j++;
        }
        
        return true;
        
    }
}

```

## 32 从上到下打印二叉树

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

### Solution 1

层次遍历使用队列装载每个父节点的子节点，最终只要队列不为空就不断遍历子节点，然后依次装入队列

```java
import java.util.ArrayList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        
        TreeNode current = null;
        
        // 辅助队列
        ArrayList<TreeNode> childQueue = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        
        
        childQueue.add(root);
        while(childQueue.size()!=0){
            current = childQueue.get(0);
            if(current!=null){
                res.add(current.val);
                childQueue.add(current.left);
                childQueue.add(current.right);
            }
            childQueue.remove(0);
        }
        
        return res;
    }
    
    
}
```

## 33 按之字形顺序打印二叉树

请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

### Solution 1

使用两个栈去保存下一层的节点；当奇数层的时候，从当前oddLevel栈取出当前节点，并将left和right依次压入evenLevel栈中，偶数层同理

```java
import java.util.ArrayList;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        // 结果保存
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        if(pRoot==null){
            return res;
        }
        
        // 辅助栈
        ArrayList<TreeNode> oddLevel = new ArrayList<>();
        ArrayList<TreeNode> evenLevel = new ArrayList<>();
        ArrayList<Integer> level = new ArrayList<>();
        
        // 层次节点数量
        int currentLevel = 1;
        int nextLevel = 0;
        
        // 奇偶层判断
        boolean flag = true; // 奇数层
        
        oddLevel.add(0,pRoot);
        TreeNode current = null;
        
        while(oddLevel.size()!=0||evenLevel.size()!=0){
            if(flag){
                // 奇数层
                current = oddLevel.get(0);
                level.add(current.val);
                
                if(current.left!=null){
                    evenLevel.add(0,current.left);
                    nextLevel++;
                }
                if(current.right!=null){
                    evenLevel.add(0,current.right);
                    nextLevel++;
                }
                
                currentLevel--;
                oddLevel.remove(0);
            }else{
                // 偶数层
                current = evenLevel.get(0);
                level.add(current.val);
                
                if(current.right!=null){
                    oddLevel.add(0,current.right);
                    nextLevel++;
                }
                if(current.left!=null){
                    oddLevel.add(0,current.left);
                    nextLevel++;
                }
                
                currentLevel--;
                evenLevel.remove(0);
            }
            if(currentLevel==0){
                currentLevel = nextLevel;
                nextLevel = 0;
                flag=!flag;
                res.add(new ArrayList<>(level));
                level.clear();
            }
        }
        
        return res;
    }

}
```

## 34 二叉树中和为某一值的路径

输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

### Solution 1

先序遍历，如果计算出来的和等于target，返回当前路径

```java
import java.util.ArrayList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    private ArrayList<Integer> path = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    
    private void helper(TreeNode current,int target,int sumUp){
        path.add(current.val);
        
        if(current.left==null&&current.right==null){
            // 是叶子节点
            if(sumUp+current.val==target){
                res.add(new ArrayList<>(path));
            }
        }
        
        if(current.left!=null){
            helper(current.left,target,sumUp+current.val);
        }
        if(current.right!=null){
            helper(current.right,target,sumUp+current.val);
        }
        
        path.remove(path.size()-1);
     }
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root!=null){
            helper(root,target,0);
        }
        
        return res;
        
    }
}
```

## 35 复杂链表复制

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

### Solution 1

思路就是先复制一遍，在这一遍复制中，存储一个原始节点和复制节点的映射。后面再复制一遍随即节点的位置

```java
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
import java.util.*;
public class Solution {
    private RandomListNode copyHead = new RandomListNode(0);
    private HashMap<RandomListNode,RandomListNode> mapping = new HashMap<>();
    {
        mapping.put(null,null);
    }
    
    private void copyRandomNode(RandomListNode pHead){
        RandomListNode currentCopy = copyHead.next;
        RandomListNode current = pHead;
        
        while(current!=null){
            currentCopy.random = mapping.get(current.random);

            currentCopy = currentCopy.next;
            current = current.next;
        }
    }
    
    private void copyNext(RandomListNode pHead){
        // 不考虑随机指针的拷贝
        RandomListNode currentCopy = copyHead;
        RandomListNode current = pHead;
        
        while(current!=null){
            currentCopy.next = new RandomListNode(current.label);
            
            mapping.put(current,currentCopy.next); // 存储节点映射

            currentCopy = currentCopy.next;
            current = current.next;
        }
    }
    public RandomListNode Clone(RandomListNode pHead)
    {
        copyNext(pHead);
        copyRandomNode(pHead);
        
        return copyHead.next;
    }
}
```

## 36 序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树

二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树

```java
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
 
    public TreeNode(int val) {
        this.val = val;
 
    }
 
}
*/
public class Solution {
    String Serialize(TreeNode root) {
        if(root==null){
            return "#!";
        }
        StringBuilder sb=new StringBuilder();
        Serialize2(root,sb);
        return sb.toString();
  }
    void Serialize2(TreeNode root,StringBuilder sb){//前序遍历
        if(root==null){
            sb.append("#!");
            return;
        }
        sb.append(root.val);
        sb.append("!");
        Serialize2(root.left,sb);
        Serialize2(root.right,sb);
    }
     
     
    TreeNode Deserialize(String str) {
       if(str.length()==0)return null;
       String[] strs=str.split("!");
       return Deserialize2(strs);
         
  }
    int index=-1;
     
    TreeNode Deserialize2(String[] strs){
        index++;
        if(!strs[index].equals("#")){
            TreeNode root=new TreeNode(0);
            root.val=Integer.parseInt(strs[index]);
            root.left=Deserialize2(strs);
            root.right=Deserialize2(strs);
            return root;
        }
        return null;
    }
     
}
```

## 37 字符串的排列

```java
import java.util.*;
public class Solution {
    private HashSet<String> res = new HashSet<>();
    private void Permutation(char[] str, int index){
        // 递归排列
        if(index==str.length){
            res.add(String.valueOf(str));
            return;
        }else{
            for(int i=index;i<str.length;i++){
                char temp = str[i];
                str[i] = str[index];
                str[index] = temp;
                
                Permutation(str,index+1);
                
                temp = str[i];
                str[i] = str[index];
                str[index] = temp;
            }
        }
        return;
    }
    public ArrayList<String> Permutation(String str) {
        if(str==null||str.length()==0){
            return new ArrayList<String>(res);
        }
        Permutation(str.toCharArray(),0);
        return new ArrayList<String>(res);
    }
}
```

## 38 数组中出现次数超过一半的数字

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

### Solution 1 On

使用快排的 Partition 函数处理数组，缺点是会改变原本数组的顺序

### Solution 2 On

利用数组特性，如果有一个数字出现次数超过了数组长度的一半，其他所有数字的出现次数的和一定小于它出现的次数

```java
public class Solution {
    private boolean checkIfMoreThanHalp(int number,int[] array){
        int times = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]==number){
                times++;
            }
        }
        return times>(array.length/2);
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        int flag = 0;
        int temp=0;
        for(int i=0;i<array.length;i++){
            if(flag==0){
                temp = array[i];
                flag=1;
            }else{
                if(temp==array[i]){
                    flag++;
                }else{
                    flag--;
                }
            }
        }
        if(!checkIfMoreThanHalp(temp,array)){
            return 0;
        }
        return temp;
    }
}
```

## 39 最小的k个数字

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。

### Solution 1

先排序，再找前k

```java
import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
         
        ArrayList list = new ArrayList<>();
        if(input == null || input.length == 0 || input.length < k) return list;
        Arrays.sort(input);
        for(int i = 0; i < k; i++){
            list.add(input[i]);
        }
        return list;
    }
}
```

### Solution 2

堆排序

先创建容量为 k+1 的最小堆，将最开始的 k 个元素建立最小堆，之后每次添加新元素的时候，最小堆进行 k+1 位置元素的上浮操作



