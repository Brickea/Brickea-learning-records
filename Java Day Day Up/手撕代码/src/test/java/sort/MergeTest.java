package sort;

import org.junit.Test;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class MergeTest {

    @Test
    public void test1(){
        Integer[] a = new Integer[]{1,12,3,1,254,1,243,61,34,612,3457,2,45,61,345,1234,62,672,457,24,8752,345,13};

        MergeSort.sort(a);

        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
