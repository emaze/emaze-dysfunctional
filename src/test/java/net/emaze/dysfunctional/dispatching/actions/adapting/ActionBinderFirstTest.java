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
public class ActionBinderFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderFirst<O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final BinaryCapturingAction<O, O> mock = new BinaryCapturingAction<O, O>(new BinaryNoop<O, O>());
        final ActionBinderFirst<O, O> adapted = new ActionBinderFirst<O, O>(mock, O.ONE);
        adapted.perform(O.ANOTHER);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
