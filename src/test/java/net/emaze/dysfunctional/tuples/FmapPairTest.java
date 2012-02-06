package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapPairTest {

    private static final Delegate<O, O> ID = new Identity<O>();

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
        new FmapPair<O, O, O, O>(ID, ID).perform(null);
    }

    @Test
    public void firstDelegateTransformsFirstType() {
        final Delegate<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Pair<O, O> got = new FmapPair<O, O, O, O>(delegate, ID).perform(Pair.of(O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.first());
    }

    @Test
    public void secondDelegateTransformsSecondType() {
        final Delegate<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Pair<O, O> got = new FmapPair<O, O, O, O>(ID, delegate).perform(Pair.of(O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.second());
    }
}