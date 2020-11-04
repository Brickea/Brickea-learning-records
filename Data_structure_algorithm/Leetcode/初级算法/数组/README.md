# 初级算法 - 数组 <!-- omit in toc -->

- [删除排序数组中的重复项](#删除排序数组中的重复项)
  - [双指针解法](#双指针解法)
- [买卖股票的最佳时机 II](#买卖股票的最佳时机-ii)
  - [动态规划解法](#动态规划解法)
  - [峰谷法](#峰谷法)
  - [一边遍历](#一边遍历)
- [旋转数组](#旋转数组)
  - [暴力解法](#暴力解法)
  - [使用反转](#使用反转)
- [存在重复元素](#存在重复元素)
  - [Set 解法](#set-解法)
- [只出现一次的数字](#只出现一次的数字)
  - [异或解法](#异或解法)
- [两个数组的交集 II](#两个数组的交集-ii)
  - [哈希表](#哈希表)
- [加一](#加一)
  - [解法](#解法)
- [移动零](#移动零)
  - [暴力法](#暴力法)
  - [快慢指针](#快慢指针)
- [两数之和](#两数之和)
- [有效的数独](#有效的数独)
  - [一边迭代](#一边迭代)
- [旋转图像](#旋转图像)
  - [解法](#解法-1)

## 删除排序数组中的重复项
```
给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
 

说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

### 双指针解法

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        // 从左向右遍历，双指针
        if(nums.length==0) return 0;
        int pre = 0;
        int next = 0;

        while(next<nums.length){
            if(nums[next]==nums[pre]){
                next++;
            }else{
                nums[++pre]=nums[next++];
            }
        }

        return pre+1;
    }
}
```

```
执行用时：
1 ms, 在所有 Java 提交中击败了95.43%的用户
内存消耗：
40.2 MB, 在所有 Java 提交中击败了90.33%的用户
```

## 买卖股票的最佳时机 II

```
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 

提示：

1 <= prices.length <= 3 * 10 ^ 4
0 <= prices[i] <= 10 ^ 4

```

### 动态规划解法

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 对于股票买卖，一共有三种选择，买入、卖出、休息
        // 买入一定在卖出之前，卖出一定在买入之后
        // 休息可以是“持有股票的休息”或者是“不持有股票的休息”
        
        // 今天的最高利润依据昨天的选择，上动规
        
        // 不限制交易次数
        
//         // 定义状态
//         // dp[i][s] 其中，i 代表第 i 天， s 有两种取值， 0 或者 1，分别代表 没有持有股票 和 持有股票
//         final int DAYS = prices.length;
//         final int STOCK_STATUS = 2;
        
//         int[][] dp = new int[DAYS+1][STOCK_STATUS];
        
//         // basecase
//         // dp[i][0]=0 第 0 天，买卖还未开始，利润应为 0
//         // dp[0][1]=-infinity 第 0 天，不可能持有股票，用负无穷表示不可能（方便 max 函数选择）
        
//         dp[0][0] = 0;
//         dp[0][1] = Integer.MIN_VALUE;
        
//         // 状态转移 i 代表 第 i 天
//         // dp[i][0] = max(dp[i-1][0],dp[i-1][1] + prices[i])
//         // 第 i 天没有持有股票，前一天可以没有持有股票，也可以持有股票但是在第 i 天卖出
//         // dp[i][1] = max(dp[i-1][1],dp[i-1][0] - prices[i])
//         // 第 i 天持有股票，前一天可以持有股票，也可以不持有股票但是在第 i 天买入
        
//         for(int i=1;i<=DAYS;i++){
//             dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i-1]);
//             dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i-1]);
//         }
        
//         return dp[DAYS][0];
        
        // 空间压缩
        // 定义状态
        // dp[i][s] 其中，i 代表第 i 天， s 有两种取值， 0 或者 1，分别代表 没有持有股票 和 持有股票
        final int DAYS = prices.length;
        final int STOCK_STATUS = 2;
        
        // int[][] dp = new int[DAYS+1][STOCK_STATUS];
        int status_0 = 0;
        int status_1 = Integer.MIN_VALUE;
        
        // basecase
        // dp[i][0]=0 第 0 天，买卖还未开始，利润应为 0
        // dp[0][1]=-infinity 第 0 天，不可能持有股票，用负无穷表示不可能（方便 max 函数选择）
        
        // dp[0][0] = 0;
        // dp[0][1] = Integer.MIN_VALUE;
        
        // 状态转移 i 代表 第 i 天
        // dp[i][0] = max(dp[i-1][0],dp[i-1][1] + prices[i])
        // 第 i 天没有持有股票，前一天可以没有持有股票，也可以持有股票但是在第 i 天卖出
        // dp[i][1] = max(dp[i-1][1],dp[i-1][0] - prices[i])
        // 第 i 天持有股票，前一天可以持有股票，也可以不持有股票但是在第 i 天买入（因为只能买入一次，此时前一天利润必定为 0）
        
        for(int i=1;i<=DAYS;i++){
            int temp = status_0;
            status_0 = Math.max(status_0,status_1+prices[i-1]);
            status_1 = Math.max(status_1,temp-prices[i-1]);
        }
        
        return status_0;
        
    }
}
```

```
执行用时：
2 ms, 在所有 Java 提交中击败了27.00%的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了72.40%的用户
```

### 峰谷法

算法

假设给定的数组为：

[7, 1, 5, 3, 6, 4]

如果我们在图表上绘制给定数组中的数字，我们将会得到：

![](https://pic.leetcode-cn.com/d447f96d20d1cfded20a5d08993b3658ed08e295ecc9aea300ad5e3f4466e0fe-file_1555699515174)

如果我们分析图表，那么我们的兴趣点是连续的峰和谷。

关键是我们需要考虑到紧跟谷的每一个峰值以最大化利润。如果我们试图跳过其中一个峰值来获取更多利润，那么我们最终将失去其中一笔交易中获得的利润，从而导致总利润的降低。

```java
class Solution {
    public int maxProfit(int[] prices) {
        int valleyIndex = 0;
        int peekIndex = 0;
        int maxProfit = 0;

        int index = 0;
        while(index < prices.length-1){
            while(index<prices.length-1 && prices[index] >= prices[index+1]){
                // 寻找低谷
                index++;
            }
            valleyIndex = index;
            while(index < prices.length-1 && prices[index] <= prices[index+1]){
                // 寻找顶峰
                index++;
            }
            peekIndex = index;
            maxProfit += prices[peekIndex] - prices[valleyIndex];
        }
        
        return maxProfit;
    }
}
```

```
执行用时：
1 ms, 在所有 Java 提交中击败了99.54%的用户
内存消耗：
38.3 MB, 在所有 Java 提交中击败了89.04%的用户
```

### 一边遍历

```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}

```

```
执行用时：
1 ms, 在所有 Java 提交中击败了99.54%的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了79.45%的用户
```

## 旋转数组

```
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。
```

### 暴力解法

旋转 k 次，每次将数组旋转 1 个元素

```java
class Solution {
    private void exch(int[] array,int p, int q){
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp; 
    }
    private void rotateOne(int[] array){
        // 将最后一个数字移动到前面，后续数字向后移
        final int arrayLength = array.length;
        int temp = array[arrayLength-1]; // 保存最后一个数字
        
        for(int i=arrayLength-2;i>=0;i--){
            exch(array,i,i+1);
        }
        array[0] = temp;
    }
    public void rotate(int[] nums, int k) {
        while(k>0){
            rotateOne(nums);
            k--;
        }
    }
}
```

```
执行用时：
321 ms, 在所有 Java 提交中击败了5.08%的用户
内存消耗：
39.1 MB, 在所有 Java 提交中击败了83.43%的用户
```

### 使用反转

这个方法基于这个事实：当我们旋转数组 k 次， k\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。

在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-kn−k 个元素，就能得到想要的结果。

原始数组                  : 1 2 3 4 5 6 7
反转所有数字后             : 7 6 5 4 3 2 1
反转前 k 个数字后          : 5 6 7 4 3 2 1
反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果

```java
public class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
39.1 MB, 在所有 Java 提交中击败了82.98%的用户
```

## 存在重复元素

```

给定一个整数数组，判断是否存在重复元素。

如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。


示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true

```

### Set 解法

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int i:nums){
            if(set.contains(i)){
                return true;
            }
            set.add(i);
        }

        return false;
    }
}
```

```

执行用时：
8 ms, 在所有 Java 提交中击败了56.01%的用户
内存消耗：
44.5 MB, 在所有 Java 提交中击败了53.33%的用户
```

## 只出现一次的数字

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4

### 异或解法

```java
class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}

```

```
执行用时：
1 ms, 在所有 Java 提交中击败了99.82%的用户
内存消耗：
38.9 MB, 在所有 Java 提交中击败了81.99%的用户
```

## 两个数组的交集 II

```
给定两个数组，编写一个函数来计算它们的交集。

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 

说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

```

### 哈希表

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}

```

```
执行用时：
3 ms, 在所有 Java 提交中击败了84.84%的用户
内存消耗：
38.5 MB, 在所有 Java 提交中击败了91.62%的用户
```

## 加一

```
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
示例 2:

输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
```

### 解法

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int addMore = 0;
        digits[digits.length-1]+=1;
        if(digits[digits.length-1]==10){
            digits[digits.length-1]=0;
            addMore = 1;

            for(int i=digits.length-2;i>=0;i--){
                if(digits[i]+addMore==10){
                    digits[i]=0;
                    addMore=1;
                }else{
                    digits[i]+=addMore;
                    addMore=0;
                }
            }

            if(digits[0]==0){
                int[] res = new int[digits.length+1];
                for(int i=digits.length;i>0;i--){
                    res[i] = digits[i-1];
                }
                res[0] = 1;
                digits = res;
            }
        }

        return digits;
    }
}
```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
37.2 MB, 在所有 Java 提交中击败了57.45%的用户
```

## 移动零

```
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
```

### 暴力法

```java
class Solution {
    private void exch(int[] array,int p,int q){
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }
    public void moveZeroes(int[] nums) {
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]==0){
                for(int j=i;j<nums.length-1;j++){
                    if(nums[j+1]!=0){
                        exch(nums,j,j+1);
                    }
                }
            }
        }
    }
}
```

```
执行用时：
11 ms, 在所有 Java 提交中击败了5.34%的用户
内存消耗：
39 MB, 在所有 Java 提交中击败了66.10%的用户
```

### 快慢指针

```java
class Solution {
    private void exch(int[] array,int p,int q){
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while(i<nums.length){
            if(nums[i]!=0){
                exch(nums,i,j);
                j++;
            }
            i++;
        }
    }
}
```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了92.44%的用户
```

## 两数之和

```
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                return new int[] {map.get(nums[i]),i};
            }
            map.put(target-nums[i],i);
        }
        return new int[]{1,1,1};
    }
}
```

```
执行用时：
2 ms, 在所有 Java 提交中击败了99.53%的用户
内存消耗：
38.5 MB, 在所有 Java 提交中击败了89.39%的用户
```

## 有效的数独
```
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。

示例 1:

输入:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: true
示例 2:

输入:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: false
解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
说明:

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
给定数独序列只包含数字 1-9 和字符 '.' 。
给定数独永远是 9x9 形式的。
```

### 一边迭代

```java
class Solution {
  public boolean isValidSudoku(char[][] board) {
    // init data
    HashMap<Integer, Integer> [] rows = new HashMap[9];
    HashMap<Integer, Integer> [] columns = new HashMap[9];
    HashMap<Integer, Integer> [] boxes = new HashMap[9];
    for (int i = 0; i < 9; i++) {
      rows[i] = new HashMap<Integer, Integer>();
      columns[i] = new HashMap<Integer, Integer>();
      boxes[i] = new HashMap<Integer, Integer>();
    }

    // validate a board
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char num = board[i][j];
        if (num != '.') {
          int n = (int)num;
          int box_index = (i / 3 ) * 3 + j / 3;

          // keep the current cell value
          rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
          columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
          boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

          // check if this value has been already seen before
          if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
            return false;
        }
      }
    }

    return true;
  }
}

```

```
执行用时：
3 ms, 在所有 Java 提交中击败了53.77%
的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了81.30%的用户
```

## 旋转图像
```
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

### 解法

```java
class Solution {
  public void rotate(int[][] matrix) {
    int n = matrix.length;

    // transpose matrix
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int tmp = matrix[j][i];
        matrix[j][i] = matrix[i][j];
        matrix[i][j] = tmp;
      }
    }
    // reverse each row
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[i][n - j - 1];
        matrix[i][n - j - 1] = tmp;
      }
    }
  }
}

```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
38.6 MB, 在所有 Java 提交中击败了84.34%的用户
```