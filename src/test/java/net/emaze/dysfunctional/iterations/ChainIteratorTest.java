package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.multiplexing.ChainIterator;
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
public class ChainIteratorTest {

    @Test
    public void iteratorsAreChained(){
        final List<Integer> input1 = Arrays.asList(1,2);
        final List<Integer> input2 = Arrays.asList(3,4);
        Iterator<Integer> iter = new ChainIterator(Iterations.iterator(input1.iterator(),input2.iterator()));
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,3,4),got);
    }

    @Test
    public void sameCollectionCanBeChainedTwoTimes(){
        final List<Integer> input = Arrays.asList(1,2);
        Iterator<Integer> iter = new ChainIterator(Iterations.iterator(input.iterator(),input.iterator()));
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,1,2),got);
    }

    @Test
    public void twoEmptyIteratorsLeadToAnEmptyIterator(){
        final List<Integer> input = new ArrayList<Integer>();
        Iterator<Integer> iter = new ChainIterator(Iterations.iterator(input.iterator(),input.iterator()));
        Assert.assertEquals(false,iter.hasNext());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void consumingtwoEmptyIteratorsThrowsAnException(){
        final List<Integer> input = new ArrayList<Integer>();
        Iterator<Integer> iter = new ChainIterator(Iterations.iterator(input.iterator(),input.iterator()));
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
