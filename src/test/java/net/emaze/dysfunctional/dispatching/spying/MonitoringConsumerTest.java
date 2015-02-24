package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MonitoringConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new MonitoringConsumer<O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new MonitoringConsumer<O>(new Noop<O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final Consumer<O> spy = new MonitoringConsumer<O>(new Noop<O>(), state);
        spy.accept(O.ONE);
        Assert.assertEquals(1l, state.get());
    }
}
