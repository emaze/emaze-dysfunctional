package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.options.Box;
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
        new CapturingAction<O>(null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void capturingWithNullBoxYieldsException() {
        new CapturingAction<O>(new Noop<O>(), null);
    }

    @Test
    public void parametersAreCaptured() {
        final Box<O> param = Box.empty();
        final CapturingAction<O> capturer = new CapturingAction<O>(new Noop<O>(), param);
        capturer.perform(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
