package linkedlist;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 反转链表操作
 * 三个边界值：
 * 1. head为null
 * 2. 只有一个节点
 * 3. 有多个节点
 **/

public class ReverseLinkedList {
    /**
     * @author: Zixiao Wang
     * @date: 8/17/2020
     * @param: [head]
     * @return: linkedlist.LinkNode
     * @description: 反转链表
     **/
    public static LinkNode reverseLinkedList(LinkNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }

        LinkNode pre = head;
        LinkNode current = head.next;

        while (current != null) {
            LinkNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }

        head.next = null;

        return pre;
    }
}
