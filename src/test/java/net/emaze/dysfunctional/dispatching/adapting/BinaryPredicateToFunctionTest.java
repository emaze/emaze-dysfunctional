package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryPredicateToFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryPredicateToFunction<O, O>(null);
    }

    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        final BiFunction d = new BinaryPredicateToFunction<O, O>(new BinaryAlways<O, O>());
        d.apply(new Object(), new Object());
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BiPredicate<O, O> spy = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final BinaryPredicateToFunction<O, O> adapted = new BinaryPredicateToFunction<O, O>(spy);
        adapted.apply(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> spy = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final BinaryPredicateToFunction<O, O> adapted = new BinaryPredicateToFunction<O, O>(spy);
        adapted.apply(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final BinaryPredicateToFunction<O, O> adapted = new BinaryPredicateToFunction<O, O>(new BinaryAlways<O, O>());
        final boolean got = adapted.apply(O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
