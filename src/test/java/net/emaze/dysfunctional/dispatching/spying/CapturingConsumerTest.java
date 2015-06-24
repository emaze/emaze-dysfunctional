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
public class CapturingConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new CapturingConsumer<O>(null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void capturingWithNullBoxYieldsException() {
        new CapturingConsumer<O>(new Noop<O>(), null);
    }

    @Test
    public void parametersAreCaptured() {
        final Box<O> param = Box.empty();
        final CapturingConsumer<O> capturer = new CapturingConsumer<O>(new Noop<O>(), param);
        capturer.accept(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
