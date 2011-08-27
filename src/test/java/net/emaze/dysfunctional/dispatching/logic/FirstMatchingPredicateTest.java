package net.emaze.dysfunctional.dispatching.logic;

import java.util.Arrays;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstMatchingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIterableYieldsException() {
        final Iterable<Predicate<O>> pred = null;
        new FirstMatchingPredicate<O>(pred);
    }

    @Test
    public void usingAlwaysReturnsTrue() {
        final Predicate<O> always = new Always<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Arrays.asList(always));
        Assert.assertTrue(pred.accept(O.IGNORED));
    }

    @Test
    public void usingNeverReturnsFalse() {
        final Predicate<O> never = new Never<O>();
        final FirstMatchingPredicate<O> pred = new FirstMatchingPredicate<O>(Arrays.asList(never));
        Assert.assertFalse(pred.accept(O.IGNORED));
    }
}
