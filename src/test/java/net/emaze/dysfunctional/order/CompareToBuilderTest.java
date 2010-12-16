package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.order.CompareToBuilderTest.TestArrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@SuiteClasses({
    TestArrays.class
})
public class CompareToBuilderTest {

    public static class TestArrays {

        @Test
        public void canEvaluateSameOrderForArrayOfBooleans() {
            boolean[] former = new boolean[]{true};
            boolean[] latter = new boolean[]{true};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfBytes() {
            byte[] former = new byte[]{1};
            byte[] latter = new byte[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfChars() {
            char[] former = new char[]{1};
            char[] latter = new char[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfShorts() {
            short[] former = new short[]{1};
            short[] latter = new short[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfInts() {
            int[] former = new int[]{1};
            int[] latter = new int[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfLongs() {
            long[] former = new long[]{1};
            long[] latter = new long[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfFloats() {
            float[] former = new float[]{1};
            float[] latter = new float[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfDoubles() {
            double[] former = new double[]{1};
            double[] latter = new double[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfObjects() {
            Object[] former = new Object[]{1};
            Object[] latter = new Object[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }
    }
}
