package queue;

import java.util.Iterator;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 * 基于双向链表的不定长队列
 **/

public class QueueLinkedList<Item> implements Iterable<Item>{

    private class Node{
        Item val;
        Node next;
        Node pre;
        Node(Item val){
            this.val = val;
        }
    }

    private int N;
    private Node queueEnd; // 队尾
    private Node queueStart; //队头

    private class QueueLinkedListIterator implements Iterator<Item>{

        Node queueEnd = QueueLinkedList.this.queueEnd;

        @Override
        public boolean hasNext() {
            return queueEnd!=null;
        }

        @Override
        public Item next() {
            Item val = queueEnd.val;
            queueEnd = queueEnd.next;
            return val;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: java.util.Iterator<Item>
    * @description:
     * 重写迭代器
     * 从队尾遍历到队头
    **/
    @Override
    public Iterator<Item> iterator() {
        return new QueueLinkedListIterator();
    }


    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: item
    * @return: void
    * @description:
     * 入队操作
    **/
    public void enqueue(Item item){
        this.N++;
        Node oldQueueEnd = this.queueEnd;
        this.queueEnd = new Node(item);
        this.queueEnd.next = oldQueueEnd;

        if(oldQueueEnd!=null)
            oldQueueEnd.pre = this.queueEnd;

        if(this.N==1){
            this.queueStart = this.queueEnd;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: int
    * @description:
     * 判断队列是否为空
    **/
    public boolean isEmpty(){
        return this.N==0;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: Item
    * @description:
     * 出队操作
    **/
    public Item dequeue() throws Exception {
        if(this.isEmpty()){
            throw new Exception("the queue is empty");
        }
        Item val = this.queueStart.val;
        this.queueStart = this.queueStart.pre;
        if(this.queueStart!=null)
            this.queueStart.next = null;
        this.N--;

        return val;
    }

}
