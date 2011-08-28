package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
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
        final Box<O> param2 = Box.empty();
        final BinaryAction<O, O> spy = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
        final ActionBinderSecond<O, O> adapted = new ActionBinderSecond<O, O>(spy, O.ONE);
        adapted.perform(O.ANOTHER);
        Assert.assertEquals(param2.getContent(), O.ONE);
    }
}
