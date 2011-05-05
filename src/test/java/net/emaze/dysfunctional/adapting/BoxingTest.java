package net.emaze.dysfunctional.adapting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BoxingTest.BoxingSuite.class,
    BoxingTest.BoxingDegenerationsSuite.class,
    BoxingTest.UnboxingSuite.class,
    BoxingTest.UnboxingDegenerationsSuite.class
})
public class BoxingTest {

    public static class BoxingSuite {

        @Test
        public void canBoxArrayOfDoubles() {
            final double[] array = {1.};
            final Double[] got = Boxing.box(array);
            Assert.assertEquals(Double.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfFloats() {
            final float[] array = {1.f};
            final Float[] got = Boxing.box(array);
            Assert.assertEquals(Float.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfLongs() {
            final long[] array = {1};
            final Long[] got = Boxing.box(array);
            Assert.assertEquals(Long.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfIntegers() {
            final int[] array = {1};
            final Integer[] got = Boxing.box(array);
            Assert.assertEquals(Integer.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfShorts() {
            final short[] array = {1};
            final Short[] got = Boxing.box(array);
            Assert.assertEquals(Short.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfCharacters() {
            final char[] array = {1};
            final Character[] got = Boxing.box(array);
            Assert.assertEquals(Character.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfBytes() {
            final byte[] array = {1};
            final Byte[] got = Boxing.box(array);
            Assert.assertEquals(Byte.valueOf(array[0]), got[0]);
        }

        @Test
        public void canBoxArrayOfBooleans() {
            final boolean[] array = {true};
            final Boolean[] got = Boxing.box(array);
            Assert.assertEquals(Boolean.valueOf(array[0]), got[0]);
        }
    }

    public static class BoxingDegenerationsSuite {

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfDoublesYieldsException() {
            final double[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfFloatsYieldsException() {
            final float[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfLongsYieldsException() {
            final long[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfIntegersYieldsException() {
            final int[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfShortsYieldsException() {
            final short[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfCharactersYieldsException() {
            final char[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfBytesYieldsException() {
            final byte[] array = null;
            Boxing.box(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void boxingNullArrayOfBooleansYieldsException() {
            final boolean[] array = null;
            Boxing.box(array);
        }
    }

    public static class UnboxingSuite {
        @Test
        public void canUnboxArrayOfDoubles() {
            final Double[] array = {1.};
            final double[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Double.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfFloats() {
            final Float[] array = {1.f};
            final float[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Float.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfLongs() {
            final Long[] array = {1l};
            final long[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Long.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfIntegers() {
            final Integer[] array = {1};
            final int[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Integer.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfShorts() {
            final Short[] array = {1};
            final short[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Short.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfCharacters() {
            final Character[] array = {1};
            final char[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Character.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfBytes() {
            final Byte[] array = {1};
            final byte[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Byte.valueOf(got[0]));
        }

        @Test
        public void canUnboxArrayOfBooleans() {
            final Boolean[] array = {true};
            final boolean[] got = Boxing.unbox(array);
            Assert.assertEquals(array[0], Boolean.valueOf(got[0]));
        }
        
    }

    public static class UnboxingDegenerationsSuite {
        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfDoublesYieldsException() {
            final Double[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfFloatsYieldsException() {
            final Float[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfLongsYieldsException() {
            final Long[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfIntegersYieldsException() {
            final Integer[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfShortsYieldsException() {
            final Short[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfCharactersYieldsException() {
            final Character[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfBytesYieldsException() {
            final Byte[] array = null;
            Boxing.unbox(array);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unboxingNullArrayOfBooleansYieldsException() {
            final Boolean[] array = null;
            Boxing.unbox(array);
        }
    }
}
