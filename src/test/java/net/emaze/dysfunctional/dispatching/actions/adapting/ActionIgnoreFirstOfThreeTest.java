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
public class ActionIgnoreFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ActionIgnoreFirstOfThree<O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryAction<O, O> spy = Spies.spy(new BinaryNoop<O, O>(), param1, param2);        
        final ActionIgnoreFirstOfThree<O, O, O> adapted = new ActionIgnoreFirstOfThree<O, O, O>(spy);
        adapted.perform(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertTrue(param1.getContent().equals(O.ONE) && param2.getContent().equals(O.ANOTHER));
    }
}
