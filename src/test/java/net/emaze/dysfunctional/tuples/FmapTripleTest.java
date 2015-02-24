package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapTripleTest {

    private static final Function<O, O> ID = Function.identity();

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
        new FmapTriple<O, O, O, O, O, O>(ID, ID, ID).apply(null);
    }

    @Test
    public void firstDelegateTransformsFirstType() {
        final Function<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(delegate, ID, ID).apply(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.first());
    }

    @Test
    public void secondDelegateTransformsSecondType() {
        final Function<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(ID, delegate, ID).apply(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.second());
    }

    @Test
    public void thirdDelegateTransformsThirdType() {
        final Function<O, O> delegate = new ConstantDelegate<O, O>(O.ANOTHER);
        final Triple<O, O, O> got = new FmapTriple<O, O, O, O, O, O>(ID, ID, delegate).apply(Triple.of(O.ONE, O.ONE, O.ONE));
        Assert.assertEquals(O.ANOTHER, got.third());
    }
}