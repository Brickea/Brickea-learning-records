import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // This randomized queue uses stack with resizing array data structure
    private int capacity;
    private Item[] items;

    // current empty stack top pointer
    private int current;

    // resizing methods
    private void resize(int newCapacity) {
        Item[] oldItems = this.items;
        this.capacity = newCapacity;
        this.items = (Item[]) new Object[newCapacity];
        for (int i = 0; i < this.current; i++) {
            this.items[i] = oldItems[i];
        }
    }

    // exchange position method
    private void exch(Item[] a,int p,int q){
        // exchange position q to p
        Item temp = a[p];
        a[p] = a[q];
        a[q] = temp;
    }

    // shuffle method
    private void shuffle(){
        // if there is only one number in the deque, there is no need to shuffle
        if(this.size()==1){
            return ;
        }
        for(int i=1;i<this.current;i++){
            int randomPosition = StdRandom.uniform(0,i);
            exch(this.items,randomPosition,i);
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.capacity = 1;
        this.current = 0;
        this.items = (Item[]) new Object[this.capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.current == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.current;
    }

    // add the item
    public void enqueue(Item item){
        // check if it need to extend the capacity
        if(this.current>=this.capacity){
            this.capacity*=2;
            this.resize(this.capacity);
        }
        this.items[this.current++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        // shrink if is only 25% full
        if(this.current<=this.capacity/4){
            this.capacity /= 2;
            this.resize(this.capacity);
        }
        this.shuffle();
        return this.items[--this.current];
    }

    // return a random item (but do not remove it)
    public Item sample(){
        this.shuffle();
        return this.items[this.current-1];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new randomizedQueueIterator();
    }

    private class randomizedQueueIterator implements Iterator<Item>{
        private int iteratorPointer = 0;
        private Item[] temp = (Item[]) new Object[RandomizedQueue.this.current];
        public randomizedQueueIterator(){
            RandomizedQueue.this.shuffle();
            for(int i=0;i<RandomizedQueue.this.current;i++){
                this.temp[i] = RandomizedQueue.this.items[i];
            }
        }

        public boolean hasNext(){
            return this.iteratorPointer<this.temp.length;
        }

        @Override
        public Item next() {
            return this.temp[this.iteratorPointer++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();

        StdOut.println("Is the queue empty? "+ test.isEmpty());
        for(int i=0;i<10;i++){
            test.enqueue(i);
        }
        StdOut.println("Sample 10");
        for(int i=0;i<10;i++){
            StdOut.print(test.sample());
        }
        StdOut.println("\nForeach 10");
        for(int j=0;j<10;j++){
            for(int i:test){
                StdOut.print(i);
            }
            StdOut.println();
        }

        StdOut.println("\nDeque 10");
        for(int i=0;i<10;i++){
            StdOut.print(test.dequeue());
        }
        StdOut.println();
        StdOut.println("Current size is "+ test.size());


//        for(int i: test){
//            StdOut.println(i);
//        }
    }

}
