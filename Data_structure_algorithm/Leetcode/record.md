# Leetcode 练习记录<!-- omit in toc -->

- [July Challange](#july-challange)
  - [Week 1](#week-1)
    - [Binary Tree Level Order Traversal II](#binary-tree-level-order-traversal-ii)
    - [Prison Cells After N Days](#prison-cells-after-n-days)
    - [Ugly Number ||](#ugly-number-)
    - [Hamming Distance](#hamming-distance)
- [Linked list practice](#linked-list-practice)
  - [206 Reverse Linked List E](#206-reverse-linked-list-e)
  - [141. Linked List Cycle E](#141-linked-list-cycle-e)
  - [876. Middle of the Linked List E](#876-middle-of-the-linked-list-e)
  - [237. Delete Node in a Linked List E](#237-delete-node-in-a-linked-list-e)
  - [19. Remove Nth Node From End of List M](#19-remove-nth-node-from-end-of-list-m)
  - [83. Remove Duplicates from Sorted List E](#83-remove-duplicates-from-sorted-list-e)
  - [203. Remove Linked List Elements E](#203-remove-linked-list-elements-e)
  - [82. Remove Duplicates from Sorted List II M](#82-remove-duplicates-from-sorted-list-ii-m)
  - [369. Plus One Linked List](#369-plus-one-linked-list)
  - [25 Reverse Nodes in k-Group H](#25-reverse-nodes-in-k-group-h)
- [Tree](#tree)
  - [102 Binary Tree Level Order Traversal M](#102-binary-tree-level-order-traversal-m)
  - [124 Binary Tree Maximum Path Sum H](#124-binary-tree-maximum-path-sum-h)
  - [107. Binary Tree Level Order Traversal II](#107-binary-tree-level-order-traversal-ii)
  - [494. Target Sum](#494-target-sum)
    - [Solution 1 回溯](#solution-1-回溯)
  - [103. Binary Tree Zigzag Level Order Traversal](#103-binary-tree-zigzag-level-order-traversal)
    - [Solution 1 - 奇偶层次遍历](#solution-1---奇偶层次遍历)
  - [100. Same Tree](#100-same-tree)
    - [Solution 1 BFS or DFS](#solution-1-bfs-or-dfs)
  - [101. Symmetric Tree](#101-symmetric-tree)
    - [Solution 1 - BFS or DFS](#solution-1---bfs-or-dfs)
  - [226. Invert Binary Tree](#226-invert-binary-tree)
    - [Solution 1](#solution-1)
  - [257. Binary Tree Paths](#257-binary-tree-paths)
    - [Solution 1 - 回溯法](#solution-1---回溯法)
  - [112. Path Sum](#112-path-sum)
  - [113. Path Sum II](#113-path-sum-ii)
  - [129. Sum Root to Leaf Numbers](#129-sum-root-to-leaf-numbers)
- [Binary search](#binary-search)
- [String](#string)
  - [3 Longest Substring Without Repeating Characters M](#3-longest-substring-without-repeating-characters-m)
- [Math](#math)
  - [15. 3Sum M](#15-3sum-m)
- [Array](#array)
  - [27. Remove Element E](#27-remove-element-e)
  - [26. Remove Duplicates from Sorted Array E](#26-remove-duplicates-from-sorted-array-e)
  - [80. Remove Duplicates from Sorted Array II M](#80-remove-duplicates-from-sorted-array-ii-m)
  - [189. Rotate Array](#189-rotate-array)
  - [41. First Missing Positive H](#41-first-missing-positive-h)
  - [134. Gas Station M](#134-gas-station-m)
  - [146. LRU Cache](#146-lru-cache)
  - [78. Subsets](#78-subsets)
  - [118. Pascal's Triangle](#118-pascals-triangle)
  - [119. Pascal's Triangle II](#119-pascals-triangle-ii)
  - [169. Majority Element](#169-majority-element)
- [Bit Manipulation](#bit-manipulation)
  - [389. Find the Difference E](#389-find-the-difference-e)
  - [136. Single Number E](#136-single-number-e)
  - [137. Single Number II M](#137-single-number-ii-m)
- [Dynamic Programming](#dynamic-programming)
  - [322. Coin Change](#322-coin-change)
  - [300. Longest Increasing Subsequence M](#300-longest-increasing-subsequence-m)
  - [53. Maximum Subarray E](#53-maximum-subarray-e)
  - [518. Coin Change 2 M](#518-coin-change-2-m)
  - [416. Partition Equal Subset Sum M](#416-partition-equal-subset-sum-m)
  - [509. Fibonacci Number E](#509-fibonacci-number-e)
  - [887. Super Egg Drop](#887-super-egg-drop)
    - [Solution 1](#solution-1-1)
    - [Solution 2 二分查找优化](#solution-2-二分查找优化)
- [回溯法](#回溯法)
  - [980. Unique Paths III](#980-unique-paths-iii)

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

#### Prison Cells After N Days

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

Example 1:
```
Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: 
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
```

Example 2:
```
Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]
```

Note:
```
cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
```

Solution1 每次得到新的cells就存在set里面，然后如果新的cells出现了重复，则说明找到了循环节，将N对这个set取模，最后即可得到对应的cells结果

Solution2 用数学方法证明循环 为14

[Reference](https://math.stackexchange.com/questions/3311568/why-does-this-pattern-repeat-after-14-cycles-instead-of-256-can-you-give-a-proo/3311963#3311963)

```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] result = new int[cells.length];
        N= N%14 == 0?14:N%14;
        int i, length = cells.length-1;
        while(N > 0)
        {
            for(i=1; i<length; i++)
            {
                if(cells[i-1]==cells[i+1])
                    result[i]=1;
                else
                    result[i]=0;
            }
            N--;
            cells=Arrays.copyOf(result, length+1);
        }
        return result;
    }
}
```

```
Runtime: 2 ms, faster than 83.39% of Java online submissions for Prison Cells After N Days.
Memory Usage: 40.5 MB, less than 8.64% of Java online submissions for Prison Cells After N Days.
```

#### Ugly Number ||

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:
```
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
```
思路就是按照顺序生成对应的ugly number
```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int indexOf2 = 0, indexOf3 = 0, indexOf5 = 0;
        int factorOf2 = 2, factorOf3 = 3, factorOf5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factorOf2,factorOf3),factorOf5);
            ugly[i] = min;
            if(factorOf2 == min)
                factorOf2 = 2*ugly[++indexOf2];
            if(factorOf3 == min)
                factorOf3 = 3*ugly[++indexOf3];
            if(factorOf5 == min)
                factorOf5 = 5*ugly[++indexOf5];
        }
        return ugly[n-1];
    }
}
```

#### Hamming Distance


The [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.
```
Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
```
The above arrows point to positions where the corresponding bits are different.

思路就是位运算

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int hammingCount = 0;
        int maxJudgementNum = Math.max(Integer.toBinaryString(x).length(),Integer.toBinaryString(y).length());
        for(int i=0;i<maxJudgementNum;i++){
            if((x&1)!=(y&1)){
                hammingCount++;
            }
            x >>=1;
            y >>=1;
        }
        return hammingCount;
    }
}
```

## Linked list practice

### 206 Reverse Linked List E

Reverse a singly linked list.

Example:
```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode current = head;
        ListNode last = null;
        while(current!=null){
            ListNode temp = current.next;
            current.next = last;
            last = current;
            current = temp;
        }
        head = last;
        return head;
    }
    
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
Memory Usage: 40.5 MB, less than 5.05% of Java online submissions for Reverse Linked List.
```

### 141. Linked List Cycle E

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Example 1:
```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```
![](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

Example 2:
```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```
![](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

用快慢指针，一个跨两步遍历，一个一步一步遍历，在有环的情况下，两个指针会碰在一起

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next != null && slow != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
Memory Usage: 39.7 MB, less than 45.18% of Java online submissions for Linked List Cycle.
```
### 876. Middle of the Linked List E

Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.

Example 1:
```
Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
```

Example 2:
```
Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.
```

Note:

The number of nodes in the given list will be between 1 and 100.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        if(head==null) return null;
        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Middle of the Linked List.
Memory Usage: 38.5 MB, less than 9.96% of Java online submissions for Middle of the Linked List.
```

### 237. Delete Node in a Linked List E

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:

Example 1:
```
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
```
Example 2:
```
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
```

Note:
```
The linked list will have at least two elements.
All of the nodes' values will be unique.
The given node will not be the tail and it will always be a valid node of the linked list.
Do not return anything from your function.
```

直接赋值遍历即可

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        
        while(node.next!=null){
            node.val = node.next.val;
            if(node.next.next==null){
                node.next = null;
                break;
            }
            node = node.next;
        }
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Delete Node in a Linked List.
Memory Usage: 41.5 MB, less than 5.02% of Java online submissions for Delete Node in a Linked List.
```

### 19. Remove Nth Node From End of List M

Given a linked list, remove the n-th node from the end of list and return its head.

Example:
```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

思路就是双指针，一个指向尾，另一个和尾巴保持n的距离，最后用链表删除结点的方法即可。这里有个情况就是删除的节点是头节点，这时候直接返回```head.next```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        ListNode targetPre = head;
        while(n>0&&tail.next!=null){
            tail = tail.next;
            n--;
        }
        if(n==0){
            while(tail.next!=null){
                tail=tail.next;
                targetPre=targetPre.next;
            }
            targetPre.next = targetPre.next.next;
        }else{
            return head.next;
        }
        return head;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
Memory Usage: 39.4 MB, less than 11.40% of Java online submissions for Remove Nth Node From End of List.
```

### 83. Remove Duplicates from Sorted List E

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
```
Input: 1->1->2
Output: 1->2
```
Example 2:
```
Input: 1->1->2->3->3
Output: 1->2->3
```
双指针，一个往后移动作为判断位，一个只有在遇到了不同数值的时候才会后移

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode judge = head.next;
        ListNode current = head;
        while(judge!=null){
            if(judge.val!=current.val){
                current.next.val = judge.val;
                current=current.next;
            }
            judge=judge.next;
        }
        current.next = null;
        return head;
    }
}
```

```
Runtime: 1 ms, faster than 26.30% of Java online submissions for Remove Duplicates from Sorted List.
Memory Usage: 41.4 MB, less than 10.33% of Java online submissions for Remove Duplicates from Sorted List.
```

### 203. Remove Linked List Elements E

Remove all elements from a linked list of integers that have value val.

Example:
```
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```

思路很简单，链表删除，这里做个小处理就是加个空head头，这样比较方便删除元素

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode myHead = new ListNode();
        myHead.next=head;
        ListNode pre = myHead;
        ListNode current = head;
        
        while(current!=null){
            if(current.val==val){
                pre.next=current.next;
                current=pre.next;
            }else{
                current=current.next;
                pre=pre.next;
            }
        }
        return myHead.next;
    }
}
```

```
Runtime: 1 ms, faster than 87.02% of Java online submissions for Remove Linked List Elements.
Memory Usage: 47.1 MB, less than 5.01% of Java online submissions for Remove Linked List Elements.
```

### 82. Remove Duplicates from Sorted List II M

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Return the linked list sorted as well.

Example 1:
```
Input: 1->2->3->3->4->4->5
Output: 1->2->5
```
Example 2:
```
Input: 1->1->1->2->3
Output: 2->3
```

递归一波

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if( head != null && head.next != null) {            
		    if(head.next.val != head.val) {
			    head.next = deleteDuplicates(head.next);
		    } else {
			    while(head.next != null && head.val == head.next.val)
				    head = head.next;
			    return deleteDuplicates(head.next);
		    }
	    }
	    return head;
    }
}
```

```
Runtime: 1 ms, faster than 31.15% of Java online submissions for Remove Duplicates from Sorted List II.
Memory Usage: 40.9 MB, less than 11.04% of Java online submissions for Remove Duplicates from Sorted List II.
```

### 369. Plus One Linked List

Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list.

Example

```
Input: 1->2->3
Output: 1->2->4
```

现将链表翻转然后找到第一位不为 9 的数字，加一再翻转回来，遍历过的为 9 的数字均置为 0 。

* 24 Swap Nodes in Pairs
  * recursively
    * 16 ms 99.75% faster	13.8 MB	6.6% memory less python3
* 328 Odd Even Linked List
  * iteratiely
    * 36 ms	97% faster 15.9 MB 8.33% memory less python3
* 92 Reverse Linked List II
  * iteratively
    * 28 ms	82% faster 14 MB 7.4% memory less python3


### 25 Reverse Nodes in k-Group H

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

```

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
```

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.


Solution 1

思路就是一遍遍历，然后每当计数等于k的时候就将这一串翻转

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        if(k<2){
            return head;
        }
        ListNode fakeHead = new ListNode(0,head); // 创建虚假head
        
        ListNode currentBeforeReverseNode = fakeHead;
        ListNode currentNavigateNode = head;
        
        
        int count = 0;
        while(currentNavigateNode!=null){
            count++;
            if(count>=k){
                ListNode temp = currentNavigateNode.next; // 因为反转过后currentNavigateNode的next会发生变化，先保存一下
                ListNode tempB = currentBeforeReverseNode.next; // 反转后翻转链的前置节点的next会变成后面串的前置节点
                this.reverseSubLinkedList(currentBeforeReverseNode,currentNavigateNode.next);
                currentNavigateNode = temp;
                currentBeforeReverseNode = tempB;
                count=0;
            }else{
                currentNavigateNode = currentNavigateNode.next;
            }
        }
        return fakeHead.next;
    }
    public void reverseSubLinkedList(ListNode beforeNode,ListNode afterNode){
        // beforeNode 是需要反转的链表的前一个节点
        // afterNode 是需要反转的链表的后一个节点
        ListNode currentNode = beforeNode.next;
        ListNode nextNode = currentNode.next;
        
        currentNode.next = afterNode;
        while(nextNode!=afterNode){
            ListNode temp = nextNode.next; // 保存一下next指向
            nextNode.next = currentNode; // 将currentNode 和 nextNode翻转
            currentNode = nextNode;
            nextNode = temp;
        }
        // 将befroeNode指向反转后的尾巴
        beforeNode.next = currentNode;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
Memory Usage: 39.9 MB, less than 33.82% of Java online submissions for Reverse Nodes in k-Group.
```

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

### 102 Binary Tree Level Order Traversal M

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
```
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
```

Solution 1

思路是层次遍历，但是因为返回的结果需要注意如何单独存储每一层的信息，详情见代码.

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 初始化结果存储
        List<List<Integer>> res = new ArrayList<>();
        
        if(root==null){
            return res;
        }
        
        // 初始化子节点队列
        LinkedList<TreeNode> children = new LinkedList<>();
        children.addLast(root);
        
        int currentLevelChildNum = 1; // 当前层节点数
        int nextLevelChildNum = 0; // 下一层节点数
        
        List<Integer> currentLevelVal = new ArrayList<>(); // 当前层节点值存储
        
        while(children.size()!=0){
            // 取出节点
            TreeNode current = children.removeFirst();
            // 将此节点值放入当前层次中
            currentLevelVal.add(current.val);
            // 当前层次计数减一
            currentLevelChildNum--;
            
            
            // 将子节点加入队列
            if(current.left!=null){
                children.addLast(current.left);
                // 下一层节点数计数加一
                nextLevelChildNum++;
            }
            if(current.right!=null){
                children.addLast(current.right);
                // 下一层节点数计数加一
                nextLevelChildNum++;
            }
            
            // 判断当前层是否结束遍历
            if(currentLevelChildNum==0){
                // 存储当前层的遍历结果
                res.add(currentLevelVal);
                // 注意不可以用clear来清空currentLevelVal存储，因为存到res中的是引用，结果就是res中的结果也都被清空了
                // 清空当前层存储
                currentLevelVal=new ArrayList<Integer>();
                // 进入下一层
                currentLevelChildNum = nextLevelChildNum;
                // 下一层计数清零
                nextLevelChildNum = 0;
            }
        }
        
        return res;
        
        
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Level Order Traversal.
Memory Usage: 39.4 MB, less than 75.36% of Java online submissions for Binary Tree Level Order Traversal.
```

### 124 Binary Tree Maximum Path Sum H

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:
```
Input: [1,2,3]

       1
      / \
     2   3

Output: 6
```
Example 2:
```
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
```

Solution 1

深度优先搜索+剪枝

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
    private int res = Integer.MIN_VALUE;
    
    private int dfs(TreeNode root){
            
        if(root == null)
            return 0;

        int rootVal = root.val;
        int left = Math.max(0,this.dfs(root.left)); // 深度优先搜索寻找左最大
        int right = Math.max(0,this.dfs(root.right)); // 深度优先搜索找右最大
        // 这里和0取更大是为了去掉子节点最大为负数的情况，这样相当于直接剪枝

        int val = rootVal + left + right ; // 计算包含当前根的最大路径

        res = Math.max(val,res); // 更新最大

        return rootVal+Math.max(left,right); // 将其作为路径之一返回
    }
    
    public int maxPathSum(TreeNode root) {
        // 深度优先搜索，因为要找的是某一结点到某一结点的最大路径，可以考虑回溯的思路
        
        this.res = root.val;
        
        dfs(root);
        
        return res;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Maximum Path Sum.
Memory Usage: 41.4 MB, less than 40.11% of Java online submissions for Binary Tree Maximum Path Sum.
```

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

### 494. Target Sum

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

```
Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
```

There are 5 ways to assign symbols to make the sum of nums be target 3.
 
Constraints:

* The length of the given array is positive and will not exceed 20.
* The sum of elements in the given array will not exceed 1000.
* Your output answer is guaranteed to be fitted in a 32-bit integer.

#### Solution 1 回溯

```java
class Solution {
    private int result = 0;
    
    private void helper(int[] nums,int index, int rest){
        // 回溯函数
        // base case
        if(index == nums.length){
            if(rest==0){
                result++;
            }
            return ;
        }
        
        // 考虑 +
        rest -= nums[index];
        helper(nums,index+1,rest);
        // 回溯
        rest += nums[index];
        
        // 考虑 -
        rest += nums[index];
        helper(nums,index+1,rest);
        // 回溯
        rest -= nums[index];
        
        
    }
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length==0){
            return 0;
        }    
        
        helper(nums,0,S);
        
        return result;
    }
}
```

时间复杂度为$2^n$

```
Runtime: 561 ms, faster than 16.79% of Java online submissions for Target Sum.
Memory Usage: 37.3 MB, less than 80.83% of Java online submissions for Target Sum.
```

### 103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

```
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
```

#### Solution 1 - 奇偶层次遍历

基本思路为层次遍历。

使用两个栈来保存奇偶层的遍历，奇数层存入奇数栈，取出的时候孩子先从左到右遍历存入偶数栈。偶数层存入偶数栈，取出的时候孩子先从右到左遍历存入奇数栈

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        // 结果存放
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 两个栈，分别遍历奇偶层次
        LinkedList<TreeNode> leftStack = new LinkedList<>();
        LinkedList<TreeNode> rightStack = new LinkedList<>();

        // 起始为奇遍历
        leftStack.addLast(root);
        boolean flag = false; // 代表奇数层次遍历

        // 开始奇偶层次遍历
        while (leftStack.size() != 0 || rightStack.size() != 0) {

            List<Integer> temp = new ArrayList<>(); // 临时存放每层遍历结果

            if (!flag) {
                // 奇数层
                while (leftStack.size() != 0) {
                    TreeNode leftItem = leftStack.removeLast();
                    temp.add(leftItem.val);
                    if (leftItem.left != null) {
                        rightStack.addLast(leftItem.left);
                    } 
                    if (leftItem.right != null) {
                        rightStack.addLast(leftItem.right);
                    }

                }

                flag = true;
            } else {
                // 偶数层
                while (rightStack.size() != 0) {
                    TreeNode rightItem = rightStack.removeLast();
                    temp.add(rightItem.val);
                    if (rightItem.right != null) {
                        leftStack.addLast(rightItem.right);
                    } 
                    if (rightItem.left != null) {
                        leftStack.addLast(rightItem.left);
                    }

                }

                flag = false;
            }

            res.add(temp);
        }
        return res;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
Memory Usage: 39.5 MB, less than 63.82% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
```

### 100. Same Tree

Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

```
Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
```

#### Solution 1 BFS or DFS

广度优先搜索和深度优先搜索均可，两种不同的遍历节点策略

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
    public boolean BFS(TreeNode p, TreeNode q){
        // 广度优先搜索
        // 使用 queue 数据结构存储节点
        LinkedList<TreeNode> queueP = new LinkedList<>();
        LinkedList<TreeNode> queueQ = new LinkedList<>();
        queueP.addLast(p);
        queueQ.addLast(q);

        while(queueP.size()!=0&&queueQ.size()!=0){
            TreeNode pItem = queueP.removeLast();
            TreeNode qItem = queueQ.removeLast();
            if(pItem!=null&&qItem!=null&&pItem.val==qItem.val){
                // 两者相同
                queueP.addFirst(pItem.right);
                queueQ.addFirst(qItem.right);
                queueP.addFirst(pItem.left);
                queueQ.addFirst(qItem.left);

            }else if(pItem==qItem){
                // 均为 null
                continue;
            }else{
                // 既不为空也不相同
                return false;
            }
        }

        // 对比完所有节点
        return true;
    }
    public boolean DFS(TreeNode p, TreeNode q) {
        // 深度优先搜索
        // 使用 stack 数据结构存储节点
        LinkedList<TreeNode> stackP = new LinkedList<>();
        LinkedList<TreeNode> stackQ = new LinkedList<>();
        stackP.addLast(p);
        stackQ.addLast(q);

        while(stackP.size()!=0&&stackQ.size()!=0){
            TreeNode pItem = stackP.removeLast();
            TreeNode qItem = stackQ.removeLast();

            if(pItem!=null&&qItem!=null&&pItem.val==qItem.val){
                // 两者相同
                stackP.addLast(pItem.right);
                stackQ.addLast(qItem.right);
                stackP.addLast(pItem.left);
                stackQ.addLast(qItem.left);

            }else if(pItem==qItem){
                // 均为 null
                continue;
            }else{
                // 既不为空也不相同
                return false;
            }
        }

        // DFS 对比完所有节点
        return true;

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return BFS(p,q);
    }
}
```

BFS
```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
Memory Usage: 36.5 MB, less than 98.21% of Java online submissions for Same Tree.
```

DFS
```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
Memory Usage: 37 MB, less than 56.72% of Java online submissions for Same Tree.
```

stack 的结构比 queue 占用空间更多，符合直觉

### 101. Symmetric Tree

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

```
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
```

#### Solution 1 - BFS or DFS

修改改一下 100中的方法 就可以直接用了，考虑对比根节点下两个子树是否对称一样，修改一下遍历的方向即可

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
    public boolean BFS(TreeNode p, TreeNode q){
        // 广度优先搜索
        // 使用 queue 数据结构存储节点
        LinkedList<TreeNode> queueP = new LinkedList<>();
        LinkedList<TreeNode> queueQ = new LinkedList<>();
        queueP.addLast(p);
        queueQ.addLast(q);

        while(queueP.size()!=0&&queueQ.size()!=0){
            TreeNode pItem = queueP.removeLast();
            TreeNode qItem = queueQ.removeLast();
            if(pItem!=null&&qItem!=null&&pItem.val==qItem.val){
                // 两者相同
                queueP.addFirst(pItem.right);
                queueQ.addFirst(qItem.left);
                queueP.addFirst(pItem.left);
                queueQ.addFirst(qItem.right);

            }else if(pItem==qItem){
                // 均为 null
                continue;
            }else{
                // 既不为空也不相同
                return false;
            }
        }

        // 对比完所有节点
        return true;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null||root.right==null&&root.left==null){
            return true;
        }
        return BFS(root.left,root.right);
    }
}
```

```
Runtime: 1 ms, faster than 38.39% of Java online submissions for Symmetric Tree.
Memory Usage: 39 MB, less than 36.49% of Java online submissions for Symmetric Tree.
```

### 226. Invert Binary Tree

Invert a binary tree.
```
Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

#### Solution 1 

广度优先搜索并交换孩子

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
    public void exchangeNode(TreeNode node){
        // 交换两个节点
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        
    }
    public void BFS(TreeNode node){
        if(node==null)
            return ;
        // 广度优先搜索
        // 使用 queue 数据结构存储节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(node);

        while(queue.size()!=0){
            TreeNode item = queue.removeFirst();
            exchangeNode(item);
            if(item.left!=null){
                queue.addLast(item.left);
            }
            if(item.right!=null){
                queue.addLast(item.right);
            }
            
            
        }

    }
    public TreeNode invertTree(TreeNode root) {
        BFS(root);
        return root;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
Memory Usage: 36.9 MB, less than 76.92% of Java online submissions for Invert Binary Tree.
```

### 257. Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.
```
Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
```

#### Solution 1 - 回溯法

深度优先遍历，使用回溯

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
import java.lang.*;
class Solution {
    public void allPaths(TreeNode root, LinkedList<TreeNode> stack,List<String> res){
        if(root.left==null&&root.right==null){
            // 到达叶子节点，记录结果
            // 默认 root 首先装入
            Iterator it = stack.iterator();
            StringBuilder tempRes = new StringBuilder();
            
            TreeNode tempRoot = stack.removeFirst();
            tempRes.append(tempRoot.val);
            for(TreeNode node : stack){
                if(node!=null){
                    tempRes.append("->"+node.val);
                }
            }
            res.add(tempRes.toString());
            stack.addFirst(tempRoot);
            return;
        }
        
        if(root.left!=null){
            stack.addLast(root.left);
            allPaths(root.left,stack,res);
            stack.removeLast();
        }
        
        if(root.right!=null){
            stack.addLast(root.right);
            allPaths(root.right,stack,res);
            stack.removeLast();
        }
        
        
    }
    public List<String> binaryTreePaths(TreeNode root) {
        // 回溯法
        List<String> res = new ArrayList<String>();
        if(root==null){
            return res;
        }
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        
        stack.addLast(root);
        allPaths(root,stack,res);
        
        return res;
        
    }
}
```

```
Runtime: 13 ms, faster than 15.37% of Java online submissions for Binary Tree Paths.
Memory Usage: 40 MB, less than 31.43% of Java online submissions for Binary Tree Paths.
```

### 112. Path Sum

```
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
    
    private boolean helper(TreeNode node,int sum){
        if(node==null)
            return false;
        if(node.left==null&&node.right==null&&sum==node.val){
            // 到达叶子
            return true;
        }
        
        return this.helper(node.left,sum-node.val)||this.helper(node.right,sum-node.val);
        
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        // 回溯法
        return this.helper(root,sum);
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
Memory Usage: 39.1 MB, less than 89.35% of Java online submissions for Path Sum.
```

### 113. Path Sum II

```
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
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
    
    private List<List<Integer>> res;
    private LinkedList<Integer> tempList;
    private void helper(TreeNode node,int sum){
        if(node!=null){
            tempList.addLast(node.val);
        }else{
            return;
        }
        if(node.left==null&&node.right==null){
            if(sum==node.val){
                res.add(new ArrayList<Integer>(this.tempList));
                tempList.removeLast();
                return;
            }else{
                tempList.removeLast();
                return;
            }
            
        }
        this.helper(node.left,sum-node.val);
        this.helper(node.right,sum-node.val);
        tempList.removeLast();
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // 回溯法
        res = new ArrayList<>();
        tempList = new LinkedList<>();
        
        
        this.helper(root,sum);
        
        return res;
        
    }
}
```

### 129. Sum Root to Leaf Numbers

```
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
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
    private List<List<Integer>> res;
    private LinkedList<Integer> tempList;
    private void helper(TreeNode node){
        if(node!=null){
            tempList.addLast(node.val);
        }else{
            return;
        }
        if(node.left==null&&node.right==null){
            res.add(new ArrayList<Integer>(this.tempList));
            tempList.removeLast();
            return;
            
        }
        this.helper(node.left);
        this.helper(node.right);
        tempList.removeLast();
    }
    public int sumNumbers(TreeNode root) {
        // 回溯法
        res = new ArrayList<>();
        tempList = new LinkedList<>();
        
        
        this.helper(root);
        
        int sumUp = 0;
        
        for(int i=0;i<this.res.size();i++){
            int temp = 0;
            for(int j:this.res.get(i)){
                temp = temp*10+j;
            }
            sumUp+=temp;
        }
        
        return sumUp;
    }
}
```

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

### 3 Longest Substring Without Repeating Characters M

Given a string, find the length of the longest substring without repeating characters.

Example 1:
```
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```
Example 2:
```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```
Example 3:
```
Input: "pwwkew"
Output: 3
```
```
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

Solution 1

双指针思路，一个前置指针向后遍历字符，一个后置指针指向当前子串的开头。每当前置指针向后遍历一个新的字符的时候，就开始从后置指针开始遍历到前置指针-1的位置，判断是否有重复。有重复就将后指针指向子串中重复的位置，没有重复就继续遍历。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 双指针
        int i =0;
        int j = 0;
        int sLen = s.length();
        int res = 0;
        
        while(i<sLen){
            // 前置指针向后遍历
            String c = s.substring(i,i+1);
            
            // 从后置指针判断是否和已有的重复
            int m =j;
            while(m<i){
                if(s.substring(m,m+1).equals(c)){
                    // 重复，重置计数并保存最大
                    res = Math.max(res,i-j);
                    // 将后置指针指向重复的位置
                    j= m+1;
                    break;
                }
                m++;
            }
            
            // 无重复，更新最长长度
            res = Math.max(res,i-j+1);
            
            i++;
        }
        
        return res;
    }
}
```

```
Runtime: 8 ms, faster than 48.91% of Java online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 39.8 MB, less than 34.62% of Java online submissions for Longest Substring Without Repeating Characters.
```

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

### 15. 3Sum M

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:
```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

Solution 1

整体思路就是先排序，然后对于每一对计算其相反数，然后用二分查找寻找解，需要注意避免重复的条件

```java

class Solution {
    static public int binarySearch(int[] nums,int lo,int hi,int target){
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(nums[mid]==target){
                System.out.println(mid);
                return mid;
            }
            if(target>nums[mid]){
                lo = mid+1;
            }else if(target<nums[mid]){
                hi = mid-1;
            }
        }
        return -1;
    }
    static public List<List<Integer>> threeSum(int[] nums) {
        if(nums==null){
            return null;
        }
        // 初始化结果
        List<List<Integer>> res = new ArrayList<>();
        // 先将原数组排序
        Arrays.sort(nums);

        // 对于每一对 nums[i] 和 nums[j] 二分查找 对应的 nums[m] = -(nums[i]+nums[j])
        for(int i =0;i<nums.length-2;i++){
                if(i>0&&nums[i]==nums[i-1]){
                    continue;
                }
            for(int j=nums.length-1;j>i+1;j--){

                
                if(j<nums.length-1&&nums[j+1]==nums[j]){
                    continue;
                }
                // 二分查找
                int lo = i+1;
                int hi =j-1;
                int target = 0-nums[i]-nums[j];
                int m = binarySearch(nums,lo,hi,target);

                if(m!=-1){
                    // 存在一个解
                    res.add(Arrays.asList(nums[i],nums[j],nums[m]));
                }

            }
        }

        return res;
    }
}
```

```
Runtime: 358 ms, faster than 20.77% of Java online submissions for 3Sum.
Memory Usage: 43.4 MB, less than 63.13% of Java online submissions for 3Sum.
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

### 146. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
```
LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

Solution 1 HashMap 和 LinkedList

```java
class LRUCache {
    
    private int capacity;
    private HashMap cache;
    private LinkedList order;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap();
        this.order = new LinkedList();
    }
    
    public int get(int key) {
        if(this.cache.containsKey(key)){
            this.order.remove(new Integer(key));
            this.order.addFirst(new Integer(key));
            return (int)this.cache.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Object k = this.cache.get(key);
        if(k!=null){
            // 存在
            this.order.remove(new Integer(key));
            this.order.addFirst(new Integer(key));
            this.cache.put(key,value); // 覆盖更新
        }else{
            this.order.addFirst(new Integer(key));
            this.cache.put(key,value);
        }
        if(this.cache.size()>this.capacity){
            // 超出容量限制
            this.cache.remove((int)this.order.removeLast());
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

[引申 - 三种方法手撕LRU算法](https://leetcode-cn.com/problems/lru-cache/solution/san-chong-fang-fa-dai-ni-shou-si-lrusuan-fa-javaba/)

### 78. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

```
Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

### 118. Pascal's Triangle

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

![](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

In Pascal's triangle, each number is the sum of the two numbers directly above it.

```
Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows==0){
            return res;
        }
        res.add(new ArrayList<>(Arrays.asList(1)));        
        for(int i=2;i<=numRows;i++){
            List<Integer> temp = new ArrayList<>();
            List<Integer> pre = res.get(i-2);
            temp.add(1);
            for(int j=2;j<i;j++){
                temp.add(pre.get(j-2)+pre.get(j-1));
            }
            temp.add(1);
            res.add(temp);
        }
        return res;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.
Memory Usage: 37.2 MB, less than 55.03% of Java online submissions for Pascal's Triangle.
```

### 119. Pascal's Triangle II

Given an integer rowIndex, return the rowIndexth row of the Pascal's triangle.

Notice that the row index starts from 0.

![](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Follow up:

Could you optimize your algorithm to use only O(k) extra space?

```
Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]
 

Constraints:

0 <= rowIndex <= 40
```

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex);
        row.add(1);
        int prev = row.get(0);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 0; j < i - 1; j++) {
                prev = row.set(j + 1, row.get(j + 1) + prev);
            }
            row.add(1);
        }
        return row;
    }
}
```

```
Runtime: 2 ms, faster than 11.30% of Java online submissions for Pascal's Triangle II.
Memory Usage: 38.4 MB, less than 10.25% of Java online submissions for Pascal's Triangle II.
```

### 169. Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

```
Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
```

```java
class Solution {
    public int majorityElement(int[] nums) {
        // 摩尔投票法
        // 因为已知一定有超过 n/2 的数字
        // 遍历一遍，出现相同 count ++ 不同 count--，count = 0 的时候 res 为当前的数字，最后res即为要求的数字
        // 时间复杂度为O(n)，空间复杂度为O(1)
        
        int res = nums[0];
        int count = 0;
        
        for(int i:nums){
            if(count==0){
                count=1;
                res = i;
            }else if(res==i){
                count++;
            }else{
                count--;
            }
        }
        return res;
    }
}
```

## Bit Manipulation

### 389. Find the Difference E

Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:
```
Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.
```

Solution 1:

思路：先排序然后按位查询，因为Arrays.sort针对基本数据类型的实现是quicksort，所以时间复杂度是$O(N\log N)$

```java
class Solution {
    public char findTheDifference(String s, String t) {
        char[] sS = s.toCharArray();
        char[] tT = t.toCharArray();
        
        Arrays.sort(sS);
        Arrays.sort(tT);
        
        for(int i=0;i<tT.length;i++){
            if(i>sS.length-1){
                return tT[i];
            }
            else if(tT[i]!=sS[i]){
                return tT[i];
            }
        }
        
        return ' ';
    }
}
```

```
Runtime: 6 ms, faster than 25.86% of Java online submissions for Find the Difference.
Memory Usage: 39.8 MB, less than 5.03% of Java online submissions for Find the Difference.
```

Solution 2 位运算，利用异或的性质来得到新添加的字符

```java
class Solution {
    public char findTheDifference(String s, String t) {
        char[] sS = s.toCharArray();
        char[] tT = t.toCharArray();
        char res = 0;
        for(int i=0;i<tT.length;i++){
            if(i>sS.length-1){
                res ^= tT[i];
            }else{
                res ^= tT[i];
                res ^= sS[i];
            }
        }
        return res;
    }
}
```

```
Runtime: 2 ms, faster than 56.20% of Java online submissions for Find the Difference.
Memory Usage: 40.1 MB, less than 5.03% of Java online submissions for Find the Difference.
```

### 136. Single Number E

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
```
Input: [2,2,1]
Output: 1
```
Example 2:
```
Input: [4,1,2,1,2]
Output: 4
```

这个就非常明显了，直接用位运算异或即可

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++){
            res ^= nums[i];
        }
        return res;
    }
}
```

```
Runtime: 1 ms, faster than 63.74% of Java online submissions for Single Number.
Memory Usage: 44.2 MB, less than 6.16% of Java online submissions for Single Number.
```

### 137. Single Number II M

Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
```
Input: [2,2,3,2]
Output: 3
```
Example 2:
```
Input: [0,1,0,1,0,1,99]
Output: 99
```

遍历位数，如果所有数字在这位上的总和不是3的倍数，那么单次出现的数字在这一位一定有值，逐位拼凑出来这个数字 

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res=0;
        for(int i=0;i<32;i++){
            int count = 0;
            for(int j:nums){
                if((j>>i & 1)==1){
                    count++;
                }
            }
            if(count%3!=0){
                res |= 1 << i;
            }
        }
        return res;
    }
}
```
```
Runtime: 3 ms, faster than 57.22% of Java online submissions for Single Number II.
Memory Usage: 39.3 MB, less than 50.72% of Java online submissions for Single Number II.
```

## Dynamic Programming 

### 322. Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
```
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
```
Example 2:
```
Input: coins = [2], amount = 3
Output: -1
```

Note:
You may assume that you have an infinite number of each kind of coin.

Solution 1

使用动态规划的思想，自底向上，添加一个记忆空间用来记忆已计算的结果，加速计算

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 在求所有子问题 + 1 的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
```

```
Runtime: 10 ms, faster than 95.20% of Java online submissions for Coin Change.
Memory Usage: 39 MB, less than 72.68% of Java online submissions for Coin Change.
```

### 300. Longest Increasing Subsequence M

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.

Solution 1

思路就是动态规划，自顶向下求解问题

```java
class Solution {
    private int[] dp;
    public int lengthOfLIS(int[] nums) {
        // 动态规划初始化
        // dp[i]代表的是以nums第i个结尾的最长增长子序列的长度
        dp = new int[nums.length];
        Arrays.fill(dp,1); // dp每个的base case是自己
        
        // 自顶向下求解问题
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<i;j++){
                // 状态转移
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        
        // 遍历子状态集返回最大的长度
        int res = 0;
        for(int temp : dp){
            res = Math.max(res,temp);
        }
        return res;
    }
}
```

```
Runtime: 19 ms, faster than 22.62% of Java online submissions for Longest Increasing Subsequence.
Memory Usage: 39.1 MB, less than 15.50% of Java online submissions for Longest Increasing Subsequence.
```

### 53. Maximum Subarray E

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Solution 1

自顶向下动态规划


```java
class Solution {
    private int[] dp;
    public int maxSubArray(int[] nums) {
        // 动态规划初始化
        // 状态为： dp[i] 是以 nums[i] 结尾的数组中，最大的和，
        dp = new int[nums.length];
        // 最前面的dp没有更前的字数组
        dp[0] = nums[0];
        
        // 自顶向下解决问题
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(nums[i],nums[i]+dp[i-1]); 
        }
        
        // 遍历状态集，寻找最大
        int res = Integer.MIN_VALUE;
        for(int item:dp){
            res = Math.max(res,item);
        }
        return res;
        
    }
}
```

```
Runtime: 1 ms, faster than 75.96% of Java online submissions for Maximum Subarray.
Memory Usage: 41.5 MB, less than 9.09% of Java online submissions for Maximum Subarray.
```

### 518. Coin Change 2 M

You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 

Example 1:
```
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```

Example 2:
```
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
```

Example 3:
```
Input: amount = 10, coins = [10] 
Output: 1
```

Note:

You can assume that
```
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
```

Solution 1

完全背包问题，自顶向下动态规划。不压缩状态

```java

class Solution {
    public int change(int amount, int[] coins) {
        // 处理输入边界条件
        if(amount == 0){
            return 1;
        }
        if(coins.length == 0){
            return 0;
        }
        // 完全背包问题
        // 考虑状态，有两个，一个是使用了前几种面值的硬币，一个是当前要凑的总额
        // dp[i][j] 表示为，在使用前i种面额的硬币凑 j 的方法数量
        int[][] dp = new int[coins.length+1][amount+1];
        
        // base case是，i=0的时候，意味着没有面额给是使用，所以dp[0][:]=0
        // j=0的时候，不论用那种面额，都只有一种结果，所以
        for(int i=1;i<coins.length+1;i++){
            dp[i][0] = 1;
        }
        
        // 动态规划，自顶向下
        // 有两种前置 一种是使用前i种面额凑钱，一种是使用前i-1种凑钱，但因为是可能性的总数，所以要相加
        for(int i=1;i<coins.length+1;i++){
            // 因为定义的i比coins实际对应面额的下标小1，所以减1
            for(int j=1;j<amount+1;j++){
                // 防止越界
                if(j - coins[i-1]>=0){
                    dp[i][j] = dp[i-1][j]+dp[i][j-coins[i-1]];  
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        
        // 返回结果
        return dp[coins.length][amount];
       
    }
}
```

```
Runtime: 23 ms, faster than 11.28% of Java online submissions for Coin Change 2.
Memory Usage: 48.5 MB, less than 6.51% of Java online submissions for Coin Change 2.
```

Solution 2

状态压缩，因为dp[i] 只和 dp[i-1] 有关，故可以压缩状态，减少空间复杂度


```java
class Solution {
    // private int[][] dp;
    private int[] dp;
    
    public int change(int amount, int[] coins) {
        // 处理输入边界条件
        if(amount == 0){
            return 1;
        }
        if(coins.length == 0){
            return 0;
        }
        // 完全背包问题
        // 考虑状态，有两个，一个是使用了前几种面值的硬币，一个是当前要凑的总额
        // dp[i][j] 表示为，在使用前i种面额的硬币凑 j 的方法数量
        // dp = new int[coins.length+1][amount+1];
        dp = new int[amount+1];
        
        // base case是，i=0的时候，意味着没有面额给是使用，所以dp[0][:]=0
        // j=0的时候，不论用那种面额，都只有一种结果，所以
        // for(int i=1;i<coins.length+1;i++){
        //     dp[i][0] = 1;
        // }
        dp[0]=1;
        
        // 动态规划，自顶向下
        // 有两种前置 一种是使用前i种面额凑钱，一种是使用前i-1种凑钱，但因为是可能性的总数，所以要相加
        // for(int i=1;i<coins.length+1;i++){
        //     // 因为定义的i比coins实际对应面额的下标小1，所以减1
        //     for(int j=1;j<amount+1;j++){
        //         // 防止越界
        //         if(j - coins[i-1]>=0){
        //             dp[i][j] = dp[i-1][j]+dp[i][j-coins[i-1]];  
        //         }else{
        //             dp[i][j] = dp[i-1][j];
        //         }
        //     }
        // }
        for(int i=0;i<coins.length;i++){
            // 因为定义的i比coins实际对应面额的下标小1，所以减1
            for(int j=1;j<amount+1;j++){
                // 防止越界
                if(j - coins[i]>=0){
                    dp[j] = dp[j]+dp[j-coins[i]];  
                }
            }
        }
        
        
        // 返回结果
        return dp[amount];
    }
}
```

```
Runtime: 4 ms, faster than 54.50% of Java online submissions for Coin Change 2.
Memory Usage: 36.9 MB, less than 75.83% of Java online submissions for Coin Change 2.
```

### 416. Partition Equal Subset Sum M

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
```
Each of the array element will not exceed 100.
The array size will not exceed 200.
```

Example 1:
```
Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
```
 

Example 2:
```
Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
```

Solution 

```java
class Solution {
    public boolean canPartition(int[] nums) {
        // 背包问题
        // 抽象为：用一个背包，容量为 sum(nums)/2 ，物品重量为 nums ，是否能恰好装满
        
        // 计算 sum
        int sum = 0;
        for(int i:nums)
            sum+=i;
        
        // 基本判断
        if(sum%2!=0)
            // sum为奇数，不可能划分成两个相等的子集
            return false;
        
        if(nums.length==0)
            // 没有数字可以用来凑
            return false;
        
//         // 状态定义 dp[i][j] 代表 容量为 j 的时候，用前 i 个数字是否能恰好装满
//         boolean[][] dp = new boolean[nums.length+1][sum/2+1];
        
//         // basecase 
//         // 当 j 为 0 的时候，不用装就是已满
//         // 当 i 为 0 的时候，除了 j 为 0 ，其他情况一定装不满
//         for(int i=0;i<=nums.length;i++){
//             dp[i][0] = true;
//         }
        
//         // 状态转移
//         for(int i=1;i<=nums.length;i++){
//             for(int j=sum/2;j>=0;j--){
//                 if(j-nums[i-1]<0){
//                     // 容量不足
//                     dp[i][j] = dp[i-1][j];
//                 }else{
//                     // 不装入或者装入
//                     dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
//                 }
//             }
//         }
        
//         return dp[nums.length][sum/2];
        
        // 空间压缩
        // 注意到 dp[i][j] 都是通过上一行 dp[i-1][..] 转移过来的，之前的数据都不会再使用了
        
        // 状态定义 dp[i][j] 代表 容量为 j 的时候，用前 i 个数字是否能恰好装满
        boolean[] dp = new boolean[sum/2+1];
        
        // basecase 
        // 当 j 为 0 的时候，不用装就是已满
        // 当 i 为 0 的时候，除了 j 为 0 ，其他情况一定装不满
        
        dp[0] = true;
        
        
        // 状态转移
        for(int i=1;i<=nums.length;i++){
            for(int j=sum/2;j>=0;j--){
                if(j-nums[i-1]<0){
                    // 容量不足
                    dp[j] = dp[j];
                }else{
                    // 不装入或者装入
                    dp[j] = dp[j] || dp[j-nums[i-1]];
                }
            }
        }
        
        return dp[sum/2];
        
    }
}

```

```
Runtime: 26 ms, faster than 57.99% of Java online submissions for Partition Equal Subset Sum.
Memory Usage: 37.5 MB, less than 92.20% of Java online submissions for Partition Equal Subset Sum.
```

### 509. Fibonacci Number E

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).

Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

```

```java
class Solution {
    public int fib(int N) {
        int f0 = 0;
        int f1 = 1;
        if(N<1){
            return 0;
        }
        
        for(int i=1;i<N;i++){
            int temp = f0+f1;
            f0 = f1;
            f1 = temp;
        }
        
        return f1;
    }
}
```

```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
Memory Usage: 36.2 MB, less than 64.26% of Java online submissions for Fibonacci Number.
```

### 887. Super Egg Drop

You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

```

Example 1:

Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4
 

Note:

1 <= K <= 100
1 <= N <= 10000
```

#### Solution 1

```java
class Solution {

    private int[][] dpTable;
    public int dp(int k,int n){
        int res = Integer.MAX_VALUE;
        // 状态转移
        if(k==1){
            this.dpTable[k][n] = n;
            return n;
        }
        // dp[k][n] 代表的是 还有 k 个鸡蛋去测试 n 层
        // basecase 就是 k=1 的时候，一个鸡蛋只能线性查找
        // basecase 另外一个就是 n=0 的时候，不需要扔鸡蛋
        
        if(n==0){
            this.dpTable[k][n]=0;
            return 0;
        }

        if(this.dpTable[k][n]!=Integer.MAX_VALUE){
            return this.dpTable[k][n];
        }

        for(int i=1;i<=n;i++){
            res = Math.min(res,Math.max(this.dp(k-1,i-1),this.dp(k,n-i))+1);
        }
        this.dpTable[k][n] = res;
        return res;
    }
    public int superEggDrop(int K, int N) {
        // 备忘录 dpTable
        this.dpTable = new int[K+1][N+1];
        for(int i=0;i<dpTable.length;i++){
            for(int j=0;j<dpTable[i].length;j++){
                this.dpTable[i][j] = Integer.MAX_VALUE;
            }
        }

        // 动态规划
        this.dp(K,N);

        return this.dpTable[K][N];


    }

}
```

超时

#### Solution 2 二分查找优化

```java
class Solution {

    private int[][] dpTable;
    public int dp(int k,int n){
        int res = Integer.MAX_VALUE;
        // 状态转移
        if(k==1){
            this.dpTable[k][n] = n;
            return n;
        }
        // dp[k][n] 代表的是 还有 k 个鸡蛋去测试 n 层
        // basecase 就是 k=1 的时候，一个鸡蛋只能线性查找
        // basecase 另外一个就是 n=0 的时候，不需要扔鸡蛋
        
        if(n==0){
            this.dpTable[k][n]=0;
            return 0;
        }

        if(this.dpTable[k][n]!=Integer.MAX_VALUE){
            return this.dpTable[k][n];
        }

        // 线性搜索
        // for(int i=1;i<=n;i++){
        //     res = Math.min(res,Math.max(this.dp(k-1,i-1),this.dp(k,n-i))+1);
        // }
        
        // 二分搜索
        int lo = 1;
        int hi = n;
        
        while(lo<=hi){
            int mid = (lo+hi)/2;
            int broken = this.dp(k-1,mid-1);
            int unBroken = this.dp(k,n-mid);
            
            if(broken > unBroken){
                hi = mid - 1;
                res = Math.min(res,broken+1);
            }else{
                lo = mid+1;
                res = Math.min(res,unBroken+1);
            }
        }
        this.dpTable[k][n] = res;
        return res;
    }
    public int superEggDrop(int K, int N) {
        // 备忘录 dpTable
        this.dpTable = new int[K+1][N+1];
        for(int i=0;i<dpTable.length;i++){
            for(int j=0;j<dpTable[i].length;j++){
                this.dpTable[i][j] = Integer.MAX_VALUE;
            }
        }

        // 动态规划
        this.dp(K,N);

        return this.dpTable[K][N];

    }

}
```

```
Runtime: 53 ms, faster than 26.87% of Java online submissions for Super Egg Drop.
Memory Usage: 40 MB, less than 50.24% of Java online submissions for Super Egg Drop.
```

## 回溯法

### 980. Unique Paths III

```
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20
```

```java
class Solution {
    int row, col; // 地图
    boolean[][] visted; // 备忘录
    
    int x, y; // 起始位置
    
    public int uniquePathsIII(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        visted = new boolean[row][col];
        int count=0; //可行走的空位个数，防止重复行走
        int res = 0; //路条数
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 0){
                    count++;
                }
                if(grid[i][j] == 1){
                    x = i;
                    y = j;
                }
            }
        }
        dfs(x, y, grid, visted, count);
        
        return res;
        
    }
    
    public dfs(int x, int y, int[][] grid, boolean[][] visted, int count){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visted[x][y] || grid[x][y] == -1){
            return;
        }
        if(grid[x][y] == 2 && count == -1){
            res++;
            return;
        }

        
        visted[x][y] = true;
        dfs(x + 1, y, grid, visted, count - 1);
        dfs(x - 1, y, grid, visted, count - 1);
        dfs(x, y + 1, grid, visted, count - 1);
        dfs(x, y - 1, grid, visted, count - 1);
        visted[x][y] = false;
    }
}
```