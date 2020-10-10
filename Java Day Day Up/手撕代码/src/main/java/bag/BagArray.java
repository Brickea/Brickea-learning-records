package bag;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description: 背包数据结构
 * 基于数组的不定长背包
 **/

public class BagArray<Item> implements Iterable<Item> {

    private int N;
    private int size;
    private Item[] storage;

    public BagArray() {
        this.N = 0;
        this.size = 10;
        this.storage = (Item[]) new Object[this.size];
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param: newSize
     * @return: void
     * @description: 用来调整背包数组存储的大小
     **/
    private void resize(int newSize) {
        Item[] temp = this.storage;

        this.storage = (Item[]) new Object[newSize];

        for (int i = 0; i < this.N; i++) {
            this.storage[i] = temp[i];
        }
    }

    private class BagArrayIterator implements Iterator<Item> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < BagArray.this.N;
        }

        @Override
        public Item next() {
            return BagArray.this.storage[i++];
        }
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param:
     * @return: java.util.Iterator<Item>
     * @description: 重写迭代器
     **/
    @Override
    public Iterator<Item> iterator() {
        return new BagArrayIterator();
    }

    public boolean isEmpty(){
        return this.N==0;
    }

    /**
     * @author: Zixiao Wang
     * @date: 10/10/2020
     * @param: item
     * @return: void
     * @description:
         * 向背包中添加元素
         * 当前容量等于最大容量的时候扩容，容量为之前的两倍
     **/
    public void add(Item item) {
        if(this.N==this.size){
            // 超出大小，数组扩容
            this.resize(this.size*2);
        }
        this.storage[this.N++] = item;
    }

    public int size(){
        return this.N;
    }
}
