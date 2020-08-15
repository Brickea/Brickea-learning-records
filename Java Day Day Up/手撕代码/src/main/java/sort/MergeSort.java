package sort;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 * 归并排序
 * 时间复杂度为 NlgN
 * 是稳定的
 **/

public class MergeSort {
    private Comparable aux;
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
    * @return: void
    * @description:
     * 用来将从 lo 到 hi 的这一段合并为一个整体
    **/
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid,int hi){
        // copy
        for(int i=lo;i<=hi;i++){
            aux[i] = a[i];
        }

        // merge
        int i = lo;
        int j = mid+1;

        for(int k =lo;k<=hi;k++){
            if(i>mid){
                a[k] = aux[j++];
            }else if(j>hi){
                a[k]=aux[i++];
            }else if(less(aux[j],aux[i])){
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];
            }
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/4/2020
    * @param: [a, aux, lo, hi]
    * @return: void
    * @description:
     * 用来二分递归调用的函数
    **/
    private static void sort(Comparable a[], Comparable aux[],int lo, int hi){

        // 递归结束
        if(hi<=lo){
            return ;
        }

        int mid = lo+(hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);

    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];

        sort(a,aux,0,a.length-1);
    }
}
