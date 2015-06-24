package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingBinaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        final Iterable<BiPredicate<O, O>> pred = null;
        new FirstMatchingBinaryPredicate<O, O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final BiPredicate<O, O> always = new BinaryAlways<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Iterations.iterable(always));
        Assert.assertTrue(pred.test(O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final BiPredicate<O, O> never = new BinaryNever<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Iterations.iterable(never));
        Assert.assertFalse(pred.test(O.IGNORED, O.IGNORED));
    }
}
