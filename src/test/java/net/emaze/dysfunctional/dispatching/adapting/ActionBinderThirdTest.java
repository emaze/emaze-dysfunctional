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
public class ActionBinderThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderThird<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindThirdParameter() {
        final Box<O> param3 = Box.empty();
        final TernaryAction<O, O, O> spy = Spies.spy3rd(new TernaryNoop<O, O, O>(), param3);        
        final ActionBinderThird<O, O, O> adapted = new ActionBinderThird<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(param3.getContent(), O.ONE);
    }
}
