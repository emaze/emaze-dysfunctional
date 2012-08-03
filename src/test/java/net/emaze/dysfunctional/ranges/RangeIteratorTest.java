package net.emaze.dysfunctional.ranges;

import java.util.Iterator;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RangeIteratorTest {

    @Test
    public void hasNextWhenInRange() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 0, Maybe.just(1));
        Assert.assertTrue(iter.hasNext());
    }
    @Test
    public void hasNoNextWhenConsumed() {
        Iterator<Integer> iter = new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 0, Maybe.just(1));
        iter.next();
        Assert.assertFalse(iter.hasNext());
    }


    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullSequencingPolicyYieldsException() {
        new RangeIterator<Integer>(null, RangeMother.comparator, Endpoints.IncludeBoth, 0, Maybe.just(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullComparatorYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, null, Endpoints.IncludeBoth, 0, Maybe.just(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullLowerBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, null, Maybe.just(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingRangeIterWithNullUpperBoundYieldsException() {
        new RangeIterator<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoints.IncludeBoth, 0, null);
    }
}
