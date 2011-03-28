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
public class PredicateIgnoreThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreThird<O, O, O>(null);
    }

    @Test
    public void canIgnoreThirdParameter() {
        final BinaryCapturingPredicate<O, O> mock = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>());
        final PredicateIgnoreThird<O, O, O> adapted = new PredicateIgnoreThird<O, O, O>(mock);
        adapted.accept(O.ONE, O.ANOTHER, O.IGNORED);
        Assert.assertTrue(mock.first.getContent().equals(O.ONE) && mock.second.getContent().equals(O.ANOTHER));
    }
}
