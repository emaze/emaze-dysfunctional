package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(null, Box.<Boolean>empty(), Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForResultYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), null, Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForFirstParamYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), Box.<Boolean>empty(), null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForSecondParamYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), Box.<Boolean>empty(), Box.<O>empty(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForThirdParamYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), Box.<Boolean>empty(), Box.<O>empty(), Box.<O>empty(), null);
    }

    @Test
    public void resultIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriPredicate<O, O, O> pred = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
        pred.test(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(true, result.getContent());
    }

    @Test
    public void firstParameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriPredicate<O, O, O> pred = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
        pred.test(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriPredicate<O, O, O> pred = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
        pred.test(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void thirdParameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriPredicate<O, O, O> pred = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
        pred.test(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, param3.getContent());
    }
}
