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
public class PredicateBinderFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderFirst<O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final BinaryCapturingPredicate<O, O> mock = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>());
        final PredicateBinderFirst<O, O> adapted = new PredicateBinderFirst<O, O>(mock, O.ONE);
        adapted.accept(O.ANOTHER);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
