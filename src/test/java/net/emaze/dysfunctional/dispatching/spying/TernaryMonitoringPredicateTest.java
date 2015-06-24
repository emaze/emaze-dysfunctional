package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryMonitoringPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new TernaryMonitoringPredicate<O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new TernaryMonitoringPredicate<O, O, O>(new TernaryAlways<O, O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final TriPredicate<O, O, O> spy = new TernaryMonitoringPredicate<O, O, O>(new TernaryAlways<O, O, O>(), state);
        spy.test(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, state.get());
    }
}
