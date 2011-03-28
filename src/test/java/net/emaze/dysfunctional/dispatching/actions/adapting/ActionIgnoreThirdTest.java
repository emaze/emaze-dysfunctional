package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.dispatching.actions.BinaryCapturingAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionIgnoreThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionIgnoreThird<O, O, O>(null);
    }

    @Test
    public void canIgnoreThirdParameter() {
        final BinaryCapturingAction<O, O> mock = new BinaryCapturingAction<O, O>(new BinaryNoop<O, O>());
        final ActionIgnoreThird<O, O, O> adapted = new ActionIgnoreThird<O, O, O>(mock);
        adapted.perform(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
