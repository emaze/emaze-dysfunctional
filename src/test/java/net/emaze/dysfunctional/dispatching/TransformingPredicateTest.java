package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
        new TransformingPredicate<O, O>(null, new Identity<O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingPredicate<O, O>(new Always<O>(), null);
    }

    @Test
    public void canComposePredicateAndDelegate() {
        final Predicate<O> composed = new TransformingPredicate<O, O>(new Always<O>(), new Identity<O>());
        final boolean got = composed.accept(O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
