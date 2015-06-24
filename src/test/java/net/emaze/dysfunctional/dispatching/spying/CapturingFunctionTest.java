package net.emaze.dysfunctional.dispatching.spying;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullDelegateYieldsException() {
        new CapturingFunction<O, O>(null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullResultBoxYieldsException() {
        new CapturingFunction<O, O>(Function.identity(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullParamBoxYieldsException() {
        new CapturingFunction<O, O>(Function.identity(), Box.<O>empty(), null);
    }

    @Test
    public void parameterIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param = Box.empty();
        final CapturingFunction<O, O> capturer = new CapturingFunction<O, O>(Function.identity(), result, param);
        capturer.apply(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }

    @Test
    public void resultIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param = Box.empty();
        final CapturingFunction<O, O> capturer = new CapturingFunction<O, O>(Function.identity(), result, param);
        capturer.apply(O.ONE);
        Assert.assertEquals(O.ONE, result.getContent());
    }
}
