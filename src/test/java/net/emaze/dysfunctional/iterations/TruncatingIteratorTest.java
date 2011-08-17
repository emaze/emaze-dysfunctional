package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
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
}
