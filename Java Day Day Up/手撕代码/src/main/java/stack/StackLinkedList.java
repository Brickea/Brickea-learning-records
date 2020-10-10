package stack;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description: 基于链表的不定长栈
 **/

public class StackLinkedList<Item> implements Iterable<Item> {

    private class Node {
        Item val;
        Node next;

        Node(Item item) {
            this.val = item;
        }
    }

    private int N;
    private Node first;

    @Override
    public Iterator<Item> iterator() {
        return new StackLinkedListIterator();
    }

    private class StackLinkedListIterator implements Iterator<Item> {
        Node first = StackLinkedList.this.first;

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
     * @param: item
     * @return: void
     * @description: 压栈
     **/
    public void push(Item item) {
        Node oldNode = this.first;
        this.first = new Node(item);
        this.first.next = oldNode;
        this.N++;
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param:
     * @return: Item
     * @description: 返回栈顶元素
     **/
    public Item peek() {
        return this.first.val;
    }

    public boolean isEmpty(){
        return this.N==0;
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param:
     * @return: Item
     * @description:
     **/
    public Item pop()throws Exception{
        Node oldNode = null;
        if (!this.isEmpty()) {
            oldNode = this.first;
            this.first = this.first.next;
        }
        else{
            throw new Exception("stack is empty!");
        }
        this.N--;
        return oldNode.val;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: int
    * @description:
     * 返回当前栈的大小
    **/
    public int size(){
        return this.N;
    }

}
