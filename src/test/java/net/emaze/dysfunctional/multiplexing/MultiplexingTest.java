package net.emaze.dysfunctional.multiplexing;

import java.util.Iterator;
import net.emaze.dysfunctional.options.MaybeIterator;
import net.emaze.dysfunctional.testing.O;
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
    MultiplexingTest.Demux.class,
    MultiplexingTest.Roundrobin.class
})
public class MultiplexingTest {

    public static class Chain {

        @Test(expected = IllegalArgumentException.class)
        public void cannotChainNullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.chain(iterable);
        }
    }

    public static class Flatten {

        @Test(expected = IllegalArgumentException.class)
        public void cannotFlatternANullIterable() {
            final Iterable<Iterable<O>> iterable = null;
            Multiplexing.flatten(iterable);
        }
    }

    public static class Mux {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.mux(iterable);
        }
    }

    public static class Muxl {

        @Test(expected = IllegalArgumentException.class)
        public void cannotMuxlANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.muxl(iterable);
        }
    }

    public static class Demux {

        @Test(expected = IllegalArgumentException.class)
        public void cannotDemuxANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.demux(1, iterable);
        }
    }

    public static class Demuxl {

        @Test(expected = IllegalArgumentException.class)
        public void cannotDemuxlANullIterable() {
            final MaybeIterator<O> iterable = null;
            Multiplexing.demuxl(1, iterable);
        }
    }

    public static class Roundrobin {

        @Test(expected = IllegalArgumentException.class)
        public void cannotRoundRobinANullIterable() {
            final Iterable<Iterator<O>> iterable = null;
            Multiplexing.roundrobin(iterable);
        }
    }
}
