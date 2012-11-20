package net.emaze.dysfunctional.multiplexing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Iterations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RoundrobinIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullArrayYieldsException() {
        new RoundrobinIterator<Object>(null);
    }

    @Test
    public void consumingIteratorYieldsElementsInOrder() {
        Iterator<Integer> former = Iterations.iterator(1, 3);
        Iterator<Integer> latter = Iterations.iterator(2, 4);
        Iterator<Integer> iter = new RoundrobinIterator<Integer>(Iterations.iterator(former, latter));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), Consumers.all(iter));
    }

    @Test
    public void hasNextWhenNonEmptyInnerIterators() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Integer> nonEmpty = Arrays.asList(1).iterator();
        Iterator<Iterator<Integer>> emptyAndNonEmpty = Arrays.asList(empty, nonEmpty).iterator();
        Iterator<Integer> iter = new RoundrobinIterator<Integer>(emptyAndNonEmpty);
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void hasNoNextWhenAllEmptyInnerIterators() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Iterator<Integer>> justEmpty = Collections.singletonList(empty).iterator();
        Iterator<Integer> iter = new RoundrobinIterator<Integer>(justEmpty);
        Assert.assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyIteratorYieldsException() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Iterator<Integer>> justEmpty = Collections.singletonList(empty).iterator();
        Iterator<Integer> iter = new RoundrobinIterator<Integer>(justEmpty);
        iter.next();
    }

    @Test
    public void canGetNextWhenAnIteratorIsEmpty() {
        Iterator<Integer> empty = Collections.<Integer>emptyList().iterator();
        Iterator<Integer> nonEmpty = Arrays.asList(1).iterator();
        Iterator<Iterator<Integer>> emptyAndNonEmpty = Arrays.asList(empty, nonEmpty).iterator();
        Iterator<Integer> iter = new RoundrobinIterator<Integer>(emptyAndNonEmpty);
        Assert.assertEquals(Integer.valueOf(1), iter.next());
    }
}
