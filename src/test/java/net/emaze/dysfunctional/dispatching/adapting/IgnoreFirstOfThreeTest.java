package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;
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
public class IgnoreFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreFirstOfThree<O, O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BiFunction<O, O, O> spy = Spies.spy(new FirstParam<O, O>(), Box.<O>empty(), param1, param2);
        final IgnoreFirstOfThree<O, O, O, O> adapted = new IgnoreFirstOfThree<O, O, O, O>(spy);
        adapted.apply(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertTrue(param1.getContent().equals(O.ONE) && param2.getContent().equals(O.ANOTHER));
    }
}
