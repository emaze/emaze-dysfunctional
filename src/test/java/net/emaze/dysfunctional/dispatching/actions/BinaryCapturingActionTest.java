package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryCapturingActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new BinaryCapturingAction<O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final BinaryCapturingAction<O, O> capturer = new BinaryCapturingAction<O, O>(new BinaryNoop<O, O>());
        capturer.perform(O.ONE, O.ANOTHER);
        Assert.assertTrue(O.ONE.equals(capturer.first.getContent()) && O.ANOTHER.equals(capturer.second.getContent()));
    }
}
