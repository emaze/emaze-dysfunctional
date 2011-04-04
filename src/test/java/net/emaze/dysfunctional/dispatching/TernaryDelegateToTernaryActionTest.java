package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryCapturingDelegate;
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
        final TernaryCapturingDelegate<O, O, O, O> capturer = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>());
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(capturer);
        adapted.perform(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals(O.ONE, capturer.first.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final TernaryCapturingDelegate<O, O, O, O> capturer = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>());
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(capturer);
        adapted.perform(O.IGNORED, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, capturer.second.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final TernaryCapturingDelegate<O, O, O, O> capturer = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>());
        final TernaryAction<O, O, O> adapted = new TernaryDelegateToTernaryAction<O, O, O>(capturer);
        adapted.perform(O.IGNORED, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, capturer.third.getContent());
    }
}
