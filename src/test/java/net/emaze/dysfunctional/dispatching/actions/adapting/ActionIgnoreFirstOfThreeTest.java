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
public class ActionIgnoreFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ActionIgnoreFirstOfThree<O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final BinaryCapturingAction<O, O> mock = new BinaryCapturingAction<O, O>(new BinaryNoop<O, O>());
        final ActionIgnoreFirstOfThree<O, O, O> adapted = new ActionIgnoreFirstOfThree<O, O, O>(mock);
        adapted.perform(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
