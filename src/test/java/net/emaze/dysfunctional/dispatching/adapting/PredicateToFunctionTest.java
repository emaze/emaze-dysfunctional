package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.logic.Always;
import java.util.function.Predicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateToFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new PredicateToFunction<O>(null);
    }
    
    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        Function d = new PredicateToFunction<O>(new Always<O>());
        d.apply(new Object());
    }
    
    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param = Box.empty();
        final Predicate<O> spy = Spies.spy1st(new Always<O>(), param);
        final PredicateToFunction<O> adapted = new PredicateToFunction<O>(spy);
        adapted.apply(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final PredicateToFunction<O> adapted = new PredicateToFunction<O>(new Always<O>());
        boolean got = adapted.apply(O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
