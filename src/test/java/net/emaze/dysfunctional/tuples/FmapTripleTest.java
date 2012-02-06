package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapTripleTest {

    private static final Delegate<O, O> ID = new Identity<O>();

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullFirstDelegateYieldsException() {
        new FmapTriple<O, O, O, O, O, O>(null, ID, ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSecondDelegateYieldsException() {
        new FmapTriple<O, O, O, O, O, O>(ID, null, ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullThirdDelegateYieldsException() {
        new FmapTriple<O, O, O, O, O, O>(ID, ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullTripleYieldsException() {
        new FmapTriple<O, O, O, O, O, O>(ID, ID, ID).perform(null);
    }

    @Test
    public void firstDelegateTransformsFirstType() {
        final Delegate<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(delegate, ID, ID).perform(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.first());
    }

    @Test
    public void secondDelegateTransformsSecondType() {
        final Delegate<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(ID, delegate, ID).perform(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.second());
    }

    @Test
    public void thirdDelegateTransformsThirdType() {
        final Delegate<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(ID, ID, delegate).perform(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.third());
    }
}