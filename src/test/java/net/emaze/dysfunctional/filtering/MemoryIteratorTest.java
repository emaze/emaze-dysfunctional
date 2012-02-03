package net.emaze.dysfunctional.filtering;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MemoryIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMemoryIteratorWithNullIteratorYieldsException() {
        new MemoryIterator<Integer>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingMemoryIteratorWithNonPositiveSizeYieldsException() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2);
        new MemoryIterator<Integer>(iter, 0).remove();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratingOverTooSmallIteratorYieldsException() {
        final Iterator<Integer> iter = Iterations.iterator(1);
        new MemoryIterator<Integer>(iter, 2).next();
    }

    @Test
    public void nonEmptyIteratorHasNext() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2);
        final MemoryIterator<Integer> memoryIterator = new MemoryIterator<Integer>(iter, 2);
        Assert.assertTrue(memoryIterator.hasNext());
    }

    @Test
    public void callingHasNextDoesNotConsumeIterator() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2);
        final MemoryIterator<Integer> memoryIterator = new MemoryIterator<Integer>(iter, 2);
        memoryIterator.hasNext();
        Assert.assertTrue(memoryIterator.hasNext());
    }

    @Test
    public void memoryYieldsOnlyLastNElement() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2, 3);
        final MemoryIterator<Integer> memoryIterator = new MemoryIterator<Integer>(iter, 2);
        Assert.assertEquals(Arrays.asList(2, 3), Consumers.all(memoryIterator));
    }
}
