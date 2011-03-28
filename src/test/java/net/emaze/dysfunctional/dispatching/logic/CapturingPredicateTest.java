package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new CapturingPredicate<O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final CapturingPredicate<O> capturingPredicate = new CapturingPredicate<O>(new Always<O>());
        capturingPredicate.accept(O.ONE);
        Assert.assertEquals(O.ONE, capturingPredicate.first.getContent());
    }
}
