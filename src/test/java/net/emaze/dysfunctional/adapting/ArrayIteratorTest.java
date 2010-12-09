package net.emaze.dysfunctional.adapting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ArrayIteratorTest {

    @Test(expected = NoSuchElementException.class)
    public void consumingEmptyArrayIteratorYieldANoSuchElementException() {
        new ArrayIterator<Object>(new Object[]{}).next();
        Assert.assertTrue(true);
    }

    @Test
    public void emptyArrayIteratorHasNoNextElement() {
        final boolean hasNext = new ArrayIterator<Object>(new Object[]{}).hasNext();
        Assert.assertEquals(false, hasNext);
    }

    @Test
    public void consumingTheArrayYieldsEveryArrayElements() {
        final Integer[] values = new Integer[]{1, 2, 3, 4, 5};
        final Iterator<Integer> iter = new ArrayIterator<Integer>(values);
        final List<Integer> got = new ArrayList<Integer>();
        while (iter.hasNext()) {
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(values), got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingFromNullYieldsAnIllegalArgumentException() {
        new ArrayIterator<Integer>(null);
        Assert.assertTrue(true);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void removingFromArrayIteratorYieldsUnsupportedException(){
        new ArrayIterator<Integer>(new Integer[]{1}).remove();

    }
}
