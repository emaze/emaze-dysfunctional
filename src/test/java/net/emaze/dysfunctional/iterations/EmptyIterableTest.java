package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class EmptyIterableTest {

    @Test
    public void emptyIterableYieldsAnEmptyIterator() {
        final Iterator<O> iterator = new EmptyIterable<O>().iterator();
        Assert.assertFalse(iterator.hasNext());
    }
}
