package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryMonitoringPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new BinaryMonitoringPredicate<O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new BinaryMonitoringPredicate<O, O>(new BinaryAlways<O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final BiPredicate<O, O> spy = new BinaryMonitoringPredicate<O, O>(new BinaryAlways<O, O>(), state);
        spy.test(O.ONE, O.ONE);
        Assert.assertEquals(1l, state.get());
    }
}
