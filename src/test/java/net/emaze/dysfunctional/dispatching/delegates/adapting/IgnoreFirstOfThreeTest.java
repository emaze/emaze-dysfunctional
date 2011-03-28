package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
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
        final BinaryCapturingDelegate<O, O, O> mock = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>());
        final IgnoreFirstOfThree<O, O, O, O> adapted = new IgnoreFirstOfThree<O, O, O, O>(mock);
        adapted.perform(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
