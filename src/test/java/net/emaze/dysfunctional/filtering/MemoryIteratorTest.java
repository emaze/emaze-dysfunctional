package net.emaze.dysfunctional.filtering;

import java.util.Arrays;
import java.util.Iterator;
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
        final Iterator<Integer> iter = Arrays.asList(1, 2).iterator();
        new MemoryIterator<Integer>(iter, 0).remove();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratingOverTooSmallIteratorYieldsException() {
        final Iterator<Integer> iter = Arrays.asList(1).iterator();
        new MemoryIterator<Integer>(iter, 2).next();
    }
}
