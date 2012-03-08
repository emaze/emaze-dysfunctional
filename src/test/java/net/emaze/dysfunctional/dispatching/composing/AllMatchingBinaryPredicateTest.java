package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingBinaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Iterable<BinaryPredicate<O, O>> empty = Iterations.iterable();
        Assert.assertTrue(new AllMatchingBinaryPredicate<O, O>(empty).accept(null, null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        final AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Iterations.iterable(always));
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final BinaryPredicate<O, O> never = new BinaryNever<O, O>();
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Iterations.iterable(never));
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<BinaryPredicate<O, O>> nullIterable = null;        
        new AllMatchingBinaryPredicate<O, O>(nullIterable);
    }

}
