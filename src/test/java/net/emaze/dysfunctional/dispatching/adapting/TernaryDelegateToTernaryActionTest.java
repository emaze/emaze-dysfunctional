package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryDelegateToTernaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new TernaryDelegateToTernaryAction<O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final TernaryDelegate<O, O, O, O> spy = Spies.spy1st(new FirstParamOfThree<O, O, O>(), param1);
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(spy);
        adapted.perform(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final TernaryDelegate<O, O, O, O> spy = Spies.spy2nd(new FirstParamOfThree<O, O, O>(), param2);
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(spy);
        adapted.perform(O.IGNORED, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param2.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final Box<O> param3 = Box.empty();
        final TernaryDelegate<O, O, O, O> spy = Spies.spy3rd(new FirstParamOfThree<O, O, O>(), param3);
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(spy);
        adapted.perform(O.IGNORED, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param3.getContent());
    }
}
