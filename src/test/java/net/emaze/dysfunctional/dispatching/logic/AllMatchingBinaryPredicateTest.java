package net.emaze.dysfunctional.dispatching.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AllMatchingBinaryPredicateTest {

    @Test
    public void canEvaluateEmptyPredicateList() {
        List<BinaryPredicate<O, O>> empty = Collections.emptyList();
        Assert.assertTrue(new AllMatchingBinaryPredicate<O, O>(empty).accept(null, null));
    }

    @Test
    public void yieldsTrueWhenEveryPredicateReturnsTrue() {
        final BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Arrays.asList(always));
        Assert.assertTrue(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test
    public void yieldsFalseOnFirstPredicateReturningFalse() {
        final BinaryPredicate<O, O> never = new BinaryNever<O, O>();
        AllMatchingBinaryPredicate<O, O> test = new AllMatchingBinaryPredicate<O, O>(Arrays.asList(never));
        Assert.assertFalse(test.accept(O.IGNORED, O.IGNORED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWitNullIterableYieldsException() {
        final Iterable<BinaryPredicate<O, O>> nullIterable = null;        
        new AllMatchingBinaryPredicate<O, O>(nullIterable);
    }

}
