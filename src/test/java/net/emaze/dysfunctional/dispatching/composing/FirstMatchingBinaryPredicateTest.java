package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
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
        final Iterator<BinaryPredicate<O, O>> pred = null;
        new FirstMatchingBinaryPredicate<O, O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final BinaryPredicate<O, O> always = new BinaryAlways<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Iterations.iterator(always));
        Assert.assertTrue(pred.accept(O.IGNORED, O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final BinaryPredicate<O, O> never = new BinaryNever<O, O>();
        final FirstMatchingBinaryPredicate<O, O> pred = new FirstMatchingBinaryPredicate<O, O>(Iterations.iterator(never));
        Assert.assertFalse(pred.accept(O.IGNORED, O.IGNORED));
    }
}
