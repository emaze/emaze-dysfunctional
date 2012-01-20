package net.emaze.dysfunctional.filtering;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class TakeUpToIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        new TakeUpToIterator<O>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNegativeHowManyYieldsException() {
        final Iterator<O> iter = Iterations.iterator();
        new TakeUpToIterator<O>(iter, -1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removingFromIteratorYieldsException() {
        final Iterator<O> inner = Iterations.iterator(O.ONE);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        iter.remove();
    }

    @Test
    public void hasNoNextWhenInnerIteratorIsEmpty() {
        final Iterator<O> inner = Iterations.iterator();
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void afterConsumingRequestedElementsIteratorHasNoNext() {
        final Iterator<O> inner = Iterations.iterator(O.ONE, O.ANOTHER);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        iter.next();
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void hasNextWhenRequestedElementsAreNotConsumedAndInnerIteratorHasNext() {
        final Iterator<O> inner = Iterations.iterator(O.ONE, O.ANOTHER);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        Assert.assertTrue(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingMoreThanRequestedElementsYieldsException() {
        final Iterator<O> inner = Iterations.iterator(O.ONE, O.ANOTHER);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        iter.next();
        iter.next();
    }

    @Test
    public void doesNotPrefetchElementsOnHasNext() {
        final Iterator<O> inner = Iterations.iterator(O.ONE);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        iter.hasNext();
        Assert.assertTrue(inner.hasNext());
    }

    @Test
    public void doesNotPrefetchElementsOnNext() {
        final Iterator<O> inner = Iterations.iterator(O.ONE, O.ANOTHER);
        final TakeUpToIterator<O> iter = new TakeUpToIterator<O>(inner, 1);
        iter.next();
        Assert.assertEquals(O.ANOTHER, inner.next());
    }
}
