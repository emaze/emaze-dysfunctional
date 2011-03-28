package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryCapturingDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullDelegateYieldsException() {
        new BinaryCapturingDelegate<O, O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>());
        capturer.perform(O.ONE, O.ANOTHER);
        Assert.assertTrue(O.ONE.equals(capturer.first.getContent()) && O.ANOTHER.equals(capturer.second.getContent()));
    }
}
