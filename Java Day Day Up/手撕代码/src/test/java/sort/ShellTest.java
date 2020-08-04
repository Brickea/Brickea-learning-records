package sort;

import org.junit.Test;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class ShellTest {

    @Test
    public void test1(){
        Integer[] a = new Integer[]{1,23,12,41,2,51,25,1,543,6,45,364,5673,46,2,13,4,35468};

        ShellSort.sort(a);

        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
