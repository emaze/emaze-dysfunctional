package net.emaze.dysfunctional.dispatching.composing;

import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import java.util.function.Predicate;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        final Iterable<Predicate<O>> pred = null;
        new FirstMatchingPredicate<O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Iterations.iterable(always));
        Assert.assertTrue(pred.test(O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final Predicate<O> never = new Never<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Iterations.iterable(never));
        Assert.assertFalse(pred.test(O.IGNORED));
    }
}
