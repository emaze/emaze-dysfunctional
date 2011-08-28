package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreSecondOfThree<O, O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryDelegate<O, O, O> spy = Spies.spy(new FirstParam<O, O>(), result, param1, param2);
        final IgnoreSecondOfThree<O, O, O, O> adapted = new IgnoreSecondOfThree<O, O, O, O>(spy);
        adapted.perform(O.ONE, O.IGNORED, O.ANOTHER);
        Assert.assertTrue(param1.getContent().equals(O.ONE) && param2.getContent().equals(O.ANOTHER));
    }
}
