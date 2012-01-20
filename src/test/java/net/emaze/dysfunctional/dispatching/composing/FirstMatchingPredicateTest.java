package net.emaze.dysfunctional.dispatching.composing;

import java.util.Iterator;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
        final Iterator<Predicate<O>> pred = null;
        new FirstMatchingPredicate<O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Iterations.iterator(always));
        Assert.assertTrue(pred.accept(O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final Predicate<O> never = new Never<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Iterations.iterator(never));
        Assert.assertFalse(pred.accept(O.IGNORED));
    }
}
