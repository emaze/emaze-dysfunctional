package net.emaze.dysfunctional.logic;

import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        boolean got = pred.remove(new Always<O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        Always<O> always = new Always<O>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        Predicate<O> always = new Always<O>();
        pred.setFunctors(Arrays.asList(always, always));
        pred.test(O.IGNORED);
    }

    @Test
    public void usingAlwaysReturnsTrue(){
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        pred.add(new Always<O>());
        Assert.assertTrue(pred.test(O.IGNORED));
    }
    
    @Test
    public void usingNeverReturnsFalse(){
        FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>();
        pred.add(new Never<O>());
        Assert.assertFalse(pred.test(O.IGNORED));
    }
}
