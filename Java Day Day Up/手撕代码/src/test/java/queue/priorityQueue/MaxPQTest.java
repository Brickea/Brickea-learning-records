package queue.priorityQueue;

import org.junit.Test;

import java.util.Random;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class MaxPQTest {

    @Test
    public void test(){
        MaxPQ<Integer> maxPQ = new MaxPQ<>(10);

        Random random = new Random();

        for(int i=0;i<10;i++){
            int temp = random.nextInt(100);
            maxPQ.insert(temp);
            System.out.println(temp);
        }

        assert maxPQ.size()==10;

        System.out.println("_____________________________");

        while(!maxPQ.isEmpty()){
            System.out.println(maxPQ.delMax());
        }
    }
}
