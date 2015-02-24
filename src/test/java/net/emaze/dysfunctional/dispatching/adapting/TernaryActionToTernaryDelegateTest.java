package net.emaze.dysfunctional.dispatching.adapting;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryActionToTernaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new TernaryActionToTernaryDelegate<O, O, O>(null);
    }

    @Test
    public void callingAdapterCallsAdapted() {
        final AtomicLong calls = new AtomicLong();
        final TernaryAction<O, O, O> monitor = Spies.monitor(new TernaryNoop<O, O, O>(), calls);
        final TriFunction<O, O, O, Void> delegate = new TernaryActionToTernaryDelegate<>(monitor);
        delegate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(1l, calls.get());
    }

    @Test
    public void firstParamIsForwardedToAdaptedAction() {
        final Box<O> param1 = Box.empty();
        final TernaryAction<O, O, O> monitor = Spies.spy1st(new TernaryNoop<O, O, O>(), param1);
        final TriFunction<O, O, O, Void> delegate = new TernaryActionToTernaryDelegate<>(monitor);
        delegate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParamIsForwardedToAdaptedAction() {
        final Box<O> param2 = Box.empty();
        final TernaryAction<O, O, O> monitor = Spies.spy2nd(new TernaryNoop<O, O, O>(), param2);
        final TriFunction<O, O, O, Void> delegate = new TernaryActionToTernaryDelegate<>(monitor);
        delegate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void thirdParamIsForwardedToAdaptedAction() {
        final Box<O> param3 = Box.empty();
        final TernaryAction<O, O, O> monitor = Spies.spy3rd(new TernaryNoop<O, O, O>(), param3);
        final TriFunction<O, O, O, Void> delegate = new TernaryActionToTernaryDelegate<>(monitor);
        delegate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, param3.getContent());
    }
}
