package net.emaze.dysfunctional.logic;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Assert.assertTrue(new AllMatchingPredicate<String>().test(null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        AllMatchingPredicate<String> test = new AllMatchingPredicate<String>();
        test.add(new Always<String>());
        Assert.assertTrue(test.test("aaa"));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        AllMatchingPredicate<String> test = new AllMatchingPredicate<String>();
        test.add(new Never<String>());
        Assert.assertFalse(test.test("aaa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        boolean got = pred.remove(new Always<String>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        Always<String> always = new Always<String>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        AllMatchingPredicate<String> pred = new AllMatchingPredicate<String>();
        pred.setFunctors(Arrays.<Predicate<String>>asList(new Always<String>(), new Always<String>()));
        pred.test("ignored_value");
    }
}
