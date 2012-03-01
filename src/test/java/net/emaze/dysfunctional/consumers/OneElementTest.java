package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class OneElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void consuminNullIteratorYieldsException() {
        final Iterator<O> iterator = null;
        new OneElement<O>().perform(iterator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void consuminEmptyIteratorYieldsException() {
        final Iterator<O> iterator = Iterations.iterator();
        new OneElement<O>().perform(iterator);
    }

    @Test(expected = IllegalStateException.class)
    public void consuminIteratorWithMoreThanOneElementYieldsException() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        new OneElement<O>().perform(iterator);
    }

    @Test
    public void consuminIteratorWithOneElementYieldsTheElement() {
        final Iterator<String> iterator = Iterations.iterator("the only element");
        final String got = new OneElement<String>().perform(iterator);
        Assert.assertEquals("the only element", got);
    }
}