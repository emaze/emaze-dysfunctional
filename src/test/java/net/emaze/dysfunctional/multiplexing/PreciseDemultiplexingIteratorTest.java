package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.options.Maybe;
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
    PreciseDemultiplexingIteratorTest.Degenerations.class,
    PreciseDemultiplexingIteratorTest.Functions.class
})
public class PreciseDemultiplexingIteratorTest {

    public static class Functions {

        @Test
        public void consumePreciseDemultiplexingIteratorWithTwoChannelsYieldsTwoChannels() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1), Maybe.just(1)).iterator();
            PreciseDemultiplexingIterator<Integer> demu = new PreciseDemultiplexingIterator<Integer>(iter, 2);
            Assert.assertEquals(Arrays.asList(Maybe.just(1), Maybe.just(1)), demu.next());
        }

        @Test
        public void emptyIteratorHasNoNext() {
            Iterator<Maybe<Integer>> iter = Arrays.<Maybe<Integer>>asList().iterator();
            PreciseDemultiplexingIterator<Integer> demu = new PreciseDemultiplexingIterator<Integer>(iter, 2);
            Assert.assertFalse(demu.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingPreciseDemultiplexingIteratorWithNullIteratorYieldsException() {
            new PreciseDemultiplexingIterator<Object>(null, 2);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingPreciseDemultiplexingIteratorWithNonPositiveChannelsCountYieldsException() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1)).iterator();
            new PreciseDemultiplexingIterator<Integer>(iter, 0);
        }

        @Test(expected = UnsupportedOperationException.class)
        public void removingFromIteratorYieldsException() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1)).iterator();
            PreciseDemultiplexingIterator<Integer> demu = new PreciseDemultiplexingIterator<Integer>(iter, 1);
            demu.next();
            demu.remove();
        }
    }
}
