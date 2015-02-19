package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreSecondOfThree<O, O, O>(null);
    }

    @Test
    public void firstParameterIsCorrectlyForwarded() {
        final Box<O> param1 = Box.empty();
        final BiPredicate<O, O> spy = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final PredicateIgnoreSecondOfThree<O, O, O> adapted = new PredicateIgnoreSecondOfThree<O, O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCorrectlyForwarded() {
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> spy = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final PredicateIgnoreSecondOfThree<O, O, O> adapted = new PredicateIgnoreSecondOfThree<O, O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
