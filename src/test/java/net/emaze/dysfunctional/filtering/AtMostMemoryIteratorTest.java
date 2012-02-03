package net.emaze.dysfunctional.filtering;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Reductions;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class AtMostMemoryIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingAtMostMemoryIteratorWithNullIteratorYieldsException() {
        new AtMostMemoryIterator<Integer>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAtMostMemoryIteratorWithNonPositiveSizeYieldsException() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2);
        new AtMostMemoryIterator<Integer>(iter, 0).remove();
    }

    @Test
    public void iteratingOverTooSmallIteratorYieldsSmallerThanRequestedSize() {
        final Iterator<Integer> iter = Iterations.iterator(1);
        final Iterator<Integer> atMost = new AtMostMemoryIterator<Integer>(iter, 2);
        Assert.assertEquals(1, Reductions.count(atMost));
    }

    @Test
    public void memoryYieldsAtMostOnlyLastNElement() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2, 3);
        final Iterator<Integer> memoryIterator = new AtMostMemoryIterator<Integer>(iter, 2);
        Assert.assertEquals(Arrays.asList(2, 3), Consumers.all(memoryIterator));
    }

    @Test
    public void canUseNextWithoutCallingHasNext() {
        final Iterator<Integer> iter = Iterations.iterator(1, 2, 3);
        final Iterator<Integer> memoryIterator = new AtMostMemoryIterator<Integer>(iter, 2);
        memoryIterator.next();
        memoryIterator.next();
    }
}
