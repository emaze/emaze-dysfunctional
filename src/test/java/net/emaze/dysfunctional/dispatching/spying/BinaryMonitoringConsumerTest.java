package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryMonitoringConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new BinaryMonitoringConsumer<O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new BinaryMonitoringConsumer<O, O>(new BinaryNoop<O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final BiConsumer<O, O> spy = new BinaryMonitoringConsumer<O, O>(new BinaryNoop<O, O>(), state);
        spy.accept(O.ONE, O.ONE);
        Assert.assertEquals(1l, state.get());
    }
}
