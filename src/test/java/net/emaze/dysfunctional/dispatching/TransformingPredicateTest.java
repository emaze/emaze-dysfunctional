package net.emaze.dysfunctional.dispatching;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new TransformingPredicate<O, O>(null, UnaryOperator.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingPredicate<O, O>(new Always<O>(), null);
    }

    @Test
    public void canComposePredicateAndDelegate() {
        final Predicate<O> composed = new TransformingPredicate<O, O>(new Always<O>(), UnaryOperator.identity());
        final boolean got = composed.test(O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
