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
public class UnaryToBinaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new UnaryToBinaryPredicate<O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Predicate<Pair<O, O>> predicate = new Always<Pair<O, O>>();
        final UnaryToBinaryPredicate<O, O> adapted = new UnaryToBinaryPredicate<O, O>(predicate);
        Assert.assertTrue(adapted.accept(O.IGNORED, O.IGNORED));
    }
}
