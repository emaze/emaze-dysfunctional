package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryCapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new BinaryCapturingPredicate<O, O>(null, Box.<Boolean>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForResultYieldsException() {
        new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForFirstParamYieldsException() {
        new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), Box.<Boolean>empty(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForSecondParamYieldsException() {
        new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), Box.<Boolean>empty(), Box.<O>empty(), null);
    }

    @Test
    public void resultIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> pred = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), result, param1, param2);
        pred.test(O.ONE, O.ANOTHER);
        Assert.assertEquals(true, result.getContent());
    }
    @Test
    public void firstParameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> pred = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), result, param1, param2);
        pred.test(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> pred = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), result, param1, param2);
        pred.test(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
