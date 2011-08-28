package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionIgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ActionIgnoreFirst<O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param = Box.empty();
        final Action<O> spy = Spies.spy(new Noop<O>(), param);
        final ActionIgnoreFirst<O, O> adapted = new ActionIgnoreFirst<O, O>(spy);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
