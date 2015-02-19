package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MaybeOneElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new MaybeOneElement<O>().apply(null);
    }

    @Test
    public void callingWithEmptyIteratorYieldsNothing() {
        final Iterator<O> iterator = Iterations.iterator();
        final Optional<O> got = new MaybeOneElement<O>().apply(iterator);
        Assert.assertEquals(Optional.<O>empty(), got);
    }

    @Test
    public void callingWithSingletonIteratorYieldsJustElement() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final Optional<O> got = new MaybeOneElement<O>().apply(iterator);
        Assert.assertEquals(Optional.of(O.ONE), got);
    }

    @Test(expected = IllegalStateException.class)
    public void callingWithIteratorWithMoreThanOneElementYieldsException() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        new MaybeOneElement<O>().apply(iterator);
    }
}