package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.logic.Always;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingBinaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new TransformingBinaryPredicate<O, O, O>(null, new FirstParam<O, O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingBinaryPredicate<O, O, O>(new Always<O>(), null);
    }

    @Test
    public void canComposePredicateAndDelegate() {
        final BiPredicate<O, O> composed = new TransformingBinaryPredicate<O, O, O>(new Always<O>(), new FirstParam<O, O>());
        final boolean got = composed.test(O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
