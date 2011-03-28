package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new TernaryCapturingPredicate<O, O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final TernaryCapturingPredicate<O, O, O> pred = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>());
        pred.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertTrue(
                O.ONE.equals(pred.first.getContent())
                && O.ANOTHER.equals(pred.second.getContent())
                && O.YET_ANOTHER.equals(pred.third.getContent())
        );
    }
}
