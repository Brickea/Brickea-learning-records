package sort;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 选择排序
 * 其不稳定
 * 时间复杂度 n^2
 **/

public class SelectionSort {

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
    * @date: 8/3/2020
    * @param: [a]
    * @return: void
    * @description:
     * 选择排序
    **/
    public static void sort(Comparable[] a) {
        // 选择排序
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (!less(a[min], a[j])) {
                    min = j;
                }
            }
            System.out.println(a[min]+" -> "+a[i]);
            exch(a, i, min); // 将右边最小的和当前指针位置互换
        }
    }
}
