package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RoundRobinIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullArrayYieldsException() {
        Iterator<Object>[] iterators = null;
        new RoundRobinIterator<Object>(iterators);
    }

    @Test
    public void hasNextWhenNonEmptyInnerIterators() {
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(Collections.<Integer>emptyList().iterator(), Arrays.asList(1).iterator());
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void hasNoNextWhenAllEmptyInnerIterators() {
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(Collections.<Integer>emptyList().iterator());
        Assert.assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(Collections.<Integer>emptyList().iterator());
        iter.next();
    }

    @Test
    public void canGetNextWhenAnIteratorIsEmpty() {
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(Collections.<Integer>emptyList().iterator(), bucket.iterator());
        Assert.assertEquals(Integer.valueOf(1), iter.next());
    }

    @Test
    public void canRemoveFromInnerIterators() {
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(Collections.<Integer>emptyList().iterator(), bucket.iterator());
        iter.next();
        iter.remove();
        Assert.assertTrue(bucket.isEmpty());
    }
}
