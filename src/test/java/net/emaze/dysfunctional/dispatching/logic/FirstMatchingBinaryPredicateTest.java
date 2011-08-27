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
    public void creatingWithNullIterableYieldsException() {
        final Iterable<BinaryPredicate<O, O>> pred = null;
        new FirstMatchingBinaryPredicate<O, O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Arrays.asList(always));
        Assert.assertTrue(pred.accept(O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final BinaryPredicate<O, O> never = new BinaryNever<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Arrays.asList(never));
        Assert.assertFalse(pred.accept(O.IGNORED, O.IGNORED));
    }
}
