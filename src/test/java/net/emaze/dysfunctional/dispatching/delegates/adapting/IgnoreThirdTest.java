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
public class IgnoreThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreThird<O, O, O, O>(null);
    }

    @Test
    public void canIgnoreThirdParameter() {
        final BinaryCapturingDelegate<O, O, O> mock = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>());
        final IgnoreThird<O, O, O, O> adapted = new IgnoreThird<O, O, O, O>(mock);
        adapted.perform(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
