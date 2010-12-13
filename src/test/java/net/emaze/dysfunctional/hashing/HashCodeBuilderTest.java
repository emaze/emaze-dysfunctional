package net.emaze.dysfunctional.hashing;

import net.emaze.dysfunctional.hashing.HashCodeBuilderTest.TestIntrospectsArrays;
import net.emaze.dysfunctional.hashing.HashCodeBuilderTest.TestArrays;
import net.emaze.dysfunctional.hashing.HashCodeBuilderTest.TestPrimitive;
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
    TestIntrospectsArrays.class,

})
public class HashCodeBuilderTest {

    public static class TestPrimitive {

        @Test
        public void forBoolean() {
            boolean former = true;
            boolean latter = true;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forByte() {
            byte former = 1;
            byte latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forChar() {
            char former = 1;
            char latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forShort() {
            short former = 1;
            short latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forInt() {
            int former = 1;
            int latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forLong() {
            long former = 1;
            long latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forFloat() {
            float former = 1;
            float latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forDouble() {
            double former = 1;
            double latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forObject() {
            Object former = 1;
            Object latter = 1;
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }
    }

    public static class TestArrays {

        @Test
        public void forArrayOfBooleans() {
            boolean[] former = new boolean[]{true};
            boolean[] latter = new boolean[]{true};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfBytes() {
            byte[] former = new byte[]{1};
            byte[] latter = new byte[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfChars() {
            char[] former = new char[]{1};
            char[] latter = new char[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfShorts() {
            short[] former = new short[]{1};
            short[] latter = new short[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfInts() {
            int[] former = new int[]{1};
            int[] latter = new int[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfLongs() {
            long[] former = new long[]{1};
            long[] latter = new long[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfFloats() {
            float[] former = new float[]{1};
            float[] latter = new float[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfDoubles() {
            double[] former = new double[]{1};
            double[] latter = new double[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfObjects() {
            Object[] former = new Object[]{1};
            Object[] latter = new Object[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }
    }
    public static class TestIntrospectsArrays {

        @Test
        public void forArrayOfBooleans() {
            Object former = new boolean[]{true};
            Object latter = new boolean[]{true};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfBytes() {
            Object former = new byte[]{1};
            Object latter = new byte[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfChars() {
            Object former = new char[]{1};
            Object latter = new char[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfShorts() {
            Object former = new short[]{1};
            Object latter = new short[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfInts() {
            Object former = new int[]{1};
            Object latter = new int[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfLongs() {
            Object former = new long[]{1};
            Object latter = new long[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfFloats() {
            Object former = new float[]{1};
            Object latter = new float[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfDoubles() {
            Object former = new double[]{1};
            Object latter = new double[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }

        @Test
        public void forArrayOfOfObjects() {
            Object former = new Object[]{1};
            Object latter = new Object[]{1};
            HashCodeBuilder formerBuilder = new HashCodeBuilder().append(former);
            HashCodeBuilder latterBuilder = new HashCodeBuilder().append(latter);
            Assert.assertEquals(formerBuilder.toHashCode(), latterBuilder.toHashCode());
        }
    }
}