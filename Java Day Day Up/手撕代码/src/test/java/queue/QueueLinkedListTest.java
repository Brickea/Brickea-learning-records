package queue;

import org.junit.Test;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class QueueLinkedListTest {
    @Test
    public void test() {
        QueueLinkedList<Integer> queueLinkedList = new QueueLinkedList<>();

        assert queueLinkedList.isEmpty();

        for (int i = 0; i < 10; i++) {
            queueLinkedList.enqueue(i);
        }
        assert !queueLinkedList.isEmpty();

        for (int i = 0; i < 10; i++) {
            try {
                assert queueLinkedList.dequeue() == i;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            queueLinkedList.dequeue();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
