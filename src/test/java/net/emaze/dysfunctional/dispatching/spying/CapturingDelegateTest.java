package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.options.Box;
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
        new CapturingDelegate<O, O>(null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullResultBoxYieldsException() {
        new CapturingDelegate<O, O>(new Identity<O>(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullParamBoxYieldsException() {
        new CapturingDelegate<O, O>(new Identity<O>(), Box.<O>empty(), null);
    }

    @Test
    public void parameterIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param = Box.empty();
        final CapturingDelegate<O, O> capturer = new CapturingDelegate<O, O>(new Identity<O>(), result, param);
        capturer.apply(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }

    @Test
    public void resultIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param = Box.empty();
        final CapturingDelegate<O, O> capturer = new CapturingDelegate<O, O>(new Identity<O>(), result, param);
        capturer.apply(O.ONE);
        Assert.assertEquals(O.ONE, result.getContent());
    }
}
