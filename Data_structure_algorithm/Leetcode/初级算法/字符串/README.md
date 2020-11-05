# 初级算法 - 字符串<!-- omit in toc -->

- [反转字符串](#反转字符串)
  - [半数遍历](#半数遍历)
- [整数反转](#整数反转)
  - [数学方法](#数学方法)
- [字符串中的第一个唯一字符](#字符串中的第一个唯一字符)
  - [linkedHashMap 解法](#linkedhashmap-解法)
- [有效的字母异位词](#有效的字母异位词)
  - [哈希表解法](#哈希表解法)
  - [排序解法](#排序解法)
- [验证回文串](#验证回文串)
  - [原字符串上直接判断](#原字符串上直接判断)
- [字符串转换整数 (atoi)](#字符串转换整数-atoi)
  - [解法](#解法)
- [实现 strStr()](#实现-strstr)

## 反转字符串
```
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

 

示例 1：

输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：

输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```

### 半数遍历

```java
class Solution {
    private void exch(char[] s,int p,int q){
        char temp = s[p];
        s[p] = s[q];
        s[q] = temp;
    }
    public void reverseString(char[] s) {
        final int sLength = s.length;
        for(int i=0;i<sLength/2;i++){
            exch(s,i,sLength-1-i);
        }
    }
}
```

```
执行用时：
1 ms, 在所有 Java 提交中击败了99.99%的用户
内存消耗：
45.1 MB, 在所有 Java 提交中击败了92.30%的用户
```

## 整数反转
```
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
```

### 数学方法

方法：弹出和推入数字 & 溢出前进行检查
思路

我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。

算法

反转整数的方法可以与反转字符串进行类比。

我们想重复“弹出” xx 的最后一位数字，并将它“推入”到 rev 的后面。最后，rev 将与 xx 相反。

要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。

```
//pop operation:
pop = x % 10;
x /= 10;

//push operation:
temp = rev * 10 + pop;
rev = temp;

```
但是，这种方法很危险，因为当 temp=rev⋅10+pop 时会导致溢出。

幸运的是，事先检查这个语句是否会导致溢出很容易。

-2147483648 to 2147483647

只要 rev > INTMAX/10 那么一定会溢出

如果 rev = INTMAX/10 如果pop 大于7 一定会溢出

负数类推

```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

```

```
执行用时：
1 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
35.7 MB, 在所有 Java 提交中击败了84.43%的用户
```

## 字符串中的第一个唯一字符

```
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

 

示例：

s = "leetcode"
返回 0

s = "loveleetcode"
返回 2
 

提示：你可以假定该字符串只包含小写字母。
```

### linkedHashMap 解法

```java
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>(); 
        char[] temp = s.toCharArray();
        for(int i=0;i<temp.length;i++){
            if(!map.containsKey(temp[i])){
                map.put(temp[i],i);
            }else{
                map.put(temp[i],-1);
            }
        }

        for(char c : map.keySet()){
            if(map.get(c)!=-1){
                return map.get(c);
            }
        }

        return -1;
    }
}
```

```
执行用时：
27 ms, 在所有 Java 提交中击败了61.16%的用户
内存消耗：
38.9 MB, 在所有 Java 提交中击败了90.59%的用户
```

## 有效的字母异位词
```
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
```

### 哈希表解法

先用计数器表计算 ss，然后用 tt 减少计数器表中的每个字母的计数器。如果在任何时候计数器低于零，我们知道 tt 包含一个不在 ss 中的额外字母，并立即返回 FALSE。

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
        table[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
        table[t.charAt(i) - 'a']--;
        if (table[t.charAt(i) - 'a'] < 0) {
            return false;
        }
    }
    return true;
}

```

```
执行用时：
4 ms, 在所有 Java 提交中击败了64.67%的用户
内存消耗：
38.5 MB, 在所有 Java 提交中击败了94.22%的用户
```

### 排序解法

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

}
```

```
执行用时：
3 ms, 在所有 Java 提交中击败了85.97%的用户
内存消耗：
38.5 MB, 在所有 Java 提交中击败了91.68%的用户
```

## 验证回文串
```
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
```

### 原字符串上直接判断

直接在原字符串 ss 上使用双指针。在移动任意一个指针时，需要不断地向另一指针的方向移动，直到遇到一个字母或数字字符，或者两指针重合为止。也就是说，我们每次将指针移到下一个字母字符或数字字符，再判断这两个指针指向的字符是否相同。

```java
class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }
}

```

```
执行用时：
3 ms, 在所有 Java 提交中击败了93.90%的用户
内存消耗：
38.5 MB, 在所有 Java 提交中击败了88.31%的用户
```

## 字符串转换整数 (atoi)
```
请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```

### 解法

```java
class Solution {
    public int myAtoi(String s) {
        if(s.length()==0) return 0;
        char[] ss = s.toCharArray();

        int res = 0;

        // 排除所有空字符
        int i=0;
        while(i<ss.length){
            if(ss[i]==' ') i++;
            else break;
        }

        // 判断是否为正负号
        int flag = 1;
        if(i<ss.length&&ss[i]=='-') {
            flag=-1;
            i++;
        }else if(i<ss.length&&ss[i]=='+'){
            i++;
        }

        // 第一个非空字符是否为数字
        if(!(i<ss.length)) return 0;
        if(ss[i]<'0'||ss[i]>'9') return 0;

        // 尽可能多的组合数字
        while(i<ss.length){
            if(ss[i]>='0'&&ss[i]<='9'){
                int pop = Character.getNumericValue(ss[i]);
                if(flag==-1&&(res> Integer.MAX_VALUE/10 || (res==Integer.MAX_VALUE/10 && pop>8))) 
                    return Integer.MIN_VALUE;
                
                if(flag==1&&(res> Integer.MAX_VALUE/10 || (res==Integer.MAX_VALUE/10 && pop>7))) 
                    return Integer.MAX_VALUE;
                res = res * 10 + pop;
            }else{
                break;
            }
            i++;
        }

        return res*flag;
    }
}
```

```
执行用时：
2 ms, 在所有 Java 提交中击败了99.75%的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了85.07%的用户
```

## 实现 strStr()
```
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
```

