# Leetcode 练习记录<!-- omit in toc -->

- [July Challange](#july-challange)
  - [Week 1](#week-1)
    - [Binary Tree Level Order Traversal II](#binary-tree-level-order-traversal-ii)
    - [Prison Cells After N Days](#prison-cells-after-n-days)
    - [Ugly Number ||](#ugly-number-)
    - [Hamming Distance](#hamming-distance)
- [Linked list practice](#linked-list-practice)
  - [206. Reverse Linked List E](#206-reverse-linked-list-e)
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
- [Bit Manipulation](#bit-manipulation)
  - [389. Find the Difference E](#389-find-the-difference-e)
  - [136. Single Number E](#136-single-number-e)
  - [137. Single Number II M](#137-single-number-ii-m)

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

### 206. Reverse Linked List E 

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