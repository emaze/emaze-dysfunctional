package net.emaze.dysfunctional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.collections.ArrayListFactory;
import java.util.function.Supplier;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dangelocola, rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MultiplexingTest.Chain.class,
    MultiplexingTest.Flatten.class,
    MultiplexingTest.RoundRobinShortest.class,
    MultiplexingTest.RoundRobinLongest.class,
    MultiplexingTest.Unchain.class,
    MultiplexingTest.UnchainWithExactChannelSize.class,
    MultiplexingTest.Roundrobin.class,
    MultiplexingTest.Cycle.class,
    MultiplexingTest.Batch.class,
    MultiplexingTest.Facade.class
})
public class MultiplexingTest {

    private static Iterable<O> AN_ITERABLE = Iterations.iterable(O.ONE);

    public static class Chain {

        @Test(expected = IllegalArgumentException.class)
        public void cannotChainNullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.chain(iterable);
        }

        @Test
        public void canChainFromIterable() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> chained = Multiplexing.chain(iterable);
            Assert.assertNotNull(chained);
        }

        @Test
        public void canChainFromIterator() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> chained = Multiplexing.chain(iterable.iterator());
            Assert.assertNotNull(chained);
        }

        @Test
        public void canChainFromTwoValues() {
            final Iterator<O> chained = Multiplexing.chain(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(chained);
        }

        @Test
        public void canChainFromThreeValues() {
            final Iterator<O> chained = Multiplexing.chain(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(chained);
        }
    }

    public static class Flatten {

        @Test(expected = IllegalArgumentException.class)
        public void cannotFlatternANullIterable() {
            final Iterable<Iterable<O>> iterable = null;
            Multiplexing.flatten(iterable);
        }

        @Test
        public void canFlattenFromIterable() {
            final Iterable<Iterable<O>> iterable = Iterations.iterable(AN_ITERABLE);
            final Iterator<O> flattened = Multiplexing.flatten(iterable);
            Assert.assertNotNull(flattened);
        }

        @Test
        public void canFlattenFromIterator() {
            final Iterable<Iterable<O>> iterable = Iterations.iterable(AN_ITERABLE);
            final Iterator<O> flattened = Multiplexing.flatten(iterable.iterator());
            Assert.assertNotNull(flattened);
        }

        @Test
        public void canFlattenFromTwoValues() {
            final Iterator<O> flattened = Multiplexing.flatten(AN_ITERABLE, AN_ITERABLE);
            Assert.assertNotNull(flattened);
        }

        @Test
        public void canFlattenFromThreeValues() {
            final Iterator<O> flattened = Multiplexing.flatten(AN_ITERABLE, AN_ITERABLE, AN_ITERABLE);
            Assert.assertNotNull(flattened);
        }
    }

    public static class RoundRobinShortest {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.roundrobinShortest(iterable);
        }

        @Test
        public void canMuxFromIterable() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> muxed = Multiplexing.roundrobinShortest(iterable);
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxFromIterator() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> muxed = Multiplexing.roundrobinShortest(iterable.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxTwoValues() {
            final Iterator<O> muxed = Multiplexing.roundrobinShortest(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxThreeValues() {
            final Iterator<O> muxed = Multiplexing.roundrobinShortest(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }
    }

    public static class RoundRobinLongest {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxLongestANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.roundrobinLongest(iterable);
        }

        @Test
        public void canMuxLongestFromIterable() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<Optional<O>> muxed = Multiplexing.roundrobinLongest(iterable);
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromIterator() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<Optional<O>> muxed = Multiplexing.roundrobinLongest(iterable.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromTwoValues() {
            final Iterator<Optional<O>> muxed = Multiplexing.roundrobinLongest(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromThreeValues() {
            final Iterator<Optional<O>> muxed = Multiplexing.roundrobinLongest(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }
    }

    public static class Unchain {

        @Test(expected = IllegalArgumentException.class)
        public void cannotUnchainANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.unchain(1, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotUnchainNullIterableUsingProvider() {
            final Supplier<ArrayList<O>> provider = new ArrayListFactory<O>();
            final Iterable<O> iterable = null;
            Multiplexing.unchain(1, iterable, provider);
        }

        @Test
        public void canUnchainFromIterable() {
            Iterator<List<O>> demux = Multiplexing.unchain(1, AN_ITERABLE);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainFromIterator() {
            Iterator<List<O>> demux = Multiplexing.unchain(1, AN_ITERABLE.iterator());
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainFromIteratorUsingProvider() {
            Supplier<ArrayList<O>> provider = new ArrayListFactory<O>();
            Iterator<ArrayList<O>> demux = Multiplexing.unchain(1, AN_ITERABLE.iterator(), provider);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainFromIterableUsingProvider() {
            Supplier<ArrayList<O>> provider = new ArrayListFactory<O>();
            Iterator<ArrayList<O>> demux = Multiplexing.unchain(1, AN_ITERABLE, provider);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainFromArrayUsingProvider() {
            Supplier<ArrayList<O>> provider = new ArrayListFactory<O>();
            Iterator<ArrayList<O>> demux = Multiplexing.unchain(1, provider, O.ONE, O.ANOTHER);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainTwoValues() {
            Iterator<List<O>> demux = Multiplexing.unchain(1, O.IGNORED, O.IGNORED);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canUnchainThreeValues() {
            Iterator<List<O>> demux = Multiplexing.unchain(1, O.IGNORED, O.IGNORED, O.IGNORED);
            Assert.assertNotNull(demux);
        }
    }

    public static class UnchainWithExactChannelSize {

        @Test(expected = IllegalArgumentException.class)
        public void cannotUnchainANullIterable() {
            final Iterable<O> iterable = null;
            Multiplexing.unchainWithExactChannelSize(1, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void cannotUnchainANullIterableWithProvider() {
            Supplier<ArrayList<Optional<O>>> provider = new ArrayListFactory<Optional<O>>();
            final Iterable<O> iterable = null;
            Multiplexing.unchainWithExactChannelSize(1, iterable, provider);
        }

        @Test
        public void canUnchainFromIterable() {
            Iterator<List<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, AN_ITERABLE);
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canUnchainFromIterator() {
            Iterator<List<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, AN_ITERABLE.iterator());
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canUnchainFromArray() {
            Iterator<List<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, O.IGNORED);
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canUnchainFromIterableWithProvider() {
            Supplier<ArrayList<Optional<O>>> provider = new ArrayListFactory<Optional<O>>();
            Iterator<ArrayList<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, AN_ITERABLE, provider);
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canUnchainFromIteratorWithProvider() {
            Supplier<ArrayList<Optional<O>>> provider = new ArrayListFactory<Optional<O>>();
            Iterator<ArrayList<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, AN_ITERABLE.iterator(), provider);
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canUnchainFromArrayWithProvider() {
            Supplier<ArrayList<Optional<O>>> provider = new ArrayListFactory<Optional<O>>();
            Iterator<ArrayList<Optional<O>>> demuxl = Multiplexing.unchainWithExactChannelSize(1, provider, O.IGNORED);
            Assert.assertNotNull(demuxl);
        }
    }

    public static class Roundrobin {

        @Test(expected = IllegalArgumentException.class)
        public void cannotRoundRobinANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.roundrobin(iterable);
        }

        @Test
        public void canRoundrobinFromIterable() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> rr = Multiplexing.roundrobin(iterable);
            Assert.assertNotNull(rr);
        }

        @Test
        public void canRoundrobinFromIterator() {
            final Iterable<Iterator<O>> iterable = Iterations.iterable(AN_ITERABLE.iterator());
            final Iterator<O> rr = Multiplexing.roundrobin(iterable.iterator());
            Assert.assertNotNull(rr);
        }

        @Test
        public void canRoundrobinFromTwoValues() {
            final Iterator<O> rr = Multiplexing.roundrobin(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(rr);
        }

        @Test
        public void canRoundrobinFromThreeValues() {
            final Iterator<O> rr = Multiplexing.roundrobin(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(rr);
        }
    }

    public static class Cycle {

        @Test(expected = IllegalArgumentException.class)
        public void cannotCycleANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.cycle(iterable);
        }

        @Test
        public void canCycleFromIterable() {
            final Iterable<O> iterable = Iterations.iterable(O.ONE);
            final Iterator<O> cycle = Multiplexing.cycle(iterable);
            Assert.assertNotNull(cycle);
        }

        @Test
        public void canCycleFromIterator() {
            final Iterator<O> iterator = Iterations.iterator(O.ONE);
            final Iterator<O> cycle = Multiplexing.cycle(iterator);
            Assert.assertNotNull(cycle);
        }

        @Test
        public void canCycleFromTwoValues() {
            final Iterator<O> cycle = Multiplexing.cycle(O.ONE, O.ANOTHER);
            Assert.assertNotNull(cycle);
        }

        @Test
        public void canCycleFromThreeValues() {
            final Iterator<O> cycle = Multiplexing.cycle(O.ONE, O.ANOTHER, O.YET_ANOTHER);
            Assert.assertNotNull(cycle);
        }
    }

    public static class Batch {

        @Test
        public void canBatchAnIterator() {
            final Iterator<O> source = Iterations.iterator(O.ONE);
            final Iterator<List<O>> got = Multiplexing.batch(1, source);
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test
        public void canBatchAnIterable() {
            final Iterable<O> source = Iterations.iterable(O.ONE);
            final Iterator<List<O>> got = Multiplexing.batch(1, source);
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test
        public void canBatchAnArray() {
            final O[] source = new O[]{O.ONE};
            final Iterator<List<O>> got = Multiplexing.batch(1, source);
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test
        public void canBatchAnIteratorWithChannelProvider() {
            final Iterator<O> source = Iterations.iterator(O.ONE);
            final Iterator<ArrayList<O>> got = Multiplexing.batch(1, source, new ArrayListFactory<O>());
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test
        public void canBatchAnIterableWithChannelProvider() {
            final Iterable<O> source = Iterations.iterable(O.ONE);
            final Iterator<ArrayList<O>> got = Multiplexing.batch(1, source, new ArrayListFactory<O>());
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test
        public void canBatchAnArrayWithChannelProvider() {
            final O[] source = new O[]{O.ONE};
            final Iterator<ArrayList<O>> got = Multiplexing.batch(1, source, new ArrayListFactory<O>());
            Assert.assertEquals(Arrays.asList(Arrays.asList(O.ONE)), Consumers.all(got));
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingBatchWithNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Multiplexing.batch(1, iterable);
        }

        @Test(expected = IllegalArgumentException.class)
        public void callingBatchWithProviderWithNullIterableYieldsException() {
            final Iterable<O> iterable = null;
            Multiplexing.batch(1, iterable, new ArrayListFactory<O>());
        }
    }

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Multiplexing() {
            };
        }
    }
}
