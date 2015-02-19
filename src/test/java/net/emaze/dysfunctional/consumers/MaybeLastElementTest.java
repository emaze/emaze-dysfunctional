package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class MaybeLastElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new MaybeLastElement<O>().apply(null);
    }

    @Test
    public void callingWithEmptyIteratorYieldsNothing() {
        final Iterator<O> iterator = Iterations.iterator();
        final Optional<O> got = new MaybeLastElement<O>().apply(iterator);
        Assert.assertEquals(Optional.<O>empty(), got);
    }
    @Test
    public void callingWithNonEmptyIteratorYieldsLastElement() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final Optional<O> got = new MaybeLastElement<O>().apply(iterator);
        Assert.assertEquals(Optional.of(O.ANOTHER), got);
    }
}