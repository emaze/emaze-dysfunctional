package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreThird<O, O, O>(null);
    }

    @Test
    public void firstParameterIsCorrectlyForwarded() {
        final Box<O> param1 = Box.empty();
        final BinaryPredicate<O, O> spy = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final TernaryPredicate<O, O, O> adapted = new PredicateIgnoreThird<O, O, O>(spy);
        adapted.accept(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCorrectlyForwarded() {
        final Box<O> param2 = Box.empty();
        final BinaryPredicate<O, O> spy = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final TernaryPredicate<O, O, O> adapted = new PredicateIgnoreThird<O, O, O>(spy);
        adapted.accept(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
