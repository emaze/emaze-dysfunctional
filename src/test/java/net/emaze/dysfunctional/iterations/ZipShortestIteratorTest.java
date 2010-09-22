package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.iterations.ZipShortestIterator;
import net.emaze.dysfunctional.tuples.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ZipShortestIteratorTest {
    
    @Test
    public void emptyCollectionsLeadsToEmptyIterator() {
        List<Integer> ints = new ArrayList<Integer>();
        List<Long> longs = new ArrayList<Long>();
        Iterator<Pair<Integer,Long>> iter = new ZipShortestIterator(ints.iterator(), longs.iterator());
        Assert.assertFalse(iter.hasNext());
    }
    
    @Test
    public void consumedIteratorsHasNoNextElement() {
        List<Integer> ints = Arrays.asList(1);
        List<Long> longs = Arrays.asList(2l);
        Iterator<Pair<Integer,Long>> iter = new ZipShortestIterator(ints.iterator(), longs.iterator());
        iter.next();
        Assert.assertFalse(iter.hasNext());
    }


    public void iteratorsAreConsumedInOrder() {
        List<Integer> ints = Arrays.asList(1,2);
        List<Long> longs = Arrays.asList(11l,12l);
        Iterator<Pair<Integer,Long>> iter = new ZipShortestIterator(ints.iterator(), longs.iterator());
        Assert.assertEquals(new Pair<Integer,Long>(1,11l), iter.next());
        Assert.assertEquals(new Pair<Integer,Long>(2,12l), iter.next());
    }

}
