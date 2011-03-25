package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.FirstMatchingBinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingBinaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void addingNullFunctorToPredicateYieldsException() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        pred.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNullFunctorToPredicateYieldsException() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        pred.remove(null);
    }

    @Test
    public void removingNonPresentPredicateYieldsFalse() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        boolean got = pred.remove(new BinaryAlways<O, O>());
        Assert.assertFalse(got);
    }

    @Test
    public void removingPresentPredicateYieldsTrue() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        BinaryAlways<O, O> always = new BinaryAlways<O, O>();
        pred.add(always);
        boolean got = pred.remove(always);
        Assert.assertTrue(got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void settingNullFunctorsCollectionYieldsException() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        pred.setFunctors(null);
    }

    @Test
    public void canSetFunctors() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        pred.setFunctors(Arrays.asList(always, always));
        pred.test(O.IGNORED, O.IGNORED);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        pred.add(new BinaryAlways<O, O>());
        Assert.assertTrue(pred.test(O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>();
        pred.add(new BinaryNever<O, O>());
        Assert.assertFalse(pred.test(O.IGNORED, O.IGNORED));
    }
}
