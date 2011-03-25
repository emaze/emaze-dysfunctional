package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.FirstMatchingTernaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingTernaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        boolean got = pred.remove(new TernaryAlways<O, O, O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        TernaryAlways<O, O, O> always = new TernaryAlways<O, O, O>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        TernaryPredicate<O, O, O> always = new TernaryAlways<O, O, O>();
        pred.setFunctors(Arrays.asList(always, always));
        pred.accept(O.IGNORED, O.IGNORED, O.IGNORED);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        pred.add(new TernaryAlways<O, O, O>());
        Assert.assertTrue(pred.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        FirstMatchingTernaryPredicate<O, O, O> pred = new FirstMatchingTernaryPredicate<O, O, O>();
        pred.add(new TernaryNever<O, O, O>());
        Assert.assertFalse(pred.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }
}
