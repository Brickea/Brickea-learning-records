package linkedlist;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 * 判断单链表是否有环
 * 使用快慢指针，只要最后快慢指针相碰，则说明有环
 **/

public class HasCircle {
    /**
    * @author: Zixiao Wang
    * @date: 8/17/2020
    * @param: [pHead]
    * @return: linkedlist.LinkNode
    * @description:
     * 返回环中某一节点，如果null则说明无环
    **/
    public static LinkNode findCircleNode(LinkNode pHead){
        // 返回环中的某一个节点
        LinkNode walker = pHead;
        LinkNode runner = pHead;

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
}
