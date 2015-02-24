package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullDelegateYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(null, Box.<O>empty(), Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForResultYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), null, Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForFirstParamYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), Box.<O>empty(), null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForSecondParamYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), Box.<O>empty(), Box.<O>empty(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForThirdParamYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), Box.<O>empty(), Box.<O>empty(), Box.<O>empty(), null);
    }

    @Test
    public void resultIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriFunction<O, O, O, O> delelgate = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
        delelgate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, result.getContent());
    }

    @Test
    public void firstParameterIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriFunction<O, O, O, O> delelgate = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
        delelgate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriFunction<O, O, O, O> delelgate = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
        delelgate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void thirdParameterIsCaptured() {
        final Box<O> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriFunction<O, O, O, O> delelgate = new TernaryCapturingDelegate<O, O, O, O>(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
        delelgate.apply(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, param3.getContent());
    }
}
