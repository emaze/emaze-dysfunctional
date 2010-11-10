package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author dangelocola
 */
public class RangeTest {

    @Test
    public void canDetectNonOverlappingRanges() {
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 13, 15);
        Assert.assertFalse(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectNonOverlappingRangesInverted() {
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 13, 15);
        Assert.assertFalse(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingRanges() {
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 5, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingRangesInverted() {
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 5, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlapping() {
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 11);
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 10, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingInverted() {
        Range<Integer> lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range<Integer> rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 10, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void sameRangeOverlaps() {
        Range<Integer> range = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Assert.assertTrue(range.overlaps(range));
    }

    @Test
    public void lowerBoundIsIncludedInRange() {
        Range<Integer> range = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Assert.assertTrue(range.contains(0));
    }

    @Test
    public void upperBoundIsIncludedInRange() {
        Range<Integer> range = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Assert.assertTrue(range.contains(10));
    }

    @Test
    public void canIterateDegenerateRange(){
        Range<Integer> range = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 0);
        Assert.assertEquals( new Integer(0), range.iterator().next());
    }    
}
