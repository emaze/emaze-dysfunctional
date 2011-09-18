package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RoundRobinIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullArrayYieldsException() {
        new RoundRobinIterator<Object>(null);
    }

    @Test
    public void hasNextWhenNonEmptyInnerIterators() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Integer> nonEmpty = Arrays.asList(1).iterator();
        Iterator<Iterator<Integer>> emptyAndNonEmpty = Arrays.asList(empty, nonEmpty).iterator();
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(emptyAndNonEmpty);
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void hasNoNextWhenAllEmptyInnerIterators() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Iterator<Integer>> justEmpty = Collections.singletonList(empty).iterator();
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(justEmpty);
        Assert.assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Iterator<Integer>> justEmpty = Collections.singletonList(empty).iterator();
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(justEmpty);
        iter.next();
    }

    @Test
    public void canGetNextWhenAnIteratorIsEmpty() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Integer> nonEmpty = Arrays.asList(1).iterator();
        Iterator<Iterator<Integer>> emptyAndNonEmpty = Arrays.asList(empty, nonEmpty).iterator();
        Iterator<Integer> iter = new RoundRobinIterator<Integer>(emptyAndNonEmpty);
        Assert.assertEquals(Integer.valueOf(1), iter.next());
    }
}
