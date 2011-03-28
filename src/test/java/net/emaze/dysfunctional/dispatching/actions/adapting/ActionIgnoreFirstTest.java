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
public class ActionIgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionIgnoreFirst<O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final CapturingAction<O> mock = new CapturingAction<O>(new Noop<O>());
        final ActionIgnoreFirst<O, O> adapted = new ActionIgnoreFirst<O, O>(mock);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
