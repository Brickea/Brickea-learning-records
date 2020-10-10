package queue.priorityQueue;


/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 * 基于最大堆的最大优先队列
 **/

public class MaxPQ<Item extends Comparable<Item>> {


    private int size;
    private int N;
    private Item[] maxHeap; // 最大堆

    // 最大堆相关
    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: i
     * @param: j
    * @return: void
    * @description:
     * 交换位于 i 和 j 的两个元素
    **/
    private void exch(int i,int j){
        Item temp = this.maxHeap[i];
        this.maxHeap[i] = this.maxHeap[j];
        this.maxHeap[j] = temp;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: i
     * @param: j
    * @return: boolean
    * @description:
     * 判断 i 和 j 中 i 是否小于 j
    **/
    private boolean less(int i, int j){
        return this.maxHeap[i].compareTo(this.maxHeap[j])<0;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: index
    * @return: void
    * @description:
     * 将 index 所在位置进行上浮操作
    **/
    private void swim(int index){
        while(index>1&&this.less(index/2,index)){
            exch(index/2,index);
            index /= 2;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: index
    * @return: void
    * @description:
     * 对应 index 位置元素下沉
    **/
    private void sink(int index){
        while(index*2<=this.N){
            int j=2*index;
            if(j<this.N&&less(j,j+1)) j++;
            if(!less(index,j)) break;
            exch(index,j);
            index = j;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: max
    * @return:
    * @description:
     * 构造函数，创建容量为 max 的最大优先序列
    **/
    public MaxPQ(int max){
        this.size = max;
        this.N = 0;
        this.maxHeap = (Item[]) new Comparable[this.size+1];
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param: item
    * @return:
    * @description:
     * 插入元素到最大优先队列中
    **/
    public void insert(Item item){
        this.maxHeap[++this.N] = item;
        this.swim(this.N);
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: Item
    * @description:
     * 返回最大值
    **/
    public Item max(){
        return this.maxHeap[1];
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/10/2020
     * @param:
    * @return: Item
    * @description:
     * 删除最大值
    **/
    public Item delMax(){
        Item val = this.maxHeap[1];
        this.exch(this.N,1);
        this.maxHeap[this.N--]=null;
        this.sink(1);
        return val;
    }

    public boolean isEmpty(){
        return this.N==0;
    }

    public int size(){
        return this.N;
    }




}
