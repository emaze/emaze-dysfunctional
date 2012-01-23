package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryMonitoringActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new TernaryMonitoringAction<O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new TernaryMonitoringAction<O, O, O>(new TernaryNoop<O, O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final TernaryAction<O, O, O> spy = new TernaryMonitoringAction<O, O, O>(new TernaryNoop<O, O, O>(), state);
        spy.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, state.get());
    }
}
