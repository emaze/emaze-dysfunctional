package net.emaze.dysfunctional.filtering;

import java.util.Arrays;
import java.util.Iterator;
import net.emaze.dysfunctional.reductions.Reductions;
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
        final Iterator<Integer> iter = Arrays.asList(1, 2).iterator();
        new AtMostMemoryIterator<Integer>(iter, 0).remove();
    }

    @Test
    public void iteratingOverTooSmallIteratorYieldsSmallerThanRequestedSize() {
        final Iterator<Integer> iter = Arrays.asList(1).iterator();
        final Iterator<Integer> atMost = new AtMostMemoryIterator<Integer>(iter, 2);
        Assert.assertEquals(1, Reductions.count(atMost));
    }
}
