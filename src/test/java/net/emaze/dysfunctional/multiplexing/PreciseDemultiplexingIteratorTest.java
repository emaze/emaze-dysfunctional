package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.casts.Narrow;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
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

    final static Provider<List<Maybe<Integer>>> MAYBE_INTEGER_LIST_FACTORY = Compositions.compose(new Narrow<List<Maybe<Integer>>, ArrayList<Maybe<Integer>>>(), new ArrayListFactory<Maybe<Integer>>());

    public static class Functions {

        @Test
        public void consumePreciseDemultiplexingIteratorWithTwoChannelsYieldsTwoChannels() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1), Maybe.just(1)).iterator();
            PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer> demu = new PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer>(2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(Maybe.just(1), Maybe.just(1)), demu.next());
        }

        @Test
        public void emptyIteratorHasNoNext() {
            Iterator<Maybe<Integer>> iter = Arrays.<Maybe<Integer>>asList().iterator();
            PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer> demu = new PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer>(2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertFalse(demu.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingPreciseDemultiplexingIteratorWithNullIteratorYieldsException() {
            new PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer>(2, null, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingPreciseDemultiplexingIteratorWithNonPositiveChannelsCountYieldsException() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1)).iterator();
            new PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer>(0, iter, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingPreciseDemultiplexingIteratorWithNullProviderYieldsException() {
            Iterator<Maybe<Integer>> iter = Arrays.asList(Maybe.just(1)).iterator();
            new PreciseDemultiplexingIterator<List<Maybe<Integer>>, Integer>(2, iter, null);
        }
    }
}
