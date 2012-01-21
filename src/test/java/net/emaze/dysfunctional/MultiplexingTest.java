package net.emaze.dysfunctional;

import net.emaze.dysfunctional.Multiplexing;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.options.MaybeIterator;
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
    MultiplexingTest.Mux.class,
    MultiplexingTest.Muxl.class,
    MultiplexingTest.Demux.class,
    MultiplexingTest.Demuxl.class,
    MultiplexingTest.Roundrobin.class,
    MultiplexingTest.Facade.class
})
public class MultiplexingTest {

    public static List<O> AN_ITERABLE = Arrays.asList(O.ONE);

    public static class Chain {

        @Test(expected = IllegalArgumentException.class)
        public void cannotChainNullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.chain(iterable);
        }

        @Test
        public void canChainFromIterable() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<O> chained = Multiplexing.chain(iterable);
            Assert.assertNotNull(chained);
        }

        @Test
        public void canChainFromIterator() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
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
            final List<Iterable<O>> iterable = Arrays.<Iterable<O>>asList(AN_ITERABLE);
            final Iterator<O> flattened = Multiplexing.flatten(iterable);
            Assert.assertNotNull(flattened);
        }

        @Test
        public void canFlattenFromIterator() {
            final List<Iterable<O>> iterable = Arrays.<Iterable<O>>asList(AN_ITERABLE);
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

    public static class Mux {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.mux(iterable);
        }

        @Test
        public void canMuxFromIterable() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<O> muxed = Multiplexing.mux(iterable);
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxFromIterator() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<O> muxed = Multiplexing.mux(iterable.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxTwoValues() {
            final Iterator<O> muxed = Multiplexing.mux(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxThreeValues() {
            final Iterator<O> muxed = Multiplexing.mux(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }
    }

    public static class Muxl {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxLongestANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.muxl(iterable);
        }

        @Test
        public void canMuxLongestFromIterable() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<Maybe<O>> muxed = Multiplexing.muxl(iterable);
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromIterator() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<Maybe<O>> muxed = Multiplexing.muxl(iterable.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromTwoValues() {
            final Iterator<Maybe<O>> muxed = Multiplexing.muxl(AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }

        @Test
        public void canMuxLongestFromThreeValues() {
            final Iterator<Maybe<O>> muxed = Multiplexing.muxl(AN_ITERABLE.iterator(), AN_ITERABLE.iterator(), AN_ITERABLE.iterator());
            Assert.assertNotNull(muxed);
        }
    }

    public static class Demux {

        @Test(expected = IllegalArgumentException.class)
        public void cannotDemuxANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.demux(1, iterable);
        }

        @Test
        public void canDemuxFromIterable() {
            Iterator<List<O>> demux = Multiplexing.demux(1, AN_ITERABLE);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canDemuxFromIterator() {
            Iterator<List<O>> demux = Multiplexing.demux(1, AN_ITERABLE.iterator());
            Assert.assertNotNull(demux);
        }

        @Test
        public void canDemuxTwoValues() {
            Iterator<List<O>> demux = Multiplexing.demux(1, O.IGNORED, O.IGNORED);
            Assert.assertNotNull(demux);
        }

        @Test
        public void canDemuxThreeValues() {
            Iterator<List<O>> demux = Multiplexing.demux(1, O.IGNORED, O.IGNORED, O.IGNORED);
            Assert.assertNotNull(demux);
        }
    }

    public static class Demuxl {

        @Test(expected = IllegalArgumentException.class)
        public void cannotDemuxlANullIterable() {
            final Iterable<Maybe<O>> iterable = null;
            Multiplexing.demuxl(1, iterable);
        }

        @Test
        public void canDemuxLongestFromIterable() {
            MaybeIterator<O> maybeIter = new MaybeIterator<O>(AN_ITERABLE.iterator());
            Iterator<List<Maybe<O>>> demuxl = Multiplexing.demuxl(1, Iterations.oneTime(maybeIter));
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canDemuxLongestFromIterator() {
            Iterator<List<Maybe<O>>> demuxl = Multiplexing.demuxl(1, new MaybeIterator<O>(AN_ITERABLE.iterator()));
            Assert.assertNotNull(demuxl);
        }

        @Test
        public void canDemuxLongestFromArray() {
            Iterator<List<Maybe<O>>> demuxl = Multiplexing.demuxl(1, Maybe.just(O.IGNORED), Maybe.<O>nothing());
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
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
            final Iterator<O> rr = Multiplexing.roundrobin(iterable);
            Assert.assertNotNull(rr);
        }

        @Test
        public void canRoundrobinFromIterator() {
            final List<Iterator<O>> iterable = Arrays.asList(AN_ITERABLE.iterator());
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

    public static class Facade {

        @Test
        public void facadeIsNotFinal() {
            new Multiplexing() {
            };
        }
    }
}
