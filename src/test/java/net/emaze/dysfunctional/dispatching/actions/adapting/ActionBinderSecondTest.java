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
public class ActionBinderSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderSecond<O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final BinaryCapturingAction<O, O> mock = new BinaryCapturingAction<O, O>(new BinaryNoop<O, O>());
        final ActionBinderSecond<O, O> adapted = new ActionBinderSecond<O, O>(mock, O.ONE);
        adapted.perform(O.ANOTHER);
        Assert.assertEquals(mock.second.getContent(), O.ONE);
    }
}
