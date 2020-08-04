package sort;

import java.util.Random;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 * 快排
 * 时间复杂度为 NlgN
 * 不稳定
 **/

public class QuickSort {

    private static void randomShuffle(Comparable[] a){
        Random r = new Random();

        for(int i=a.length-1;i>=0;i--){
            int temp = r.nextInt(i+1);
            exch(a,i,temp);
        }
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [v, w]
     * @return: boolean
     * @description: 用来判断两个实现了 Comparable 接口的对象是否是 v 小于 w
     **/
    private static boolean less(Comparable v, Comparable w) {
        // 这样设计的时候排序是稳定的，因为在遇到相等的内容时，原本在后的不会跑到前面
        return v.compareTo(w) < 0;
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [a, i, j]
     * @return: void
     * @description: 用来呼唤 i 和 j 的位置
     **/
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/4/2020
     * @param: [a, aux, lo, mid, hi]
     * @return: int
     * @description:
     * 用来寻找 partition 的位置
     **/

    public static int partition(Comparable[] a,int lo,int hi){
        int i = lo;
        int j = hi;

        while(true){
            while(less(a[++i],a[lo])){
                if(i==hi) break;
            }

            while(less(a[lo],a[j])){
                j--;
                if(j==lo) break;
            }

            if(i>=j) break;

            exch(a,i,j);
        }
        exch(a,lo,j);

        return j;
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/4/2020
    * @param: [a, lo, hi]
    * @return: void
    * @description:
     * 用来二分递归
    **/
    public static void sort(Comparable[] a,int lo,int hi){

        if(hi<=lo) return;

        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/4/2020
    * @param: [a]
    * @return: void
    * @description:
     * 快速排序
    **/
    public static void sort(Comparable[] a){
        randomShuffle(a);

        sort(a,0,a.length-1);
    }
}
