package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.logic.Predicate;
import net.emaze.dysfunctional.logic.TernaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryToUnaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new TernaryToUnaryPredicate<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Predicate<Triple<O, O, O>> predicate = new TernaryToUnaryPredicate<O, O, O>(new TernaryAlways<O, O, O>());
        Assert.assertTrue(predicate.test(Triple.of(O.IGNORED, O.IGNORED, O.IGNORED)));
    }
}
