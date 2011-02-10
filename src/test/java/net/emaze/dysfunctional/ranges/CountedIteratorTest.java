package net.emaze.dysfunctional.ranges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;
import static net.emaze.dysfunctional.ranges.RangeMother.*;
/**
 *
 * @author rferranti
 */
public class CountedIteratorTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingWithEmptyIteratorYieldsException() {
        Range<Integer> range = r(0, 10);
        new CountedIterator<Integer, Integer>(null, range);
    }

    @Test(expected=IllegalArgumentException.class)
    public void creatingWithEmptyRangeYieldsException() {
        new CountedIterator<Integer, Integer>(Arrays.asList(1).iterator(), null);
    }
    
    @Test
    public void hasNextWhenNestedIteratorHasNext() {
        Range<Integer> range = r(0, 10);
        CountedIterator<Integer, Integer> iter = new CountedIterator<Integer, Integer>(Arrays.asList(1).iterator(), range);
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void hasNoNextWhenNestedIteratorHasNoNext() {
        Range<Integer> range = r(0, 10);
        CountedIterator<Integer, Integer> iter = new CountedIterator<Integer, Integer>(Arrays.<Integer>asList().iterator(), range);
        Assert.assertFalse(iter.hasNext());
    }
    
    @Test
    public void canFetchNext() {
        Range<Integer> range = r(0, 10);
        CountedIterator<Integer, Integer> iter = new CountedIterator<Integer, Integer>(Arrays.asList(111).iterator(), range);
        Pair<Integer,Integer > got = iter.next();
        Assert.assertEquals(Pair.of(0, 111), got);
    }
    
    @Test
    public void canRemoveElementFromInnerIterator() {
        Range<Integer> range = r(0, 10);
        List<Integer> bucket = new ArrayList<Integer>();
        bucket.add(1);
        CountedIterator<Integer, Integer> iter = new CountedIterator<Integer, Integer>(bucket.iterator(), range);
        iter.next();
        iter.remove();
        Assert.assertTrue(bucket.isEmpty());
    }

}