package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
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
        final Box<O> param1 = Box.empty();
        final BinaryAction<O, O> spy = Spies.spy1st(new BinaryNoop<O, O>(), param1);
        final ActionBinderFirst<O, O> adapted = new ActionBinderFirst<O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
