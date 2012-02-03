package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingTernaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new TransformingTernaryPredicate<O, O, O, O>(null, new FirstParamOfThree<O, O, O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingTernaryPredicate<O, O, O, O>(new Always<O>(), null);
    }

    @Test
    public void canComposePredicateAndDelegate() {
        final TernaryPredicate<O, O, O> composed = new TransformingTernaryPredicate<O, O, O, O>(new Always<O>(), new FirstParamOfThree<O, O, O>());
        final boolean got = composed.accept(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
