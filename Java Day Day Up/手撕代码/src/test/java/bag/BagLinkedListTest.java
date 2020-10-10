package bag;

import org.junit.Test;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class BagLinkedListTest {
    @Test
    public void test(){
        BagLinkedList<Integer> bag = new BagLinkedList<>();

        assert bag.isEmpty();

        for(int i=0;i<20;i++){
            bag.add(i);
        }

        for(int i:bag){
            System.out.println(i);
        }

        assert bag.size()==20;
        assert !bag.isEmpty();

    }
}
