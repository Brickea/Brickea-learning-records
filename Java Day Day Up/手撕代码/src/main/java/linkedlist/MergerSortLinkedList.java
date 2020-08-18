package linkedlist;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 * 合并两个排序了的链表
 * 考虑两个list为null的边界条件
 **/

public class MergerSortLinkedList {
    public LinkNode Merge(LinkNode list1,LinkNode list2) {
        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }

        LinkNode resHead = null;

        if(list1.val<list2.val){
            resHead = list1;
            resHead.next = Merge(list1.next,list2);
        }else{
            resHead = list2;
            resHead.next = Merge(list1,list2.next);
        }

        return resHead;
    }
}
