package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import java.util.function.Supplier;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MonitoringSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new MonitoringSupplier<O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new MonitoringSupplier<O>(new ConstantSupplier<O>(O.ONE), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final Supplier<O> spy = new MonitoringSupplier<O>(new ConstantSupplier<O>(O.ONE), state);
        spy.get();
        Assert.assertEquals(1l, state.get());
    }
}
