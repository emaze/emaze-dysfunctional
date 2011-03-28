package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.dispatching.actions.TernaryCapturingAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionBinderThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderThird<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindThirdParameter() {
        final TernaryCapturingAction<O, O, O> mock = new TernaryCapturingAction<O, O, O>(new TernaryNoop<O, O, O>());
        final ActionBinderThird<O, O, O> adapted = new ActionBinderThird<O, O, O>(mock, O.ONE);
        adapted.perform(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(mock.third.getContent(), O.ONE);
    }
}
