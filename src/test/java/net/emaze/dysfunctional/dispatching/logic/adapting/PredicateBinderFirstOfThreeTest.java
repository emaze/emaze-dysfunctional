package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.TernaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateBinderFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderFirstOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final TernaryCapturingPredicate<O, O, O> mock = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>());
        final PredicateBinderFirstOfThree<O, O, O> adapted = new PredicateBinderFirstOfThree<O, O, O>(mock, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(mock.first.getContent(), O.ONE);
    }
}
