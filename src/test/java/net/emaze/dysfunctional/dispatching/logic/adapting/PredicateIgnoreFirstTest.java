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
public class PredicateIgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreFirst<O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final CapturingPredicate<O> mock = new CapturingPredicate<O>(new Always<O>());
        final PredicateIgnoreFirst<O, O> adapted = new PredicateIgnoreFirst<O, O>(mock);
        adapted.accept(O.IGNORED, O.ONE);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
