package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.NoSuchElementException;
import junit.framework.Assert;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Filtering;
import net.emaze.dysfunctional.Iterations;
import org.junit.Test;

/**
 *
 * @author mcodella, asturman
 */
public class CyclicIteratorTest {

    @Test
    public void canIterateCyclicly() {
        final CyclicIterator<String> instance = new CyclicIterator<String>(Iterations.iterator("element"));
        Assert.assertEquals(instance.next(), instance.next());
    }

    @Test
    public void cyclesInOrder() {
        final CyclicIterator<Integer> instance = new CyclicIterator<Integer>(Iterations.iterator(1, 2));
        Assert.assertEquals(Arrays.asList(1, 2, 1, 2), Consumers.all(Filtering.take(4, instance)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void iteratorCannotBeNull() {
        new CyclicIterator<Object>(null);
    }

    @Test
    public void emptyIteratorHasNoNext() {
        CyclicIterator<Object> cyclicIterator = new CyclicIterator<Object>(Iterations.iterator());
        Assert.assertFalse(cyclicIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextOnEmptyIteratorYieldsException() {
        CyclicIterator<Object> cyclicIterator = new CyclicIterator<Object>(Iterations.iterator());
        cyclicIterator.next();
    }
}