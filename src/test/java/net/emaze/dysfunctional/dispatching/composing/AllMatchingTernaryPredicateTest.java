package net.emaze.dysfunctional.dispatching.composing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingTernaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        List<TernaryPredicate<O, O, O>> empty = Collections.emptyList();
        Assert.assertTrue(new AllMatchingTernaryPredicate<O, O, O>(empty).accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final TernaryPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>(Arrays.asList(always));
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final TernaryPredicate<O, O, O> never = new TernaryNever<O, O, O>();
        AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>(Arrays.asList(never));
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<TernaryPredicate<O, O, O>> nullIterable = null;
        new AllMatchingTernaryPredicate<O, O, O>(nullIterable);
    }
}
