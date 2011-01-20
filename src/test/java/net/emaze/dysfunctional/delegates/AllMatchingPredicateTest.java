
package net.emaze.dysfunctional.delegates;

import java.lang.String;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingPredicateTest {

    public AllMatchingPredicateTest() {
    }

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

}