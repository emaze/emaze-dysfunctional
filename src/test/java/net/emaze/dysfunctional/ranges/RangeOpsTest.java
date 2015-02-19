package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.ranges.Range.Endpoint;
import org.junit.Assert;
import org.junit.Test;
import static net.emaze.dysfunctional.ranges.RangeMother.*;
import net.emaze.dysfunctional.ranges.Difference;
import net.emaze.dysfunctional.ranges.Intersection;
import net.emaze.dysfunctional.ranges.SymmetricDifference;

/**
 *
 * @author rferranti
 */
public class RangeOpsTest {

    @Test
    public void canPerformDifferenceOnTwoDisjointRanges() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        final Range<Integer> lhs = r(0, 10);
        final Range<Integer> rhs = r(11, 20);
        Assert.assertEquals(r(0, 10), difference.apply(lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoOverlappingRanges() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 10);
        Range<Integer> rhs = r(8, 20);
        Assert.assertEquals(r(0, 7), difference.apply(lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoOverlappingRangesWithRhsLower() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(8, 20);
        Range<Integer> rhs = r(0, 10);
        Assert.assertEquals(r(11, 20), difference.apply(lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnTwoNestedRanges() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 20);
        Range<Integer> rhs = r(4, 10);
        Assert.assertEquals(r(p(0, 3), p(11, 20)), difference.apply(lhs, rhs));
    }

    @Test
    public void canPerformDifferenceOnSelf() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        Range<Integer> x = r(0, 20);
        Assert.assertEquals(r(Endpoint.Include, 0, 0, Endpoint.Exclude), difference.apply(x, x));
    }

    @Test
    public void canPerformDifferenceYieldingEmptyRange() {
        final Difference<Integer> difference = new Difference<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 10);
        Range<Integer> rhs = r(0, 100);
        Assert.assertFalse(difference.apply(lhs, rhs).iterator().hasNext());
    }

    @Test
    public void canPerformIntersectOnTwoNonOverlappingRanges() {
        final Intersection<Integer> intersection = new Intersection<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 1);
        Range<Integer> rhs = r(2, 3);
        Assert.assertFalse(intersection.apply(lhs, rhs).iterator().hasNext());
    }

    @Test
    public void canPerformIntersectWithSparseRanges() {
        final Intersection<Integer> intersection = new Intersection<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(p(0, 1), p(2, 4));
        Range<Integer> rhs = r(p(0, 1), p(5, 7));
        Assert.assertEquals(r(0, 1), intersection.apply(lhs, rhs));
    }

    @Test
    public void canPerformIntersectOnTwoOverlappingRanges() {
        final Intersection<Integer> intersection = new Intersection<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 20);
        Range<Integer> rhs = r(4, 10);
        Assert.assertEquals(r(4, 10), intersection.apply(lhs, rhs));
    }

    @Test
    public void canPerformsymmetriDifferenceOnTwoRanges() {
        final SymmetricDifference<Integer> symmDiff = new SymmetricDifference<Integer>(sequencer, comparator, 1);
        Range<Integer> lhs = r(0, 15);
        Range<Integer> rhs = r(10, 20);
        Assert.assertEquals(r(p(0, 9), p(16, 20)), symmDiff.apply(lhs, rhs));
    }
}
