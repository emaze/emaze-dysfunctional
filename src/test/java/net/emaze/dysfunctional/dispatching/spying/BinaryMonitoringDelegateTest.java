package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryMonitoringDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new BinaryMonitoringDelegate<O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new BinaryMonitoringDelegate<O, O, O>(new FirstParam<O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final BinaryDelegate<O, O, O> spy = new BinaryMonitoringDelegate<O, O, O>(new FirstParam<O, O>(), state);
        spy.perform(O.ONE, O.ONE);
        Assert.assertEquals(1l, state.get());
    }
}
