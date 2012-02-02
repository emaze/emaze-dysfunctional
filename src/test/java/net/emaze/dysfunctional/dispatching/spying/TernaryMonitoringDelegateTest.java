package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryMonitoringDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new TernaryMonitoringDelegate<O, O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new TernaryMonitoringDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final TernaryDelegate<O, O, O, O> spy = new TernaryMonitoringDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), state);
        spy.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, state.get());
    }
}
