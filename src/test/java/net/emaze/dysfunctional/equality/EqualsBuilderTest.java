package net.emaze.dysfunctional.equality;

import net.emaze.dysfunctional.equality.EqualsBuilderTest.TestArrays;
import net.emaze.dysfunctional.equality.EqualsBuilderTest.TestIgnoreArrayParamsWhenAlreadyDifferent;
import net.emaze.dysfunctional.equality.EqualsBuilderTest.TestIgnoreParamsWhenAlreadyDifferent;
import net.emaze.dysfunctional.equality.EqualsBuilderTest.TestPrimitive;
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
    TestPrimitive.class,
    TestArrays.class,
    TestIgnoreParamsWhenAlreadyDifferent.class,
    TestIgnoreArrayParamsWhenAlreadyDifferent.class
})
public class EqualsBuilderTest {

    public static class TestPrimitive {

        @Test
        public void canCompareTwoBooleans() {
            boolean former = true;
            boolean latter = true;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoBytes() {
            byte former = 1;
            byte latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoChars() {
            char former = 1;
            char latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoShorts() {
            short former = 1;
            short latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoInts() {
            int former = 1;
            int latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoLongs() {
            long former = 1;
            long latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoFloats() {
            float former = 1;
            float latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoDoubles() {
            double former = 1;
            double latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoObjects() {
            Object former = 1;
            Object latter = 1;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }
    }

    public static class TestArrays {

        @Test
        public void canCompareTwoArraysOfBooleans() {
            boolean[] former = new boolean[]{true};
            boolean[] latter = new boolean[]{true};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfByte() {
            byte[] former = new byte[]{1};
            byte[] latter = new byte[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfChar() {
            char[] former = new char[]{1};
            char[] latter = new char[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfShort() {
            short[] former = new short[]{1};
            short[] latter = new short[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfInt() {
            int[] former = new int[]{1};
            int[] latter = new int[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfLong() {
            long[] former = new long[]{1};
            long[] latter = new long[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfFloat() {
            float[] former = new float[]{1};
            float[] latter = new float[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfDouble() {
            double[] former = new double[]{1};
            double[] latter = new double[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }

        @Test
        public void canCompareTwoArraysOfObject() {
            Object[] former = new Object[]{1};
            Object[] latter = new Object[]{1};
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(former, latter);
            Assert.assertTrue(builder.isEquals());
        }
    }

    public static class TestIgnoreParamsWhenAlreadyDifferent {

        @Test
        public void forBooleans() {
            boolean former = true;
            boolean latter = true;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forBytes() {
            byte former = 1;
            byte latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forChars() {
            char former = 1;
            char latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forShorts() {
            short former = 1;
            short latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forInts() {
            int former = 1;
            int latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forLongs() {
            long former = 1;
            long latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forFloats() {
            float former = 1;
            float latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forDoubles() {
            double former = 1;
            double latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forObjects() {
            Object former = 1;
            Object latter = 1;
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }
    }

    public static class TestIgnoreArrayParamsWhenAlreadyDifferent {

        @Test
        public void forBooleans() {
            boolean[] former = new boolean[]{true};
            boolean[] latter = new boolean[]{true};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forBytes() {
            byte[] former = new byte[]{1};
            byte[] latter = new byte[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forChars() {
            char[] former = new char[]{1};
            char[] latter = new char[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forShorts() {
            short[] former = new short[]{1};
            short[] latter = new short[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forInts() {
            int[] former = new int[]{1};
            int[] latter = new int[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forLongs() {
            long[] former = new long[]{1};
            long[] latter = new long[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forFloats() {
            float[] former = new float[]{1};
            float[] latter = new float[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forDoubles() {
            double[] former = new double[]{1};
            double[] latter = new double[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }

        @Test
        public void forObjects() {
            Object[] former = new Object[]{1};
            Object[] latter = new Object[]{1};
            EqualsBuilder builder = new EqualsBuilder().appendSuper(false);
            builder.append(former, latter);
            Assert.assertFalse(builder.isEquals());
        }
    }
}
