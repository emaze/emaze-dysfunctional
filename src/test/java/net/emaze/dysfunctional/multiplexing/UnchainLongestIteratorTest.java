package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Compositions;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.casts.Vary;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import java.util.function.Supplier;
import java.util.Optional;
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
    UnchainLongestIteratorTest.Degenerations.class,
    UnchainLongestIteratorTest.Functions.class
})
public class UnchainLongestIteratorTest {

    final static Supplier<List<Optional<Integer>>> MAYBE_INTEGER_LIST_FACTORY = Compositions.compose(new Vary<ArrayList<Optional<Integer>>, List<Optional<Integer>>>(), new ArrayListFactory<Optional<Integer>>());

    public static class Functions {

        @Test
        public void consumeWithTwoChannelsYieldsTwoChannels() {
            final Iterator<Integer> iter = Iterations.iterator(1, 2);
            final ConstantSupplier<Optional<Integer>> channelSizeIsAlways2 = new ConstantSupplier<Optional<Integer>>(Optional.of(2));
            final UnchainLongestIterator<List<Optional<Integer>>, Integer> demu = new UnchainLongestIterator<List<Optional<Integer>>, Integer>(channelSizeIsAlways2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertEquals(Arrays.asList(Optional.of(1), Optional.of(2)), demu.next());
        }

        @Test
        public void emptyIteratorHasNoNext() {
            final Iterator<Integer> iter = Iterations.iterator();
            final ConstantSupplier<Optional<Integer>> channelSizeIsAlways2 = new ConstantSupplier<Optional<Integer>>(Optional.of(2));
            final UnchainLongestIterator<List<Optional<Integer>>, Integer> demu = new UnchainLongestIterator<List<Optional<Integer>>, Integer>(channelSizeIsAlways2, iter, MAYBE_INTEGER_LIST_FACTORY);
            Assert.assertFalse(demu.hasNext());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullIteratorYieldsException() {
            new UnchainLongestIterator<List<Optional<Integer>>, Integer>(new ConstantSupplier<Optional<Integer>>(Optional.of(2)), null, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullChannelsSizesProviderYieldsException() {
            Iterator<Integer> iter = Iterations.iterator(1);
            new UnchainLongestIterator<List<Optional<Integer>>, Integer>(null, iter, MAYBE_INTEGER_LIST_FACTORY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingIteratorWithNullProviderYieldsException() {
            Iterator<Integer> iter = Iterations.iterator(1);
            new UnchainLongestIterator<List<Optional<Integer>>, Integer>(new ConstantSupplier<Optional<Integer>>(Optional.of(2)), iter, null);
        }
    }
}
