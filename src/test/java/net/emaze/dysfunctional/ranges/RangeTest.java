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
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 13, 15);
        Assert.assertFalse(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectNonOverlappingRangesInverted() {
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 13, 15);
        Assert.assertFalse(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingRanges() {
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 5, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingRangesInverted() {
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 5, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlapping() {
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 11);
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 10, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void canDetectOverlappingInverted() {
        Range lhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Range rhs = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 10, 15);
        Assert.assertTrue(lhs.overlaps(rhs));
    }

    @Test
    public void sameRangeOverlaps() {
        Range me = new DenseRange(new IntegerSequencingPolicy(), new ComparableComparator(), 0, 10);
        Assert.assertTrue(me.overlaps(me));
    }
}
