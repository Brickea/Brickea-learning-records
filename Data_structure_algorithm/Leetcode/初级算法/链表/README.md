# 链表 <!-- omit in toc -->

## 删除链表中的节点

```
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。


现有一个链表 -- head = [4,5,1,9]，它可以表示为:


示例 1：

输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2：

输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 

提示：

链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。
```

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
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
37.6 MB, 在所有 Java 提交中击败了95.83%的用户
```

## 删除链表的倒数第N个节点
```
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？
```

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}

```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
36.6 MB, 在所有 Java 提交中击败了59.01%的用户
```

## 反转链表
```
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
```

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode current = head;
        ListNode pre = null;
        while(current.next!=null){
            ListNode nextNode = current.next;
            current.next = pre;
            pre = current;
            current = nextNode;
        }
        current.next = pre;
        return current;
    }
}
```
```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
38.2 MB, 在所有 Java 提交中击败了87.05%的用户
```

## 合并两个有序链表
```
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);

        ListNode current = dummy;

        while(l1!=null||l2!=null){
            if(l1==null){
                current.next = new ListNode(l2.val);
                l2 = l2.next;
            }else if(l2==null){
                current.next = new ListNode(l1.val);
                l1 =l1.next;
            }
            else if(l1.val<l2.val){
                current.next = new ListNode(l1.val);
                l1 = l1.next;
            }else if(l2.val<=l1.val){
                current.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            current = current.next;
        }

        return dummy.next;
    }
}
```

```
执行用时：
1 ms, 在所有 Java 提交中击败了54.68%的用户
内存消耗：
37.9 MB, 在所有 Java 提交中击败了90.63%的用户
```

## 回文链表
```
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
```

O(1)的空间，不考虑链表复原，快慢指针在寻找中间节点的过程中直接反转链表前半部分，找到中间节点之后直接从中间向两边开始比较

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}

```

```
执行用时：
4 ms, 在所有 Java 提交中击败了30.99%的用户
内存消耗：42.4 MB, 在所有 Java 提交中击败了24.58%的用户
```

## 环形链表
```
给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。


进阶：

你能用 O(1)（即，常量）内存解决此问题吗？
 

示例 1


输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：



输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：



输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
 

提示：

链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。
```

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
        ListNode fast = head;
        ListNode slow = head;

        do{
            if(slow==null||fast==null||fast.next==null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }while(fast!=slow);

        return true;
    }
}
```

```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：
38.3 MB, 在所有 Java 提交中击败了96.21%的用户
```