package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MonitoringDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new MonitoringDelegate<O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new MonitoringDelegate<O, O>(new Identity<O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final Function<O, O> spy = new MonitoringDelegate<O, O>(new Identity<O>(), state);
        spy.apply(O.ONE);
        Assert.assertEquals(1l, state.get());
    }
}
