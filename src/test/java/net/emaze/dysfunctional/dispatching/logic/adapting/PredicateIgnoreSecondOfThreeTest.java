package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreSecondOfThree<O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final BinaryCapturingPredicate<O, O> mock = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>());
        final PredicateIgnoreSecondOfThree<O, O, O> adapted = new PredicateIgnoreSecondOfThree<O, O, O>(mock);
        adapted.accept(O.ONE, O.IGNORED, O.ANOTHER);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
