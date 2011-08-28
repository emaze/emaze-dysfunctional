package net.emaze.dysfunctional.convolutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ZipShortestIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingZipWithNullFormerIteratorYieldException() {
        new ZipShortestIterator<Integer, Integer>(null, new ArrayIterator<Integer>(new Integer[]{1}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingZipWithNullLatterIteratorYieldException() {
        new ZipShortestIterator<Integer, Integer>(new ArrayIterator<Integer>(new Integer[]{1}), null);
    }

    @Test
    public void iteratorHasNextWhenBothIteratorsHaveNext() {
        Iterator<Integer> former = new ArrayIterator<Integer>(new Integer[]{1});
        Iterator<Integer> latter = new ArrayIterator<Integer>(new Integer[]{2});
        ZipShortestIterator<Integer, Integer> zipsi = new ZipShortestIterator<Integer, Integer>(former, latter);
        Assert.assertTrue(zipsi.hasNext());
    }
    
    @Test
    public void firstNextContainsFirstElements() {
        Iterator<Integer> former = new ArrayIterator<Integer>(new Integer[]{1});
        Iterator<Integer> latter = new ArrayIterator<Integer>(new Integer[]{2});
        ZipShortestIterator<Integer, Integer> zipsi = new ZipShortestIterator<Integer, Integer>(former, latter);
        Pair<Integer,Integer> got = zipsi.next();
        Assert.assertEquals(Pair.of(1,2), got);
    }

    @Test
    public void iteratorHasNoNextWhenOnlyFormerIteratorHasNext() {
        Iterator<Integer> former = new ArrayIterator<Integer>(new Integer[]{1});
        Iterator<Integer> latter = new ArrayIterator<Integer>(new Integer[]{});
        ZipShortestIterator<Integer, Integer> zipsi = new ZipShortestIterator<Integer, Integer>(former, latter);
        Assert.assertFalse(zipsi.hasNext());
    }

    @Test
    public void iteratorHasNoNextWhenOnlyLatterIteratorHasNext() {
        Iterator<Integer> former = new ArrayIterator<Integer>(new Integer[]{});
        Iterator<Integer> latter = new ArrayIterator<Integer>(new Integer[]{2});
        ZipShortestIterator<Integer, Integer> zipsi = new ZipShortestIterator<Integer, Integer>(former, latter);
        Assert.assertFalse(zipsi.hasNext());
    }
    @Test
    public void removingFromZipRemovesFromBothIterators() {
        List<Integer> former =  new ArrayList<Integer>();
        former.add(1);
        List<Integer> latter =  new ArrayList<Integer>();
        latter.add(2);
        ZipShortestIterator<Integer, Integer> zipsi = new ZipShortestIterator<Integer, Integer>(former.iterator(), latter.iterator());
        zipsi.next();
        zipsi.remove();
        Assert.assertEquals(0, former.size() + latter.size());
    }
}
