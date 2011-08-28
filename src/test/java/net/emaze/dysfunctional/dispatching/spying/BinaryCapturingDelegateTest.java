package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.spying.BinaryCapturingDelegate;
import net.emaze.dysfunctional.options.Box;
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
        new BinaryCapturingDelegate<O, O, O>(null, Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullResultBoxYieldsException() {
        new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullFirstParamBoxYieldsException() {
        new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), Box.<O>empty(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSecondParamBoxYieldsException() {
        new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), Box.<O>empty(), Box.<O>empty(), null);
    }

    @Test
    public void resultIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), result, param1, param2);
        capturer.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, result.getContent());
    }
    @Test
    public void firstParamIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), result, param1, param2);
        capturer.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
    @Test
    public void secondParamIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>(), result, param1, param2);
        capturer.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
