package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ChainIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullArrayYieldsException() {
        Iterator<Object>[] iterators = null;
        new ChainIterator<Object>(iterators);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullListYieldsException() {
        Iterator<Iterator<Object>> iterators = null;
        new ChainIterator<Object>(iterators);
    }

    @Test
    public void emptyIteratorListHasNoNext() {
        List<Iterator<Object>> iterators = Arrays.asList();
        ChainIterator<Object> chain = new ChainIterator<Object>(iterators.iterator());
        Assert.assertFalse(chain.hasNext());
    }

    @Test
    public void emptyIteratorHasNoNext() {
        List<Object> emptyIter = new ArrayList<Object>();
        List<Iterator<Object>> iterators = Arrays.asList(emptyIter.iterator());
        ChainIterator<Object> chain = new ChainIterator<Object>(iterators.iterator());
        Assert.assertFalse(chain.hasNext());
    }

    @Test
    public void nonEmptyIteratorHasNext() {
        List<Object> nonEmptyIter = Arrays.<Object>asList(1);
        List<Iterator<Object>> iterators = Arrays.asList(nonEmptyIter.iterator());
        ChainIterator<Object> chain = new ChainIterator<Object>(iterators.iterator());
        Assert.assertTrue(chain.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removingFromChainIteratorYieldsException() {
        List<Object> nonEmptyIter = new ArrayList<Object>();
        nonEmptyIter.add(1);
        List<Iterator<Object>> iterators = Arrays.asList(nonEmptyIter.iterator());
        ChainIterator<Object> chain = new ChainIterator<Object>(iterators.iterator());
        chain.next();
        chain.remove();
    }
}
