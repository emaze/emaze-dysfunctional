package net.emaze.dysfunctional.order;

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
    CompareToBuilderTest.TestSameOrderForPrimitives.class,
    CompareToBuilderTest.TestIgnoreAppendWhenNotSameOrderForPrimitives.class,
    CompareToBuilderTest.TestIgnoreAppendWhenNotSameOrderForArrays.class,
    CompareToBuilderTest.TestSameOrderForArrays.class,
    CompareToBuilderTest.TestLhsIsGreaterForArrays.class,
    CompareToBuilderTest.TestIntrospectionForArrays.class,
    CompareToBuilderTest.TestArrayLengthForArrays.class,
    CompareToBuilderTest.TestNullsForArrays.class
})
public class CompareToBuilderTest {

    public static class TestSameOrderForPrimitives {

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

    public static class TestIgnoreAppendWhenNotSameOrderForPrimitives {

        @Test
        public void ignoreAppendWhenNotSameOrderForSuper() {
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.appendSuper(Order.SAME_ORDER);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

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

    public static class TestIgnoreAppendWhenNotSameOrderForArrays {

        @Test
        public void ignoreAppendWhenNotSameOrderForBoolean() {
            boolean[] array = new boolean[]{true};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForByte() {
            byte[] array = new byte[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForChar() {
            char[] array = new char[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForShort() {
            short[] array = new short[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForInt() {
            int[] array = new int[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForLong() {
            long[] array = new long[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForFloat() {
            float[] array = new float[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForDouble() {
            double[] array = new double[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void ignoreAppendWhenNotSameOrderForObject() {
            Object[] array = new Object[]{0};
            CompareToBuilder builder = new CompareToBuilder().appendSuper(Order.LHS_IS_LESSER);
            builder.append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfBooleans() {
            boolean[] array = new boolean[]{true};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfBytes() {
            byte[] array = new byte[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfChars() {
            char[] array = new char[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfShorts() {
            short[] array = new short[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfInts() {
            int[] array = new int[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfLongs() {
            long[] array = new long[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfFloats() {
            float[] array = new float[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
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
        public void sameArrayLeadsToSameOrderForArrayOfDoubles() {
            double[] array = new double[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void canEvaluateSameOrderForArrayOfObjects() {
            Object[] former = new Object[]{1};
            Object[] latter = new Object[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }

        @Test
        public void sameArrayLeadsToSameOrderForArrayOfObjects() {
            Object[] array = new Object[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(array, array);
            Assert.assertEquals(Order.SAME_ORDER, builder.toComparison());
        }
    }

    public static class TestLhsIsGreaterForArrays {

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfBooleans() {
            boolean[] former = new boolean[]{true, true};
            boolean[] latter = new boolean[]{false, true};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfBytes() {
            byte[] former = new byte[]{2, 2};
            byte[] latter = new byte[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfChars() {
            char[] former = new char[]{2, 2};
            char[] latter = new char[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfShorts() {
            short[] former = new short[]{2, 2};
            short[] latter = new short[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfInts() {
            int[] former = new int[]{2, 2};
            int[] latter = new int[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfLongs() {
            long[] former = new long[]{2, 2};
            long[] latter = new long[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfFloats() {
            float[] former = new float[]{2, 2};
            float[] latter = new float[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfDoubles() {
            double[] former = new double[]{2, 2};
            double[] latter = new double[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfObjects() {
            Object[] former = new Object[]{2, 2};
            Object[] latter = new Object[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }
    }

    public static class TestIntrospectionForArrays {

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfBooleans() {
            Object former = new boolean[]{true, true};
            Object latter = new boolean[]{false, true};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfBytes() {
            Object former = new byte[]{2, 2};
            Object latter = new byte[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfChars() {
            Object former = new char[]{2, 2};
            Object latter = new char[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfShorts() {
            Object former = new short[]{2, 2};
            Object latter = new short[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfInts() {
            Object former = new int[]{2, 2};
            Object latter = new int[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfLongs() {
            Object former = new long[]{2, 2};
            Object latter = new long[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfFloats() {
            Object former = new float[]{2, 2};
            Object latter = new float[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfDoubles() {
            Object former = new double[]{2, 2};
            Object latter = new double[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canEvaluateLhsIsGreaterForArrayOfObjects() {
            Object former = new Object[]{2, 2};
            Object latter = new Object[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenLhsIsNull() {
            Object former = null;
            Object latter = new Object[]{1, 2};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void lhsIsGreaterWhenRhsIsNull() {
            Object former = new Object[]{1, 2};
            Object latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void canIntrospectWithComparator() {
            Object former = 2;
            Object latter = 1;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter, new ComparableComparator<Integer>());
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }
    }

    public static class TestArrayLengthForArrays {

        @Test
        public void longestArrayIsGreaterForArrayOfBooleans() {
            boolean[] former = new boolean[]{true, true};
            boolean[] latter = new boolean[]{true};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfBytes() {
            byte[] former = new byte[]{1, 2};
            byte[] latter = new byte[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfChars() {
            char[] former = new char[]{1, 2};
            char[] latter = new char[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfShorts() {
            short[] former = new short[]{1, 2};
            short[] latter = new short[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfInts() {
            int[] former = new int[]{1, 2};
            int[] latter = new int[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfLongs() {
            long[] former = new long[]{1, 2};
            long[] latter = new long[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfFloats() {
            float[] former = new float[]{1, 2};
            float[] latter = new float[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfDoubles() {
            double[] former = new double[]{1, 2};
            double[] latter = new double[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void longestArrayIsGreaterForArrayOfObjects() {
            Object[] former = new Object[]{1, 2};
            Object[] latter = new Object[]{1};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }
    }

    public static class TestNullsForArrays {

        @Test
        public void lhsIsLesserWhenNullForArrayOfBooleans() {
            boolean[] former = null;
            boolean[] latter = new boolean[]{true};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfBooleans() {
            boolean[] former = new boolean[]{true};
            boolean[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfBytes() {
            byte[] former = null;
            byte[] latter = new byte[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfBytes() {
            byte[] former = new byte[]{0};
            byte[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfChars() {
            char[] former = null;
            char[] latter = new char[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfChars() {
            char[] former = new char[]{0};
            char[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfShorts() {
            short[] former = null;
            short[] latter = new short[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfShorts() {
            short[] former = new short[]{0};
            short[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfInts() {
            int[] former = null;
            int[] latter = new int[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfInts() {
            int[] former = new int[]{0};
            int[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfLongs() {
            long[] former = null;
            long[] latter = new long[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfLongs() {
            long[] former = new long[]{0};
            long[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfFloats() {
            float[] former = null;
            float[] latter = new float[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfFloats() {
            float[] former = new float[]{0};
            float[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfDoubles() {
            double[] former = null;
            double[] latter = new double[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfDoubles() {
            double[] former = new double[]{0};
            double[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }

        @Test
        public void lhsIsLesserWhenNullForArrayOfObjects() {
            Object[] former = null;
            Object[] latter = new Object[]{0};
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_LESSER, builder.toComparison());
        }

        @Test
        public void rhsIsLesserWhenNullForArrayOfObjects() {
            Object[] former = new Object[]{0};
            Object[] latter = null;
            CompareToBuilder builder = new CompareToBuilder().append(former, latter);
            Assert.assertEquals(Order.LHS_IS_GREATER, builder.toComparison());
        }
    }
}
