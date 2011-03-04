package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.logic.Predicate;
import net.emaze.dysfunctional.logic.BinaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryToUnaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new BinaryToUnaryPredicate<O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Predicate<Pair<O, O>> predicate = new BinaryToUnaryPredicate<O, O>(new BinaryAlways<O, O>());
        Assert.assertTrue(predicate.test(Pair.of(O.IGNORED, O.IGNORED)));
    }
}
