package net.emaze.dysfunctional.options;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MaybeFirstElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new MaybeFirstElement<O>().perform(null);
    }

    @Test
    public void callingWithEmptyIteratorYieldsNothing() {
        final Iterator<O> iterator = Iterations.iterator();
        final Maybe<O> got = new MaybeFirstElement<O>().perform(iterator);
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }

    @Test
    public void callingWithNonEmptyIteratorYieldsJustFirst() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final Maybe<O> got = new MaybeFirstElement<O>().perform(iterator);
        Assert.assertEquals(Maybe.just(O.ONE), got);
    }
}