package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.Iterations;
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
        final Iterator<TernaryPredicate<O, O, O>> pred = null;
        new FirstMatchingTernaryPredicate<O, O, O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final TernaryPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        final FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>(Iterations.iterator(always));
        Assert.assertTrue(pred.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final TernaryPredicate<O, O, O> never = new TernaryNever<O, O, O>();
        final FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>(Iterations.iterator(never));
        Assert.assertFalse(pred.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }
}
