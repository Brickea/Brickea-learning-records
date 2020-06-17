import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // this deque uses linked-list data structure
    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    // first and last pointer
    private Node first;
    private Node last;

    // current deque size
    private int dequeSize;


    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.dequeSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.dequeSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.dequeSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // check if the argument is valid
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = this.first;
        // if deque is empty
        if (this.dequeSize == 0) {
            this.first = new Node();
            this.first.item = item;
            this.first.next = null;
            this.first.pre = null;
            this.last = this.first;
        }
        // deque is not empty
        else {
            this.first = new Node();
            this.first.item = item;
            this.first.next = oldFirst;
            this.first.pre = null;
            oldFirst.pre = this.first;
        }
        this.dequeSize++;

    }

    // add the item to the back
    public void addLast(Item item) {
        // check if the argument is valid
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // if deque is empty
        Node oldLast = this.last;
        if (this.dequeSize == 0) {
            this.last = new Node();
            this.last.item = item;
            this.last.next = null;
            this.last.pre = null;
            this.first = this.last;
        }
        // deque is not empty
        else {
            this.last = new Node();
            oldLast.next = this.last;
            this.last.item = item;
            this.last.next = null;
            this.last.pre = oldLast;
        }
        this.dequeSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // check if the deque is empty
        if (this.dequeSize == 0) {
            throw new NoSuchElementException();
        }

        Node oldFist = this.first;
        // if the first and the last are point to same place(size=1)
        if (this.first == this.last) {
            this.first = null;
            this.last = null;
        } else {
            this.first = this.first.next;
            this.first.pre = null;
        }
        this.dequeSize--;
        return oldFist.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        // check if the deque is empty
        if (this.dequeSize == 0) {
            throw new NoSuchElementException();
        }

        Node oldLast = this.last;
        // if the first and the last are point to same place(size=1)
        if (this.first == this.last) {
            this.first = null;
            this.last = null;
        }
        else{
            this.last = this.last.pre;
            this.last.next = null;
        }
        this.dequeSize--;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item>{
        private Node current = Deque.this.first;
        private int size = Deque.this.dequeSize;

        public boolean hasNext(){
            return this.current != null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            // throw NoSuchElementException if no more items in iteration
            if (this.size==0){
                throw new NoSuchElementException();
            }

            Item item = this.current.item;
            this.current = this.current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> test = new Deque<>();

        StdOut.println("Is the deque empty? "+test.isEmpty());

        StdOut.println("Current Deque size: "+test.size());

        test.addLast(1);

        for(int i=0;i<10;i++){
            test.addFirst(i);
        }

        for(int i=0;i<10;i++){
            test.addLast(i);
        }

        StdOut.println("Current deque: ");
        for(int i : test){
            StdOut.print(i);
        }

        StdOut.println();
        for(int i=0;i<5;i++){
            StdOut.println(test.removeLast());
            StdOut.printf("size %d \n",test.size());
        }

        for(int i=0;i<5;i++){
            StdOut.println(test.removeFirst());
            StdOut.printf("size %d \n",test.size());
        }

        StdOut.println("\nForeach");
        for(int i : test){
            StdOut.print(i);
        }
    }

}
