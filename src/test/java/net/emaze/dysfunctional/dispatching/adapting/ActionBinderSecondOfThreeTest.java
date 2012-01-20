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
public class ActionBinderSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ActionBinderSecondOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final Box<O> param2 = Box.empty();
        final TernaryAction<O, O, O> spy = Spies.spy2nd(new TernaryNoop<O, O, O>(), param2);
        final ActionBinderSecondOfThree<O, O, O> adapted = new ActionBinderSecondOfThree<O, O, O>(spy, O.ONE);
        adapted.perform(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(param2.getContent(), O.ONE);
    }
}
