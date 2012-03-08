package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingTernaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        final Iterable<TernaryPredicate<O, O, O>> empty = Iterations.iterable();
        Assert.assertTrue(new AllMatchingTernaryPredicate<O, O, O>(empty).accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final TernaryPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        final AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>(Iterations.iterable(always));
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final TernaryPredicate<O, O, O> never = new TernaryNever<O, O, O>();
        final AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>(Iterations.iterable(never));
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<TernaryPredicate<O, O, O>> nullIterator = null;
        new AllMatchingTernaryPredicate<O, O, O>(nullIterator);
    }
}
