package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryCapturingPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryPredicateToTernaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new TernaryPredicateToTernaryDelegate<O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final TernaryCapturingPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>());
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, adaptee.first.getContent());
    }
    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final TernaryCapturingPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>());
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, adaptee.second.getContent());
    }
    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final TernaryCapturingPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>());
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, adaptee.third.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O,O>(new TernaryAlways<O, O, O>());
        boolean got = adapted.perform(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
