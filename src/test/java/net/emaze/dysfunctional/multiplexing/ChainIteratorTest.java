package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.Iterations;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ChainIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullListYieldsException() {
        final Iterator<Iterator<Object>> iterators = null;
        new ChainIterator<Object>(iterators);
    }

    @Test
    public void emptyIteratorListHasNoNext() {
        final List<Iterator<Object>> iterators = Arrays.asList();
        final ChainIterator<Object> chain = new ChainIterator<Object>(iterators.iterator());
        Assert.assertFalse(chain.hasNext());
    }

    @Test
    public void emptyIteratorHasNoNext() {
        final List<Object> emptyIter = new ArrayList<Object>();
        final Iterator<Iterator<Object>> iterators = Iterations.iterator(emptyIter.iterator());
        final ChainIterator<Object> chain = new ChainIterator<Object>(iterators);
        Assert.assertFalse(chain.hasNext());
    }

    @Test
    public void nonEmptyIteratorHasNext() {
        final List<Object> nonEmptyIter = Arrays.<Object>asList(1);
        final Iterator<Iterator<Object>> iterators = Iterations.iterator(nonEmptyIter.iterator());
        final ChainIterator<Object> chain = new ChainIterator<Object>(iterators);
        Assert.assertTrue(chain.hasNext());
    }

    @Test
    public void iteratorsAreChained(){
        final List<Integer> input1 = Arrays.asList(1,2);
        final List<Integer> input2 = Arrays.asList(3,4);
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterator(input1.iterator(), input2.iterator());
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,3,4),got);
    }

    @Test
    public void sameCollectionCanBeChainedTwoTimes(){
        final List<Integer> input = Arrays.asList(1,2);
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterator(input.iterator(), input.iterator());
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,1,2),got);
    }

    @Test
    public void twoEmptyIteratorsLeadToAnEmptyIterator(){
        final List<Integer> input = new ArrayList<Integer>();
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterator(input.iterator(), input.iterator());
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        Assert.assertEquals(false,iter.hasNext());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void consumingtwoEmptyIteratorsThrowsAnException(){
        final List<Integer> input = new ArrayList<Integer>();
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterator(input.iterator(), input.iterator());
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        iter.next();
    }

    @Test
    public void iteratorsIteratorIsNotConsumedUntilUsed() {
        final List<Integer> input = Arrays.asList(1,2);
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterable(input.iterator()).iterator();
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        Assert.assertTrue(iteratorsIterator.hasNext());
    }
    
    @Test
    public void iteratorsIteratorIsNotConsumedEagerlyOnNext() {
        final List<Integer> input = Arrays.asList(1,2);
        final Iterator<Iterator<Integer>> iteratorsIterator = Iterations.iterator(input.iterator(), input.iterator());
        final Iterator<Integer> iter = new ChainIterator<Integer>(iteratorsIterator);
        iter.next();
        Assert.assertTrue(iteratorsIterator.hasNext());
    }
}
