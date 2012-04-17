package net.emaze.dysfunctional.multiplexing;

import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import org.junit.Test;

public class CyclicIteratorTest {

    @Test
    public void canIterateCyclicly() {
        final CyclicIterator<String> instance = new CyclicIterator<String>(Iterations.iterator("element"));
        Assert.assertEquals(instance.next(), instance.next());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void iteratorCannotBeNull() {
        new CyclicIterator<Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void iteratorCannotBeEmpty() {
        new CyclicIterator<Object>(Iterations.iterator());
    }
}