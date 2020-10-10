package bag;

import java.util.Iterator;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description: 基于链表的不定长背包
 **/

public class BagLinkedList<Item> implements Iterable<Item> {

    private class Node {
        Item val;
        Node next;

        public Node() {

        }

        public Node(Item val) {
            this.val = val;
        }
    }

    private int N;
    private Node first;

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param:
     * @return: java.util.Iterator<Item>
     * @description: 重写迭代器
     **/
    @Override
    public Iterator<Item> iterator() {
        return new BagLinkedListIterator();
    }

    private class BagLinkedListIterator implements Iterator<Item> {

        Node first = BagLinkedList.this.first;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item val = first.val;
            first = first.next;
            return val;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
    * @description:
     * 构造函数
    **/
    public BagLinkedList() {
        this.N = 0;
    }



    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param: item
     * @return: void
     * @description: 添加元素到背包
     **/
    public void add(Item item) {
        Node oldNode = this.first;
        this.first = new Node(item);
        this.first.next = oldNode;
        this.N++;
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param:
     * @return: int
     * @description: 返回当前背包大小
     **/
    public int size() {
        return this.N;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: boolean
    * @description:
     * 判断是否为空
    **/
    public boolean isEmpty(){
        return this.N==0;
    }


}
