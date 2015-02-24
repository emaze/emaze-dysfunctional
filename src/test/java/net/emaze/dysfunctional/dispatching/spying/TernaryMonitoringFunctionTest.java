package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryMonitoringFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAdaptedYieldsException() {
        new TernaryMonitoringFunction<O, O, O, O>(null, new AtomicLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMonitorWithNullAtomicLongYieldsException() {
        new TernaryMonitoringFunction<O, O, O, O>(new FirstParamOfThree<O, O, O>(), null);
    }

    @Test
    public void callingIncrementsTheAtomicLong() {
        final AtomicLong state = new AtomicLong();
        final TriFunction<O, O, O, O> spy = new TernaryMonitoringFunction<O, O, O, O>(new FirstParamOfThree<O, O, O>(), state);
        spy.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, state.get());
    }
}
