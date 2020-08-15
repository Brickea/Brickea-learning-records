package sort;

import org.junit.Test;

import java.util.Random;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class SelectionTest {
    @Test
    public void test1() {
        Integer[] a = new Integer[]{1, 12, 351, 23, 561324, 61, 436, 13, 461, 3, 431, 713, 7, 4};
        SelectionSort.sort(a);

        for (int i : a) {
            System.out.print(i + " ");
        }

    }
}
