package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryPredicateToBinaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryPredicateToBinaryDelegate<O, O>(null);
    }

    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        final BinaryDelegate d = new BinaryPredicateToBinaryDelegate<O, O>(new BinaryAlways<O, O>());
        d.perform(new Object(), new Object());
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BinaryPredicate<O, O> spy = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BinaryPredicate<O, O> spy = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(spy);
        adapted.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(new BinaryAlways<O, O>());
        final boolean got = adapted.perform(O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
