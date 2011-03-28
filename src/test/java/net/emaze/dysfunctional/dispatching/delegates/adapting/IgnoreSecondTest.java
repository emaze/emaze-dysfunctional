package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.CapturingDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreSecond<O, O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final CapturingDelegate<O, O> mock = new CapturingDelegate<O, O>(new Identity<O>());
        final IgnoreSecond<O, O, O> adapted = new IgnoreSecond<O, O, O>(mock);
        adapted.perform(O.ONE, O.IGNORED);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
