package linkedList;

import linkedlist.LinkNode;
import linkedlist.ReverseLinkedList;
import org.junit.Test;


/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class ReverseTest {
    @Test
    public void test() {
        LinkNode testH = new LinkNode(0);
        LinkNode temp = testH;
        for (int i = 1; i < 10; i++) {
            temp.next = new LinkNode(i);
            temp = temp.next;
        }

        temp = testH;
        while(temp!=null){
            System.out.print(temp.value+"->");
            temp=temp.next;
        }
        System.out.println();
        temp = ReverseLinkedList.reverseLinkedList(testH);

        while(temp!=null){
            System.out.print(temp.value+"->");
            temp=temp.next;
        }
    }
}
