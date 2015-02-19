package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MaybeFirstElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new MaybeFirstElement<O>().apply(null);
    }

    @Test
    public void callingWithEmptyIteratorYieldsNothing() {
        final Iterator<O> iterator = Iterations.iterator();
        final Optional<O> got = new MaybeFirstElement<O>().apply(iterator);
        Assert.assertEquals(Optional.<O>empty(), got);
    }

    @Test
    public void callingWithNonEmptyIteratorYieldsJustFirst() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final Optional<O> got = new MaybeFirstElement<O>().apply(iterator);
        Assert.assertEquals(Optional.of(O.ONE), got);
    }
}