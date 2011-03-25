package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class UnaryToTernaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new UnaryToTernaryPredicate<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Predicate<Triple<O, O, O>> predicate = new Always<Triple<O, O, O>>();
        final UnaryToTernaryPredicate<O, O, O> adapted = new UnaryToTernaryPredicate<O, O, O>(predicate);
        Assert.assertTrue(adapted.accept(O.IGNORED, O.IGNORED, O.IGNORED));
    }
}
