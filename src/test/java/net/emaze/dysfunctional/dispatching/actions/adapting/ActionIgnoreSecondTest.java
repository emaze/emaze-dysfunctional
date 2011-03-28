package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.dispatching.actions.CapturingAction;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionIgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ActionIgnoreSecond<O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final CapturingAction<O> mock = new CapturingAction<O>(new Noop<O>());
        final ActionIgnoreSecond<O, O> adapted = new ActionIgnoreSecond<O, O>(mock);
        adapted.perform(O.ONE, O.IGNORED);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
