package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MonitoringPropositionTest {
    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new MonitoringProposition(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new MonitoringProposition(new Yes(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final BooleanSupplier spy = new MonitoringProposition(new Yes(), state);
        spy.getAsBoolean();
        Assert.assertEquals(1l, state.get());
    }
}
