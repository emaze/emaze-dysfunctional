package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionBinderFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderFirstOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param1 = Box.empty();
        final TernaryAction<O, O, O> spy = Spies.spy1st(new TernaryNoop<O, O, O>(), param1);
        final ActionBinderFirstOfThree<O, O, O> adapted = new ActionBinderFirstOfThree<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
