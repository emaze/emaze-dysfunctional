package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.CapturingPredicate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreSecond<O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final CapturingPredicate<O> mock = new CapturingPredicate<O>(new Always<O>());
        final PredicateIgnoreSecond<O, O> adapted = new PredicateIgnoreSecond<O, O>(mock);
        adapted.accept(O.ONE, O.IGNORED);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
