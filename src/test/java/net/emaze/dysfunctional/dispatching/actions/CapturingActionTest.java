package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new CapturingAction<O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final CapturingAction<O> capturer = new CapturingAction<O>(new Noop<O>());
        capturer.perform(O.ONE);
        Assert.assertEquals(O.ONE, capturer.first.getContent());
    }
}
