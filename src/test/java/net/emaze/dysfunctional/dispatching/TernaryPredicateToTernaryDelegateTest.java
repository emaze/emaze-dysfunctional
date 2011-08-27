package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
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
        final Box<O> firstParam = Box.empty();
        final Box<O> secondParam = Box.empty();
        final Box<O> thirdParam = Box.empty();
        final TernaryPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>(), firstParam, secondParam, thirdParam);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, firstParam.getContent());
    }
    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> firstParam = Box.empty();
        final Box<O> secondParam = Box.empty();
        final Box<O> thirdParam = Box.empty();
        final TernaryPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>(), firstParam, secondParam, thirdParam);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, secondParam.getContent());
    }
    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final Box<O> firstParam = Box.empty();
        final Box<O> secondParam = Box.empty();
        final Box<O> thirdParam = Box.empty();
        final TernaryPredicate<O, O, O> adaptee = new TernaryCapturingPredicate<O,O,O>(new TernaryAlways<O, O, O>(), firstParam, secondParam, thirdParam);
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, thirdParam.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final TernaryPredicateToTernaryDelegate<O, O, O> adapted = new TernaryPredicateToTernaryDelegate<O, O,O>(new TernaryAlways<O, O, O>());
        boolean got = adapted.perform(O.IGNORED, O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
