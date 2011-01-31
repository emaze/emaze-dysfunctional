package net.emaze.dysfunctional.strings;

import java.util.Arrays;
import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.iterations.ConstantIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    StringsTest.Join.class,
    StringsTest.Interpose.class,
    StringsTest.IsEmpty.class
})
public class StringsTest {

    public static class Join {

        @Test
        public void canJoinIntegers() {
            Iterator<Integer> values = Arrays.asList(1, 2, 3, 4).iterator();
            String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinEmptyIterator() {
            Iterator<Object> values = Arrays.asList().iterator();
            String output = Strings.join(values);
            Assert.assertEquals("", output);
        }
    }

    public static class Interpose {

        @Test
        public void canInterposeIntegers() {
            Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
            String output = Strings.interpose(values, 0);
            Assert.assertEquals("102030405", output);
        }

        @Test
        public void canInterposeWithMultipleSeparators() {
            Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
            Iterator<String> separators = Arrays.asList("-", "+", "-", "+").iterator();
            String output = Strings.interpose(values, separators);
            Assert.assertEquals("1-2+3-4+5", output);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesYieldsException() {
            Strings.interpose(null, 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesYieldsExceptionOverload() {
            Strings.interpose(null, new ConstantIterator<Integer>(0));
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullSeparatorYieldsException() {
            Strings.interpose(Arrays.<String>asList().iterator(), null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullSeparatorsYieldsException() {
            Strings.interpose(Arrays.<String>asList().iterator(), (Iterator<String>) null);
        }
    }

    public static class IsEmpty {

        @Test
        public void nullStringIsEmpty() {
            Assert.assertTrue(Strings.isEmpty(null));
        }

        @Test
        public void emptyStringIsEmpty() {
            Assert.assertTrue(Strings.isEmpty(""));
        }

        @Test
        public void nonEmptyStringIsNotEmpty() {
            Assert.assertFalse(Strings.isEmpty("non empty text"));
        }
    }
}
