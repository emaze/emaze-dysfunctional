package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Iterable<Predicate<O>> empty = Iterations.iterable();
        Assert.assertTrue(new AllMatchingPredicate<O>(empty).accept(null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Iterations.iterable(always));
        Assert.assertTrue(test.accept(O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final Predicate<O> never = new Never<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Iterations.iterable(never));
        Assert.assertFalse(test.accept(O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<Predicate<O>> nullIterable = null;
        new AllMatchingPredicate<O>(nullIterable);
    }
}
