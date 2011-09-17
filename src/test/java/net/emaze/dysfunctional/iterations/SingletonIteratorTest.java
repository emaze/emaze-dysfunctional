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
public class SingletonIteratorTest {

    @Test
    public void unconsumedSingletonIteratorHasNext() {
        final Iterator<O> iterator = new SingletonIterator<O>(O.ONE);
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void consumedSingletonIteratorHasNoNext() {
        final Iterator<O> iterator = new SingletonIterator<O>(O.ONE);
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void tryingToConsumeConsumedSingletonIteratorYieldsException() {
        final Iterator<O> iterator = new SingletonIterator<O>(O.ONE);
        iterator.next();
        iterator.next();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void removingFromSingletonIteratorYieldsException() {
        final Iterator<O> iterator = new SingletonIterator<O>(O.ONE);
        iterator.next();
        iterator.remove();
    }
}
