package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Iterator<Predicate<O>> empty = Iterations.iterator();
        Assert.assertTrue(new AllMatchingPredicate<O>(empty).accept(null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Iterations.iterator(always));
        Assert.assertTrue(test.accept(O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final Predicate<O> never = new Never<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Iterations.iterator(never));
        Assert.assertFalse(test.accept(O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIteratorYieldsException() {
        final Iterator<Predicate<O>> nullIterator = null;
        new AllMatchingPredicate<O>(nullIterator);
    }
}
