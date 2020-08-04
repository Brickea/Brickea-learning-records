package sort;

import org.junit.Test;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class InsertionTest {
    @Test
    public void test1(){
        Integer[] a = new Integer[]{1,23,12,41,245,2,256,1234,672,37,234,57,345,83456,8,356,83,2};
        InsertionSort.sort(a);

        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
