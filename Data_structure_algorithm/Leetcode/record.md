# Leetcode 练习记录<!-- omit in toc -->

- [July Challange](#july-challange)
  - [Week 1](#week-1)
    - [Binary Tree Level Order Traversal II](#binary-tree-level-order-traversal-ii)
- [Linked list practice](#linked-list-practice)
- [Tree](#tree)
  - [107. Binary Tree Level Order Traversal II](#107-binary-tree-level-order-traversal-ii)
- [Binary search](#binary-search)
- [String](#string)
- [Math](#math)
- [Array](#array)
  - [27. Remove Element E](#27-remove-element-e)
  - [26. Remove Duplicates from Sorted Array E](#26-remove-duplicates-from-sorted-array-e)
  - [80. Remove Duplicates from Sorted Array II M](#80-remove-duplicates-from-sorted-array-ii-m)
  - [189. Rotate Array](#189-rotate-array)
  - [41. First Missing Positive H](#41-first-missing-positive-h)
  - [134. Gas Station M](#134-gas-station-m)

## July Challange

### Week 1

#### Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
```
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
```
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            while (size-- > 0) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            result.add(0, level);
        }
        
        return result;
    }
}
```

## Linked list practice

* 206 Reverse Linked List
  * iteratively
    * 40 ms 15.3 MB python3
  * recursively
    * 28 ms	18.5 MB	python3
* 141 Linked List Cycle
  * 列表存储走过的节点
    * 1172 ms 5.4% faster 17 MB	100% memory less python3
  * 快慢指针
    * 52 ms	47% faster 16.9 MB 100% memory less python3
* 876 Middle of the Linked List
  * 列表存储走过的节点
    * 32 ms 40% faster	13.7 MB	7% memory less python3
  * 快慢指针
    * 32 ms	40% faster 14 MB 7% memory less	python3
* 24 Swap Nodes in Pairs
  * recursively
    * 16 ms 99.75% faster	13.8 MB	6.6% memory less python3
* 328 Odd Even Linked List
  * iteratiely
    * 36 ms	97% faster 15.9 MB 8.33% memory less python3
* 92 Reverse Linked List II
  * iteratively
    * 28 ms	82% faster 14 MB 7.4% memory less python3

待整理

---

## Tree

* 144 Binary Tree Preorder Traversal 进阶：迭代方法
  * recursively
    * 28 ms 73% faster	13.7 MB	91% memory less python3
* 94 Binary Tree Inorder Traversal
  * recursively
    * 32 ms	45% faster 14 MB 5% memory less	python3
* 145 Binary Tree Postorder Traversal
  * recursively
    * 32 ms	44% faster 13.8 MB	57% memory less python3
* 102 Binary Tree Level Order Traversal
  * 32 ms 80% faster	14 MB	72% memory less python3
### 107. Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
```
return its bottom-up level order traversal as:
```
[
  [15,7],
  [9,20],
  [3]
]
```

Solution 1: ```Queue``` and ```Collections.reverse()```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            while (size-- > 0) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            result.add(level);
        }

        Collections.reverse(result);
        return result;
    }
}
```

```
Runtime: 2 ms, faster than 18.87% of Java online submissions for Binary Tree Level Order Traversal II.
Memory Usage: 39.8 MB, less than 43.95% of Java online submissions for Binary Tree Level Order Traversal II.
```

Solution 2: Use append ```ArrayList.add(0,item)``` 省却了翻转，快一倍
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            while (size-- > 0) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            result.add(0, level);
        }
        
        return result;
    }
}
```

```
Runtime: 1 ms, faster than 80.39% of Java online submissions for Binary Tree Level Order Traversal II.
Memory Usage: 39.2 MB, less than 98.31% of Java online submissions for Binary Tree Level Order Traversal II.
```

Solution 3: 用 stack

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        Stack<List<Integer>> stack = new Stack();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            while (size-- > 0) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            stack.add(level);
        }

        while (!stack.isEmpty())
            result.add(stack.pop());

        return result;
    }
}
```

```
Runtime: 1 ms, faster than 80.39% of Java online submissions for Binary Tree Level Order Traversal II.
Memory Usage: 39.7 MB, less than 49.37% of Java online submissions for Binary Tree Level Order Traversal II.
```

* 103 Binary Tree Zigzag Level Order Traversal
  * 28 ms	88% faster 14.1 MB 13% memory less	python3

待整理

---

## Binary search

* 278 First Bad Version e
  * 二分查找
    ```
    Runtime: 14 ms, faster than 29.55% of Java online submissions for First Bad Version.
    Memory Usage: 35.9 MB, less than 80.52% of Java online submissions for First Bad Version.
    ```
* 35 Search Insert Position e
  * 二分查找
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
    Memory Usage: 41.2 MB, less than 5.06% of Java online submissions for Search Insert Position.
    ```
* 33 Search in Rotated Sorted Array m
  * 魔改版二分查找
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
    Memory Usage: 39.3 MB, less than 37.54% of Java online submissions for Search in Rotated Sorted Array.
    ```
* 81 Search in Rotated Sorted Array II m
  * 可以用二分查找，但是有点复杂，试着直接一遍遍历，结果还挺好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array II.
    Memory Usage: 39.4 MB, less than 35.34% of Java online submissions for Search in Rotated Sorted Array II.
    ```
* 153 Find Minimum in Rotated Sorted Array m
  * 直接一遍遍历，结果很好
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
    Memory Usage: 38.7 MB, less than 95.30% of Java online submissions for Find Minimum in Rotated Sorted Array.
    ```
* 154 Find Minimum in Rotated Sorted Array II h
  * 我裂开了，还是一遍遍历就行了。我怀疑他的测试用例是随机找的pivat点，所以点的分布更接近中心，平均下来可能是$N/2$，比起复杂条件的二分，直接一边遍历的开销甚至更小。
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    Memory Usage: 39.5 MB, less than 36.19% of Java online submissions for Find Minimum in Rotated Sorted Array II.
    ```

## String

* 28 Implement strStr() e
  * What should we return when needle is an empty string? This is a great question to ask during an interview. For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
    ```
    Runtime: 1 ms, faster than 64.82% of Java online submissions for Implement strStr().
    Memory Usage: 39.4 MB, less than 23.61% of Java online submissions for Implement strStr().
    ```
* 14 Longest Common Prefix e
  * ```substring```
      ```
      Runtime: 3 ms, faster than 27.74% of Java online submissions for Longest Common Prefix.
      Memory Usage: 39.8 MB, less than 6.60% of Java online submissions for Longest Common Prefix.
      ```
* 58 Length of Last Word e
  * ```trim```
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Length of Last Word.
    Memory Usage: 37.5 MB, less than 74.80% of Java online submissions for Length of Last Word.
    ```
* 387 First Unique Character in a String e
  * Can be optimized
    ```
    Runtime: 37 ms, faster than 10.01% of Java online submissions for First Unique Character in a String.
    Memory Usage: 39.6 MB, less than 87.99% of Java online submissions for First Unique Character in a String.
    ```
* 383 Ransom Note e
  * ```HashMap, toCharArray```
    ```
    Runtime: 9 ms, faster than 46.81% of Java online submissions for Ransom Note.
    Memory Usage: 40.8 MB, less than 17.90% of Java online submissions for Ransom Note.
    ```
* 344 Reverse String e
  * Nothing to optimized
    ```
    Runtime: 1 ms, faster than 56.87% of Java online submissions for Reverse String.
    Memory Usage: 46.2 MB, less than 58.97% of Java online submissions for Reverse String.
    ```

## Math

* 7 Reverse Integer e
  * ```Math.pow```, ```Integer.MAX_VALUE```
    ```
    Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse Integer.
    Memory Usage: 36.6 MB, less than 76.60% of Java online submissions for Reverse Integer.
    ```
* 165 Compare Version Numbers m
  * ```str.split()```, ```\\.```, ```Integer.parseInt()```,```()? : ```
    ```
    Runtime: 1 ms, faster than 90.04% of Java online submissions for Compare Version Numbers.
    Memory Usage: 37.6 MB, less than 39.17% of Java online submissions for Compare Version Numbers.
    ```
* 66 Plus One e
  * Nothing to say
    ```
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Plus One.
    Memory Usage: 38.3 MB, less than 19.00% of Java online submissions for Plus One.
    ```
* 8 String to Integer (atoi) m
  * ```StringBuilder```
    ```
    Runtime: 4 ms, faster than 25.51% of Java online submissions for String to Integer (atoi).
    Memory Usage: 40.5 MB, less than 6.15% of Java online submissions for String to Integer (atoi).
    ```
* 258 Add Digits
  * Nothing to say
    ```
    Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Digits.
    Memory Usage: 36.9 MB, less than 47.08% of Java online submissions for Add Digits.
    ```
* 67 Add Binary
  * ```Integer.toBinaryString```, ```Integer.parseInt(str,radix)```, ```StringBuilder```, ```StringBuilder.reverse().toString()```
    ```
    Runtime: 2 ms, faster than 76.96% of Java online submissions for Add Binary.
    Memory Usage: 37.9 MB, less than 90.16% of Java online submissions for Add Binary.
    ```
## Array

### 27. Remove Element E

Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

```
Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
```

```
Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
```

思路很简单，从尾部开始遍历

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int tail = 0;
        for (int current : nums) {
            if (current != val) {
                nums[tail++] = current;
            }
        }
        return tail;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
Memory Usage: 40 MB, less than 5.06% of Java online submissions for Remove Element.
```

### 26. Remove Duplicates from Sorted Array E

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
```
Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
```
Example 2:
```
Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
```

思路是用双指针，一个指针遍历数组，另一个指针每当有不同元素的时候就后移一位，并把不同的那个元素放过来

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[j]!=nums[i]){
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
Memory Usage: 41.3 MB, less than 55.16% of Java online submissions for Remove Duplicates from Sorted Array.
```

### 80. Remove Duplicates from Sorted Array II M

Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
```
Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
```
Example 2:
```
Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
```

因为数组是sorted，可以如下考虑，使用一个指针，将原先数组的元素依次放入按顺序排的位置，但是最多同一个元素不能超过两个

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 3){
            return nums.length;
        }
        int count= 0;
        int tempCount = 0;
        for(int i=count+1;i<nums.length;i++){
            if(nums[count]==nums[i])
                tempCount++;
            
            if(nums[count]==nums[i] && tempCount<2){
                nums[++count] =nums[i];
            }else if(nums[count]!=nums[i] ){
                tempCount=0;
                nums[++count] =nums[i];

            }
        }
        return count+1;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
Memory Usage: 39.6 MB, less than 59.90% of Java online submissions for Remove Duplicates from Sorted Array II.
```

### 189. Rotate Array

Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

Could you do it in-place with O(1) extra space?
 

Example 1:
```
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```
Example 2:
```
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```

Solution 1: in-place with O(1) extra space

很简单就是将数组向后旋转k次

```java
class Solution {
    public void rotate(int[] nums, int k) {
        while(k>0){
            int last = nums[nums.length-1];
            for(int i =nums.length-1;i>0;i--){
                nums[i] = nums[i-1];
            }
            nums[0] = last;
            k--;
        }
        
    }
}
```

```
Runtime: 248 ms, faster than 5.63% of Java online submissions for Rotate Array.
Memory Usage: 39.5 MB, less than 98.82% of Java online submissions for Rotate Array.
```

Solution 2: in-place with O(1) extra space

思路也是翻转，但是快很多。先将数组整体翻转，然后用k将数组分为两部分，两部分分别翻转即可。这里要注意k，需要对数组长度取余，防止越界

```java
class Solution {
    private void reverse(int[] nums, int i,int j){
            while(i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
    }
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
        
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Array.
Memory Usage: 40 MB, less than 64.98% of Java online submissions for Rotate Array.
```

### 41. First Missing Positive H

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
```
Input: [1,2,0]
Output: 3
```
Example 2:
```
Input: [3,4,-1,1]
Output: 2
```
Example 3:
```
Input: [7,8,9,11,12]
Output: 1
```

Note:

Your algorithm should run in O(n) time and uses constant extra space.

思路就是把数字放到对应下标的位置，然后从头遍历一遍看第一个不对应的地方

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length==0) return 1;
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1&&nums[i]<nums.length&&nums[i]>0&&nums[i]!=nums[nums[i]-1]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i]; 
                nums[i] = temp;
            }
        }
        
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }
}
```

```
Runtime: 24 ms, faster than 5.26% of Java online submissions for First Missing Positive.
Memory Usage: 40 MB, less than 5.07% of Java online submissions for First Missing Positive.
```

### 134. Gas Station M

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

* If there exists a solution, it is guaranteed to be unique.
* Both input arrays are non-empty and have the same length.
* Each element in the input arrays is a non-negative integer.

Example 1:
```
Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
```
Example 2:
```
Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
```

Solution 1: 直观上的方法就是遍历每个点，并将其作为起点，循环一边看有还有没有剩下

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for(int i=0;i<gas.length;i++){
            tank += gas[i]-cost[i];
        }
        if(tank<0) return -1;
        
        for(int i=0;i<gas.length;i++){
            tank = 0;
            for(int j = i;j<i+gas.length;j++){
                tank+=gas[j%gas.length];
                tank-=cost[j%gas.length];
                if(tank<0)
                    break;
            }
            if(tank>=0){
                return i;
            }
        }
        
        return -1;
    }
}
```

Solution 2: 贪心算法，这个问题的核心是gas的和一定要比cost大才能走一圈，所以前面任意gas比cost小的情况，都会被考虑到后面gas大于cost的里面

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for(int i=0;i<gas.length;i++){
            tank += gas[i]-cost[i];
        }
        if(tank<0) return -1;
        
        int start = 0;
        int remain = 0;
        for(int i=0;i<gas.length;i++){
            if(gas[i]+remain < cost[i]){
                start = i+1;
                remain = 0;
            }else{
                remain += gas[i]-cost[i];
            }
        }
        return start;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Gas Station.
Memory Usage: 40.1 MB, less than 24.14% of Java online submissions for Gas Station.
```