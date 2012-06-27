package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ChainIteratorTest {

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

    @Test
    public void iteratorsAreChained(){
        final List<Integer> input1 = Arrays.asList(1,2);
        final List<Integer> input2 = Arrays.asList(3,4);
        final Iterator<Iterator<Integer>> iteratorsIterator = Arrays.asList(input1.iterator(), input2.iterator()).iterator();
        Iterator<Integer> iter = new ChainIterator(iteratorsIterator);
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,3,4),got);
    }

    @Test
    public void sameCollectionCanBeChainedTwoTimes(){
        final List<Integer> input = Arrays.asList(1,2);
        final Iterator<Iterator<Integer>> iteratorsIterator = Arrays.asList(input.iterator(), input.iterator()).iterator();
        Iterator<Integer> iter = new ChainIterator(iteratorsIterator);
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,1,2),got);
    }

    @Test
    public void twoEmptyIteratorsLeadToAnEmptyIterator(){
        final List<Integer> input = new ArrayList<Integer>();
        final Iterator<Iterator<Integer>> iteratorsIterator = Arrays.asList(input.iterator(), input.iterator()).iterator();
        Iterator<Integer> iter = new ChainIterator(iteratorsIterator);
        Assert.assertEquals(false,iter.hasNext());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void consumingtwoEmptyIteratorsThrowsAnException(){
        final List<Integer> input = new ArrayList<Integer>();
        final Iterator<Iterator<Integer>> iteratorsIterator = Arrays.asList(input.iterator(), input.iterator()).iterator();
        Iterator<Integer> iter = new ChainIterator(iteratorsIterator);
        iter.next();
    }

    @Test
    public void iteratorsIteratorIsNotConsumedUntilUsed() {
        final List<Integer> input = Arrays.asList(1,2);
        final Iterator<Iterator<Integer>> iteratorsIterator = Arrays.asList(input.iterator()).iterator();
        Iterator<Integer> iter = new ChainIterator(iteratorsIterator);
        Assert.assertTrue(iteratorsIterator.hasNext());
    }
}
