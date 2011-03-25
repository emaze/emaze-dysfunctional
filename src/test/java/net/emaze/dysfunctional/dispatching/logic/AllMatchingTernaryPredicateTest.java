package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.AllMatchingTernaryPredicate;
import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingTernaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Assert.assertTrue(new AllMatchingTernaryPredicate<O, O, O>().accept(null, null, null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>();
        test.add(new TernaryAlways<O, O, O>());
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        AllMatchingTernaryPredicate<O, O, O> test = new AllMatchingTernaryPredicate<O, O, O>();
        test.add(new TernaryNever<O, O, O>());
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        boolean got = pred.remove(new TernaryAlways<O, O, O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        TernaryAlways<O, O, O> always = new TernaryAlways<O, O, O>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        AllMatchingTernaryPredicate<O, O, O> pred = new AllMatchingTernaryPredicate<O, O, O>();
        TernaryPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        pred.setFunctors(Arrays.asList(always, always));
        pred.accept(O.IGNORED, O.IGNORED, O.IGNORED);
    }
}
