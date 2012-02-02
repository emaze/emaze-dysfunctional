package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingBinaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        Iterator<BinaryPredicate<O, O>> empty = Iterations.iterator();
        Assert.assertTrue(new AllMatchingBinaryPredicate<O, O>(empty).accept(null, null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        final AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Iterations.iterator(always));
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final BinaryPredicate<O, O> never = new BinaryNever<O, O>();
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Iterations.iterator(never));
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIteratorYieldsException() {
        final Iterator<BinaryPredicate<O, O>> nullIterator = null;        
        new AllMatchingBinaryPredicate<O, O>(nullIterator);
    }

}
