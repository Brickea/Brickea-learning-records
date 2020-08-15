package sort;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 堆排序
 * 时间复杂度为 nlgn
 * 不稳定
 **/

public class HeapSort {


    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [v, w]
     * @return: boolean
     * @description: 用来判断两个实现了 Comparable 接口的对象是否是 v 小于 w
     **/
    private static boolean less(Comparable v, Comparable w) {
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
     * @param: [a, k]
     * @return: void
     * @description: 下沉，小的元素下沉
     **/
    private static void sink(Comparable[] a, int k) {
        final int N = a.length;
        while (2 * k <= N) {
            int child = 2 * k;
            if (child + 1 <= N && less(a[child - 1], a[child])) child++; // 找出大一点的那个子节点
            if (!less(a[k - 1], a[child - 1])) {
                break;
            }
            exch(a, child - 1, k - 1);
            k = child;
        }
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/4/2020
     * @param: [a, k, N]
     * @return: void
     * @description:
     **/
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int child = 2 * k;
            if (child + 1 <= N && less(a[child - 1], a[child])) child++; // 找出大一点的那个子节点
            if (!less(a[k - 1], a[child - 1])) {
                break;
            }
            exch(a, child - 1, k - 1);
            k = child;
        }
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/4/2020
     * @param: [a]
     * @return: void
     * @description: 创建最大堆，用来生成正序排序序列
     **/
    public static void createMaxHeap(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k);
        }

    }

    public static void sort(Comparable[] a) {

        // 先创建大堆
        createMaxHeap(a);

        int n = a.length;
        while(n>0){
            exch(a, 0, n - 1);
            sink(a, 1, --n);
        }

    }
}
