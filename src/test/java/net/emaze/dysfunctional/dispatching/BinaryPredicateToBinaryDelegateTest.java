package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryCapturingPredicate;
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

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final BinaryCapturingPredicate<O, O> adaptee = new BinaryCapturingPredicate<O,O >(new BinaryAlways<O, O>());
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ONE, adaptee.first.getContent());
    }
    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final BinaryCapturingPredicate<O, O> adaptee = new BinaryCapturingPredicate<O,O >(new BinaryAlways<O, O>());
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(adaptee);
        adapted.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.ANOTHER, adaptee.second.getContent());
    }

    @Test
    public void adapterCorrectlyReturnsResultToAdapted() {
        final BinaryPredicateToBinaryDelegate<O, O> adapted = new BinaryPredicateToBinaryDelegate<O, O>(new BinaryAlways<O, O>());
        boolean got = adapted.perform(O.IGNORED, O.IGNORED);
        Assert.assertEquals(true, got);
    }
}
