package sort;

import org.junit.Test;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class HeapTest {

    @Test
    public void test1(){
        Integer[] a = new Integer[]{123,4135,12,56,134,62,347,24,57,24,572,45,762,3465,243523,6,23457,34,7,57,345,2,65};

        HeapSort.sort(a);

        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
