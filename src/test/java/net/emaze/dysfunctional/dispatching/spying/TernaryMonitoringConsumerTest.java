package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryMonitoringConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new TernaryMonitoringConsumer<O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new TernaryMonitoringConsumer<O, O, O>(new TernaryNoop<O, O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final TriConsumer<O, O, O> spy = new TernaryMonitoringConsumer<O, O, O>(new TernaryNoop<O, O, O>(), state);
        spy.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, state.get());
    }
}
