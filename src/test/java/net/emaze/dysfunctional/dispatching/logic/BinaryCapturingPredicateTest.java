package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryCapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new BinaryCapturingPredicate<O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final BinaryCapturingPredicate<O, O> pred = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>());
        pred.accept(O.ONE, O.ANOTHER);
        Assert.assertTrue(O.ONE.equals(pred.first.getContent()) && O.ANOTHER.equals(pred.second.getContent()));
    }
}
