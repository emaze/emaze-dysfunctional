package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullDelegateYieldsException() {
        new CapturingDelegate<O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final CapturingDelegate<O, O> capturer = new CapturingDelegate<O, O>(new Identity<O>());
        capturer.perform(O.ONE);
        Assert.assertEquals(O.ONE, capturer.first.getContent());
    }
}
