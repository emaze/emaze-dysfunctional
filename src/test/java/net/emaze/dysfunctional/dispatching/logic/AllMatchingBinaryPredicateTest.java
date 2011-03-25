package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.AllMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingBinaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Assert.assertTrue(new AllMatchingBinaryPredicate<O, O>().test(null, null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>();
        test.add(new BinaryAlways<O, O>());
        Assert.assertTrue(test.test(O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>();
        test.add(new BinaryNever<O, O>());
        Assert.assertFalse(test.test(O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        boolean got = pred.remove(new BinaryAlways<O, O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        BinaryAlways<O, O> always = new BinaryAlways<O, O>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        AllMatchingBinaryPredicate<O, O> pred = new AllMatchingBinaryPredicate<O, O>();
        BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        pred.setFunctors(Arrays.asList(always, always));
        pred.test(O.IGNORED, O.IGNORED);
    }
}
