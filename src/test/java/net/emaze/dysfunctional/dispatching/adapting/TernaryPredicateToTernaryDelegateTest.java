package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
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

    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        TernaryDelegate d = new TernaryPredicateToTernaryDelegate<O, O, O>(new TernaryAlways<O, O, O>());
        d.perform(new Object(), new Object(), new Object());
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy1st(new TernaryAlways<O, O, O>(), param1);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy2nd(new TernaryAlways<O, O, O>(), param2);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final Box<O> param3 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy3rd(new TernaryAlways<O, O, O>(), param3);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, param3.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(new TernaryAlways<O, O, O>());
        boolean got = adapted.perform(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
