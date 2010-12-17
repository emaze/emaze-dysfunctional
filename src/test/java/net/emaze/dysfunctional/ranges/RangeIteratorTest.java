package net.emaze.dysfunctional.ranges;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RangeIteratorTest {

    @Test
    public void hasNextWhenInRange() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, 0);
        Assert.assertTrue(iter.hasNext());
    }
    @Test
    public void hasNoNextWhenConsumed() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, 0);
        iter.next();
        Assert.assertFalse(iter.hasNext());
    }


    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullSequencingPolicyYieldsException() {
        new RangeIterator<Integer>(null, RangeMother.comparator, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullComparatorYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, null, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullLowerBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullUpperBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, null);
    }
}
