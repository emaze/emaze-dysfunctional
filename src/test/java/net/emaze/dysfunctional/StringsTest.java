package net.emaze.dysfunctional;

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
    StringsTest.Facade.class
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
        public void canJoinIterable() {
            final Iterable<Integer> values = Arrays.asList(1, 2, 3, 4);
            final String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinArray() {
            final Integer[] values = {1, 2, 3, 4};
            final String output = Strings.join(values);
            Assert.assertEquals("1234", output);
        }

        @Test
        public void canJoinEmptyIterator() {
            Iterator<Object> values = Arrays.asList().iterator();
            String output = Strings.join(values);
            Assert.assertEquals("", output);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotJoinANullIterator() {
            final Iterator<Object> nullIterator = null;
            Strings.join(nullIterator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotJoinANullIterable() {
            final Iterable<Object> nullIterable = null;
            Strings.join(nullIterable);
        }
    }

    public static class Interpose {

        @Test
        public void canInterposeIteratorIntegers() {
            Iterator<Integer> values = Arrays.asList(1, 2, 3, 4, 5).iterator();
            String output = Strings.interpose(values, 0);
            Assert.assertEquals("102030405", output);
        }

        @Test
        public void canInterposeIterable() {
            Iterable<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
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
            final Iterator<Integer> iterator = null;
            Strings.interpose(iterator, 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void interposingNullValuesYieldsExceptionOverload() {
            final Iterator<Integer> iterator = null;
            Strings.interpose(iterator, new ConstantIterator<Integer>(0));
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

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Strings() {
            };
        }
    }
}
