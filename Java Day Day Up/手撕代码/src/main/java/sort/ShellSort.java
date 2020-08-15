package sort;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 希尔排序
 * h-sort的递增序列 h 可以取值很多种
 * 2 的任意次幂减一，即1，3，7...
 * 3x+1，即1，4，13，40 （Knuth最常用）
 * 1，5，19，41
 **/

public class ShellSort {
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

    public static void sort(Comparable[] a) {
        int N = a.length;

        // 递增序列
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        // 希尔排序
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; (j >= h) && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3; // 移动向下一个h
        }

    }
}
