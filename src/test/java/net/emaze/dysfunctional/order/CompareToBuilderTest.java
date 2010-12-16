package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.order.CompareToBuilderTest.TestIgnoreAppendWhenNotSameOrderForPrimitives;
import net.emaze.dysfunctional.order.CompareToBuilderTest.TestLhsIsGreaterForPrimitives;
import net.emaze.dysfunctional.order.CompareToBuilderTest.TestSameOrderForPrimitives;
import net.emaze.dysfunctional.order.CompareToBuilderTest.TestSameOrderForArrays;
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
    TestSameOrderForPrimitives.class,
    TestIgnoreAppendWhenNotSameOrderForPrimitives.class,
    TestLhsIsGreaterForPrimitives.class,
    TestSameOrderForArrays.class
})
public class CompareToBuilderTest {
    public static class TestSameOrderForPrimitives{
        @Test
        public void sameOrderForTrue() {
            boolean former = true;
            boolean latter = true;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForFalse() {
            boolean former = false;
            boolean latter = false;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForByte() {
            byte former = 1;
            byte latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForChar() {
            char former = 1;
            char latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForShort() {
            short former = 1;
            short latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForInt() {
            int former = 1;
            int latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForLong() {
            long former = 1;
            long latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForFloat() {
            float former = 1;
            float latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForDouble() {
            double former = 1;
            double latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameOrderForObject() {
            Object former = 1;
            Object latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }
    
    }
    public static class TestLhsIsGreaterForPrimitives{
        @Test
        public void lhsIsGreaterForBoolean() {
            boolean former = true;
            boolean latter = false;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForByte() {
            byte former = 2;
            byte latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForChar() {
            char former = 2;
            char latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForShort() {
            short former = 2;
            short latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForInt() {
            int former = 2;
            int latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForLong() {
            long former = 2;
            long latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForFloat() {
            float former = 2;
            float latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForDouble() {
            double former = 2;
            double latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterForObject() {
            Object former = 2;
            Object latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

    }
    public static class TestLhsIsLesserForPrimitives{
        @Test
        public void lhsIsLesserForBoolean() {
            boolean former = false;
            boolean latter = true;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForByte() {
            byte former = 1;
            byte latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForChar() {
            char former = 1;
            char latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForShort() {
            short former = 1;
            short latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForInt() {
            int former = 1;
            int latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForLong() {
            long former = 1;
            long latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForFloat() {
            float former = 1;
            float latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForDouble() {
            double former = 1;
            double latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserForObject() {
            Object former = 1;
            Object latter = 2;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

    }

    public static class TestIgnoreAppendWhenNotSameOrderForPrimitives{
        @Test
        public void ignoreAppendWhenNotSameOrderForBoolean() {
            boolean former = true;
            boolean latter = true;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForByte() {
            byte former = 1;
            byte latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForChar() {
            char former = 1;
            char latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForShort() {
            short former = 1;
            short latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForInt() {
            int former = 1;
            int latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForLong() {
            long former = 1;
            long latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForFloat() {
            float former = 1;
            float latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForDouble() {
            double former = 1;
            double latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForObject() {
            Object former = 1;
            Object latter = 1;
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }
    }

    public static class TestSameOrderForArrays {

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
