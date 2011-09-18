package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class EmptyIteratorTest {

    @Test
    public void emptyIteratorHasNoElements() {
        final Iterator<O> iterator = new EmptyIterator<O>();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void callingNextYieldsNoSuchElementsException() {
        final Iterator<O> iterator = new EmptyIterator<O>();
        iterator.next();
    }
}
