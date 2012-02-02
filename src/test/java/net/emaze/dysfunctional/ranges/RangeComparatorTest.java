package net.emaze.dysfunctional.ranges;

import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.Comparing;
import net.emaze.dysfunctional.order.Order;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RangeComparatorTest {

    public static class MockRange implements Range<Integer> {

        private final int lower;
        private final int upper;

        public MockRange(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public Integer lower() {
            return lower;
        }

        @Override
        public Integer upper() {
            return upper;
        }

        @Override
        public boolean contains(Integer element) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean overlaps(Range<Integer> rhs) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Iterator<Integer> iterator() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int compareTo(Range<Integer> o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<DenseRange<Integer>> densified() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Test
    public void lowestRangesComesFirst() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(0, 2);
        MockRange greater = new MockRange(3, 5);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }

    @Test
    public void lowestRangesComesFirstWithNegativeRanges() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(-5, -3);
        MockRange greater = new MockRange(-2, -1);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }

    @Test
    public void lowerBoundsHasPriorityWhileDecidingOrder() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(0, 2);
        MockRange greater = new MockRange(1, 2);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }

    @Test
    public void lowerBoundsHasPriorityWhileDecidingOrderWithNegativeRanges() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(-5, -3);
        MockRange greater = new MockRange(-4, -3);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }

    @Test
    public void withRangesWithSameLowerBoundNarrowerComesFirst() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(0, 4);
        MockRange greater = new MockRange(0, 3);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }

    @Test
    public void forNegativeRangesWithSameLowerBoundNarrowerComesFirst() {
        RangeComparator<Integer> comparator = new RangeComparator<Integer>();
        MockRange lesser = new MockRange(-5, 0);
        MockRange greater = new MockRange(-5, -3);
        Assert.assertEquals(Order.LT, Order.of(comparator, lesser, greater));
    }
}
