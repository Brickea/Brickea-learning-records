package stack;

import java.util.Iterator;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 * 基于数组的不定长栈
 **/

public class StackArray<Item> implements Iterable<Item> {

    private int size;
    private Item[] storage;
    private int N;

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return:
    * @description:
     * 构造函数
     * 初始size为10
    **/
    public StackArray(){
        this.size = 10;
        this.N = 0;
        this.storage = (Item[]) new Object[this.size];
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: 
    * @return: java.util.Iterator<Item>
    * @description:
     * 重写迭代器
     * 从栈顶向下遍历
    **/
    @Override
    public Iterator<Item> iterator() {
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<Item>{
        int i = StackArray.this.N;
        @Override
        public boolean hasNext() {
            return i!=0;
        }

        @Override
        public Item next() {
            return StackArray.this.storage[--i];
        }
    }
    
    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: newSize
    * @return: void
    * @description:
     * 重新调整存储数组的大小
    **/
    private void resize(int newSize){
        Item[] temp = this.storage;
        this.size = newSize;
        this.storage = (Item[]) new Object[this.size];

        for(int i=0;i<this.N;i++){
            this.storage[i] = temp[i];
        }
    }
    
    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: item
    * @return: void
    * @description:
     * 添加元素到栈顶
    **/
    public void push(Item item){
        if(this.N==this.size){
            // 需要扩容
            this.resize(this.size*2);
        }
        this.storage[this.N++] = item;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: Item
    * @description:
     * 查看栈顶元素
    **/
    public Item peek(){
        return this.storage[this.N-1];
    }
    
    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: 
    * @return: Item
    * @description:
     * 取出栈顶元素并返回
    **/
    public Item pop() throws Exception{
        if(this.isEmpty()){
            throw new Exception("The stack is empty!");
        }
        this.N--;
        int popIndex = this.N;
        Item popItem = this.storage[popIndex];
        this.storage[popIndex]=null; // 防止元素游离

        if(this.N>0&&this.N==this.size/4){
            // 需要缩小容量
            this.resize(this.size/2);
        }

        return popItem;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: int
    * @description:
     * 返回当前大小
    **/
    public int size(){
        return this.N;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: boolean
    * @description:
     * 判断栈是否为空
    **/
    public boolean isEmpty(){
        return this.N==0;
    }
}
