package net.emaze.dysfunctional.multiplexing;

import org.junit.Test;

/**
 *
 * @author dangelocola
 */
public class MultiplexingTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotFlatternANullIterable() {
        final Iterable<Object> iterable = null;
        Multiplexing.flatten(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotChaingANullIterable() {
        final Iterable iterable = null;
        Multiplexing.chain(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMuxANullIterable() {
        final Iterable iterable = null;
        Multiplexing.mux(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMuxlANullIterable() {
        final Iterable iterable = null;
        Multiplexing.muxl(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDemuxANullIterable() {
        final Iterable iterable = null;
        Multiplexing.demux(1, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDemuxlANullIterable() {
        final Iterable iterable = null;
        Multiplexing.demuxl(1, iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotRoundRobinANullIterable() {
        final Iterable iterable = null;
        Multiplexing.roundrobin(iterable);
    }

}
