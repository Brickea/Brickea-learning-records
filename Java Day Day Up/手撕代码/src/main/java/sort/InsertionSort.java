package sort;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 插入排序
 * 可以设计成稳定
 * 时间复杂度 n^2
 * 很适合用于部分有序的序列
 **/

public class InsertionSort {
    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [v, w]
     * @return: boolean
     * @description: 用来判断两个实现了 Comparable 接口的对象是否是 v 小于 w
     **/
    private static boolean less(Comparable v, Comparable w) {
        // 这样设计的时候插入排序是稳定的，因为在遇到相等的内容时，原本在后的不会跑到前面
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
    * @date: 8/3/2020
    * @param: [a]
    * @return: void
    * @description:
     * 插入排序
    **/
    public static void sort(Comparable[] a){
        // 插入排序
        for(int i=1;i<a.length;i++){
            for(int j=i;j>0;j--){
                if(less(a[j],a[j-1])){
                    exch(a,j,j-1);
                }
            }
        }
    }
}
