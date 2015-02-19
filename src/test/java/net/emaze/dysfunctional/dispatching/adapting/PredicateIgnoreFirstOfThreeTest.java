package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreFirstOfThree<O, O, O>(null);
    }

    @Test
    public void firstParamIsCorrectlyForwarded() {
        final Box<O> param1 = Box.empty();
        final BiPredicate<O, O> mock = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final TernaryPredicate<O, O, O> adapted = new PredicateIgnoreFirstOfThree<O, O, O>(mock);
        adapted.accept(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParamIsCorrectlyForwarded() {
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> mock = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final TernaryPredicate<O, O, O> adapted = new PredicateIgnoreFirstOfThree<O, O, O>(mock);
        adapted.accept(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
