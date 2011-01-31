package net.emaze.dysfunctional.logic;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        boolean got = pred.remove(new Always<String>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        Always<String> always = new Always<String>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.setFunctors(Arrays.<Predicate<String>>asList(new Always<String>(), new Always<String>()));
        pred.test("ignored_value");
    }

    @Test
    public void usingAlwaysReturnsTrue(){
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.add(new Always<String>());
        Assert.assertTrue(pred.test("unused"));
    }
    
    @Test
    public void usingNeverReturnsFalse(){
        FirstMatchingPredicate<String> pred = new FirstMatchingPredicate<String>();
        pred.add(new Never<String>());
        Assert.assertFalse(pred.test("unused"));
    }
}
