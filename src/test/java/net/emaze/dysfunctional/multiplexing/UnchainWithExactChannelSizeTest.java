package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
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
    UnchainWithExactChannelSizeTest.Degenerations.class,
    UnchainWithExactChannelSizeTest.Functions.class
})
public class UnchainWithExactChannelSizeTest {

    final static Provider<List<Maybe<Integer>>> MAYBE_INTEGER_LIST_FACTORY = Compositions.compose(new Vary<List<Maybe<Integer>>, ArrayList<Maybe<Integer>>>(), new ArrayListFactory<Maybe<Integer>>());

    public static class Functions {

        @Test
        public void consumeWithTwoChannelsYieldsTwoChannels() {
            final Iterator<Integer> iter = Iterations.iterator(1, 2);
            final ConstantProvider<Maybe<Integer>> channelSizeIsAlways2 = new ConstantProvider<Maybe<Integer>>(Maybe.just(2));
            final UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer> demu = new UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer>(channelSizeIsAlways2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(Maybe.just(1), Maybe.just(2)), demu.next());
        }

        @Test
        public void emptyIteratorHasNoNext() {
            final Iterator<Integer> iter = Iterations.iterator();
            final ConstantProvider<Maybe<Integer>> channelSizeIsAlways2 = new ConstantProvider<Maybe<Integer>>(Maybe.just(2));
            final UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer> demu = new UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer>(channelSizeIsAlways2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertFalse(demu.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullIteratorYieldsException() {
            new UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer>(new ConstantProvider<Maybe<Integer>>(Maybe.just(2)), null, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullChannelsSizesProviderYieldsException() {
            Iterator<Integer> iter = Iterations.iterator(1);
            new UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer>(null, iter, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullProviderYieldsException() {
            Iterator<Integer> iter = Iterations.iterator(1);
            new UnchainWithExactChannelSizeIterator<List<Maybe<Integer>>, Integer>(new ConstantProvider<Maybe<Integer>>(Maybe.just(2)), iter, null);
        }
    }
}
