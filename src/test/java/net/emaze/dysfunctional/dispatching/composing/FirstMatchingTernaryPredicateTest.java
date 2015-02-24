package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingTernaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        final Iterable<TriPredicate<O, O, O>> pred = null;
        new FirstMatchingTernaryPredicate<O, O, O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final TriPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        final FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>(Iterations.iterable(always));
        Assert.assertTrue(pred.test(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final TriPredicate<O, O, O> never = new TernaryNever<O, O, O>();
        final FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>(Iterations.iterable(never));
        Assert.assertFalse(pred.test(O.IGNORED, O.IGNORED, O.IGNORED));
    }
}
