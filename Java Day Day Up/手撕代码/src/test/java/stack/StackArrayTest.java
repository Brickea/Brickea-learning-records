package stack;

import org.junit.Test;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class StackArrayTest {
    @Test
    public void test() {
        StackArray<Integer> stackArray = new StackArray<>();

        assert stackArray.size() == 0;

        for (int i = 0; i < 40; i++) {
            stackArray.push(i);
        }

        assert stackArray.size() == 40;

        for (int i = 39; i >= 0; i--) {
            try {
                assert stackArray.pop() == i;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        assert stackArray.size() == 0;

        try{
            stackArray.pop();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
