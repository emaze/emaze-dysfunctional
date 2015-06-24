package net.emaze.dysfunctional.ranges;

import java.util.Iterator;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RangeIteratorTest {

    @Test
    public void hasNextWhenInRange() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, Optional.of(1));
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void hasNoNextWhenConsumed() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, Optional.of(1));
        iter.next();
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void emptyRangeIteratorIsEmpty() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, Optional.of(0));
        Assert.assertFalse(iter.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullSequencingPolicyYieldsException() {
        new RangeIterator<Integer>(null, RangeMother.comparator, 0, Optional.of(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullComparatorYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, null, 0, Optional.of(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullLowerBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, null, Optional.of(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullUpperBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, null);
    }
}
