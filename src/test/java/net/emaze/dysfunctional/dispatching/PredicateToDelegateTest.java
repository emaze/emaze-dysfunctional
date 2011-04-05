package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.CapturingPredicate;
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

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final CapturingPredicate<O> adaptee = new CapturingPredicate<O>(new Always<O>());
        final PredicateToDelegate<O> adapted = new PredicateToDelegate<O>(adaptee);
        adapted.perform(O.ONE);
        Assert.assertEquals(O.ONE, adaptee.first.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final PredicateToDelegate<O> adapted = new PredicateToDelegate<O>(new Always<O>());
        boolean got = adapted.perform(O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
