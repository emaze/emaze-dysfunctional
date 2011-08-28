package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateToDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new PredicateToDelegate<O>(null);
    }
    
    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        Delegate d = new PredicateToDelegate<O>(new Always<O>());
        d.perform(new Object());
    }
    
    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param = Box.empty();
        final Predicate<O> spy = Spies.spy1st(new Always<O>(), param);
        final PredicateToDelegate<O> adapted = new PredicateToDelegate<O>(spy);
        adapted.perform(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final PredicateToDelegate<O> adapted = new PredicateToDelegate<O>(new Always<O>());
        boolean got = adapted.perform(O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
