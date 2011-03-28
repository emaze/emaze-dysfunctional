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
public class PredicateBinderSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderSecondOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final TernaryCapturingPredicate<O, O, O> mock = new TernaryCapturingPredicate<O, O, O>(new TernaryAlways<O, O, O>());
        final PredicateBinderSecondOfThree<O, O, O> adapted = new PredicateBinderSecondOfThree<O, O, O>(mock, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(mock.second.getContent(), O.ONE);
    }
}
