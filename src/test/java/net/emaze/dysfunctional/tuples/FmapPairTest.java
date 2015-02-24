package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapPairTest {

    private static final Function<O, O> ID = Function.identity();

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullFirstDelegateYieldsException() {
        new FmapPair<O, O, O, O>(null, ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSecondDelegateYieldsException() {
        new FmapPair<O, O, O, O>(ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullPairYieldsException() {
        new FmapPair<O, O, O, O>(ID, ID).apply(null);
    }

    @Test
    public void firstDelegateTransformsFirstType() {
        final Function<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Pair<O, O> got = new FmapPair<O, O, O, O>(delegate, ID).apply(Pair.of(O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.first());
    }

    @Test
    public void secondDelegateTransformsSecondType() {
        final Function<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Pair<O, O> got = new FmapPair<O, O, O, O>(ID, delegate).apply(Pair.of(O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.second());
    }
}