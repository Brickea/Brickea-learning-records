package sort;

import org.junit.Test;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class QuickTest {

    @Test
    public void test1(){
        Integer[] a = new Integer[]{2,123,123,51,3474,568,345,623,4,48,467,956,94,568,4,67956,9,875,4,1,3,1};

        QuickSort.sort(a);

        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
