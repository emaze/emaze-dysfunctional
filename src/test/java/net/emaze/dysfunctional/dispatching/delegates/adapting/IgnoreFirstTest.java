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
public class IgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreFirst<O, O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final CapturingDelegate<O, O> mock = new CapturingDelegate<O, O>(new Identity<O>());
        final IgnoreFirst<O, O, O> adapted = new IgnoreFirst<O, O, O>(mock);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
