package linkedlist;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 如果链表中存在环，寻找环的入口节点
 * 思路为先寻找环，找到某一节点后求出环的长度
 * 之后快慢指针，快指针先走环长度的距离，然后快慢指针同步速度遍历，最终碰撞的节点就是入口节点
 **/

public class FindEntryOfCircle {
    /**
    * @author: Zixiao Wang
    * @date: 8/17/2020
    * @param: [pHead]
    * @return: linkedlist.LinkNode
    * @description:
     * 寻找环中某一结点
    **/
    private static LinkNode findCircleNode(LinkNode pHead) {
        // 返回环中的某一个节点
        LinkNode walker = pHead;
        LinkNode runner = pHead;

        boolean flag = false; // 判断是否真正进入了环

        while (walker.next != null && runner.next != null && runner.next.next != null) {
            flag = true;
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                break;
            }
        }

        return walker == runner && flag ? walker : null;

    }

    /**
    * @author: Zixiao Wang
    * @date: 8/17/2020
    * @param: [circleNode]
    * @return: int
    * @description:
     * 求出环的长度
    **/
    private static int circleLen(LinkNode circleNode) {
        LinkNode temp = circleNode;
        int len = 0;
        do {
            temp = temp.next;
            len++;
        } while (temp != circleNode);

        return len;
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/17/2020
    * @param: [pHead]
    * @return: linkedlist.LinkNode
    * @description:
     * 返回环的入口节点
    **/
    public static LinkNode EntryNodeOfLoop(LinkNode pHead) {
        if (pHead == null) {
            return null;
        }
        LinkNode circleNode = findCircleNode(pHead);
        int len = 0;
        if (circleNode != null) {
            len = circleLen(circleNode);
        } else {
            return null;
        }

        // 快指针先以环的长度进行移动，之后两指针以相同速度移动，最后碰撞的那个节点就是环的入口
        LinkNode walker = pHead;
        LinkNode runner = pHead;
        for (int i = len; i > 0; i--) {
            runner = runner.next;
        }
        while (walker != runner) {
            runner = runner.next;
            walker = walker.next;
        }

        return walker;
    }
}
