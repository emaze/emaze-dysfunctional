package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreThird<O, O, O, O>(null);
    }

    @Test
    public void canIgnoreThirdParameter() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryDelegate<O, O, O> spy = Spies.spy(new FirstParam<O, O>(), result, param1, param2);
        final TernaryDelegate<O, O, O, O> adapted = new IgnoreThird<O, O, O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertTrue(param1.getContent().equals(O.ONE) && param2.getContent().equals(O.ANOTHER));
    }
}
