import org.junit.Test;

import java.util.Arrays;

/**
 * @author chanwook
 */
public class OddOccurrencesInArrayTest {

    @Test
    public void testcase() throws Exception {

        long before = System.nanoTime();
        final int v = solution(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6});
        assert 6 == v;
        long after = System.nanoTime();

        System.out.println("time : " + (after - before));
    }

    public int solution(int[] array) {
        // write your code in Java SE 8

        if (array == null || array.length < 1) {
            return -1;
        }

        Arrays.stream(array).forEach(value -> {

        });
        int result = -1;
        for (int targetIndex = 0; targetIndex < array.length; targetIndex++) {
            int targetValue = array[targetIndex];

            if (targetValue > 1000000) {
                //System.out.println("Exeed Value(value="+targetValue+")");
            }

            //int pairedIndex = getPairedIndex(targetValue, array);
            int pairedIndex = -1;
            for (int loopIndex = 0; loopIndex < array.length; loopIndex++) {
                if (targetValue == array[loopIndex] && targetIndex != loopIndex) {
                    pairedIndex = loopIndex;
                }
            }

            if (pairedIndex > -1) {
                //System.out.println("Paired Index ["+targetIndex+", "+pairedIndex+"]");
            } else {
                result = targetValue;
            }
        }
        return result;
    }
}
