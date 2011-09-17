package net.emaze.dysfunctional.iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

public class TruncatingIteratorTest {

    @Test
    public void hasNoNextIfNestedHasNoNext() {
        final Iterator<O> ti = new TruncatingIterator<O>(Iterations.<O>iterator(), 1);
        Assert.assertFalse(ti.hasNext());
    }

    @Test
    public void hasNoNextWhenSizeIsZero() {
        final Iterator<O> ti = new TruncatingIterator<O>(Iterations.iterator(O.ONE), 0);
        Assert.assertFalse(ti.hasNext());
    }

    @Test
    public void canConsumeBetweenBounds() {
        final Iterator<O> ti = new TruncatingIterator<O>(Iterations.iterator(O.ONE), 1);
        Assert.assertEquals(O.ONE, ti.next());
    }

    @Test
    public void nestedIteratorIsTruncated() {
        final Iterator<O> ti = new TruncatingIterator<O>(Iterations.iterator(O.ONE), 1);
        ti.next();
        Assert.assertFalse(ti.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullIteratorYieldsException() {
        new TruncatingIterator<O>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNegativeSizeYieldsException() {
        new TruncatingIterator<O>(null, -1);
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingOverBoundsYieldsException() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final TruncatingIterator<O> ti = new TruncatingIterator<O>(iterator, 0);
        ti.next();
    }

    @Test
    public void removingFromIteratorRemovesFromInnerIterator() {
        final List<O> list = new ArrayList<O>(Arrays.asList(O.ONE));
        final TruncatingIterator<O> ti = new TruncatingIterator<O>(list.iterator(), 1);
        ti.next();
        ti.remove();
        Assert.assertTrue(list.isEmpty());
    }
}
