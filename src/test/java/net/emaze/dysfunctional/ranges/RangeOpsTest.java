package net.emaze.dysfunctional.ranges;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import static net.emaze.dysfunctional.ranges.RangeMother.*;

/**
 *
 * @author rferranti
 */
public class RangeOpsTest {

    @Test
    public void canPerformDifferenceOnTwoDisjointRanges() {
        Range<Integer> lhs = r(0, 10);
        Range<Integer> rhs = r(11, 20);
        Assert.assertEquals(r(0, 10), RangeOps.difference(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoOverlappingRanges() {
        Range<Integer> lhs = r(0, 10);
        Range<Integer> rhs = r(8, 20);
        Assert.assertEquals(r(0, 7), RangeOps.difference(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoOverlappingRangesWithRhsLower() {
        Range<Integer> lhs = r(8, 20);
        Range<Integer> rhs = r(0, 10);
        Assert.assertEquals(r(11, 20), RangeOps.difference(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoNestedRanges() {
        Range<Integer> lhs = r(0, 20);
        Range<Integer> rhs = r(4, 10);
        Assert.assertEquals(r(p(0, 3), p(11, 20)), RangeOps.difference(sequencer, comparator, lhs, rhs));
    }

    @Test
    @Ignore // TODO: needs support for empty range
    public void canPerformIntersectOnTwoNonOverlappingRanges() {
        Range<Integer> lhs = r(0, 1);
        Range<Integer> rhs = r(2, 3);
        RangeOps.intersect(sequencer, comparator, lhs, rhs);// == emptyRange
    }

    @Test
    public void canPerformIntersectWithSparseRanges() {
        Range<Integer> lhs = r(p(0, 1), p(2, 4));
        Range<Integer> rhs = r(p(0, 1), p(5, 7));
        Assert.assertEquals(r(0, 1), RangeOps.intersect(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void canPerformIntersectOnTwoOverlappingRanges() {
        Range<Integer> lhs = r(0, 20);
        Range<Integer> rhs = r(4, 10);
        Assert.assertEquals(r(4, 10), RangeOps.intersect(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void canPerformsymmetriDifferenceOnTwoRanges() {
        Range<Integer> lhs = r(0, 15);
        Range<Integer> rhs = r(10, 20);
        Assert.assertEquals(r(p(0, 9), p(16, 20)), RangeOps.symmetricDifference(sequencer, comparator, lhs, rhs));
    }

    @Test
    public void rangeOpsIsNotFinal() {
        new RangeOps();
    }
}
