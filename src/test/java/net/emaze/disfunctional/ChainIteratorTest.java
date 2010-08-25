package net.emaze.disfunctional;

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
    public void sameCollectionCanBeChainedTwoTimes(){
        final List<Integer> input = Arrays.asList(1,2);
        Iterator<Integer> iter = new ChainIterator(input,input);
        final List<Integer> got = new ArrayList<Integer>();
        while(iter.hasNext()){
            got.add(iter.next());
        }
        Assert.assertEquals(Arrays.asList(1,2,1,2),got);
    }
    //@Test
    public void twoEmptyIteratorsLeadToAnEmptyIterator(){
        final List<Integer> input = new ArrayList<Integer>();
        Iterator<Integer> iter = new ChainIterator(input,input);
        Assert.assertEquals(false,iter.hasNext());
    }
    
    //@Test(expected=NoSuchElementException.class)
    public void consumingtwoEmptyIteratorThrowsAnException(){
        final List<Integer> input = new ArrayList<Integer>();
        Iterator<Integer> iter = new ChainIterator(input,input);
        iter.next();
    }
}