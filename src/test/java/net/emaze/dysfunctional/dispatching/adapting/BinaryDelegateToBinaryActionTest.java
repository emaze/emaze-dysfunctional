package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryDelegateToBinaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryDelegateToBinaryAction<O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BinaryDelegate<O, O, O> spy = Spies.spy1st(new FirstParam<O, O>(), param1);
        final BinaryAction<O, O> adapted = new BinaryDelegateToBinaryAction<O, O, O>(spy);
        adapted.perform(O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BinaryDelegate<O, O, O> spy = Spies.spy2nd(new FirstParam<O, O>(), param2);
        final BinaryAction<O, O> adapted = new BinaryDelegateToBinaryAction<O, O, O>(spy);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
