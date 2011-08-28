package net.emaze.dysfunctional.dispatching.composing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        List<Predicate<O>> empty = Collections.emptyList();
        Assert.assertTrue(new AllMatchingPredicate<O>(empty).accept(null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Arrays.asList(always));
        Assert.assertTrue(test.accept(O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final Predicate<O> never = new Never<O>();
        AllMatchingPredicate<O> test = new AllMatchingPredicate<O>(Arrays.asList(never));
        Assert.assertFalse(test.accept(O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<Predicate<O>> nullIterable = null;
        new AllMatchingPredicate<O>(nullIterable);
    }
}
