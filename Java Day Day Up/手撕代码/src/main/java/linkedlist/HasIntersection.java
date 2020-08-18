package linkedlist;

import java.util.LinkedList;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 寻找两个单链表的交点
 * 用栈来解决，两个单链表对应入栈，最后看栈顶元素，一致则说明有交点，对应出栈
 * 最后不相同的节点的下一个节点即为交点
 **/

public class HasIntersection {
    /**
     * @author: Zixiao Wang
     * @date: 8/17/2020
     * @param: [list1, list2]
     * @return: linkedlist.LinkNode
     * @description: 返回交点
     **/
    public static LinkNode intersectionNode(LinkNode list1, LinkNode list2) {
        LinkedList<LinkNode> stack1 = new LinkedList<LinkNode>();
        LinkedList<LinkNode> stack2 = new LinkedList<LinkNode>();

        while (list1 != null || list2 != null) {
            if (list1 != null) {
                stack1.addLast(list1);
                list1 = list1.next;
            }
            if (list2 != null) {
                stack2.addLast(list2);
                list2 = list2.next;
            }
        }

        LinkNode temp1 = stack1.removeLast();
        LinkNode temp2 = stack1.removeLast();

        while(true){
            if(temp1!=temp2){
                break;
            }
            temp1 = stack1.removeLast();
            temp2 = stack1.removeLast();
        }

        return temp1.next;
    }
}
