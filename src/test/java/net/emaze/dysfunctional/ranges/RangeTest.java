package net.emaze.dysfunctional.ranges;

import java.util.Comparator;
import java.util.List;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.iterations.Iterations;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author dangelocola
 */
@SuiteClasses({RangeTest.DenseRangeTest.class, RangeTest.SparseRangeTest.class, RangeTest.MixedRangeTest.class})
@RunWith(Suite.class)
public class RangeTest {

    private static Range<Integer> r(int lower, int upper) {
        return new DenseRange<Integer>(new IntegerSequencingPolicy(), new ComparableComparator<Integer>(), lower, upper);
    }

    private static Pair<Integer, Integer> p(int lower, int upper) {
        return new Pair<Integer, Integer>(lower, upper);
    }

    private static Range<Integer> r(Pair<Integer, Integer>... pairs) {
        final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
        final Comparator<Integer> comparator = new ComparableComparator<Integer>();

        final List<DenseRange<Integer>> ranges = Iterations.map(pairs, new Delegate<DenseRange<Integer>, Pair<Integer, Integer>>() {

            @Override
            public DenseRange<Integer> perform(Pair<Integer, Integer> pair) {
                return new DenseRange<Integer>(sequencer, comparator, pair.first(), pair.second());
            }
        });
        return new SparseRange<Integer>(sequencer, comparator, ranges);
    }

    public static class SparseRangeTest {

        @Test
        public void canDetectNonOverlappingSparseRangesNested() {
            Range<Integer> lhs = r(p(0, 1), p(4, 5));
            Range<Integer> rhs = r(p(2, 2), p(3, 3));
            Assert.assertFalse(lhs.overlaps(rhs));
        }

        @Test
        public void canDetectNonOverlappingSparseRangesWhenLowerOrderIsLhs() {
            Range<Integer> lhs = r(p(1, 1), p(2, 2));
            Range<Integer> rhs = r(p(3, 3), p(4, 4));
            Assert.assertFalse(lhs.overlaps(rhs));
        }

        @Test
        public void canDetectNonOverlappingSparseRangesWhenLowerOrderIsRhs() {
            Range<Integer> lhs = r(p(3, 3), p(4, 4));
            Range<Integer> rhs = r(p(1, 1), p(2, 2));
            Assert.assertFalse(lhs.overlaps(rhs));
        }

        @Test
        public void canDetectOverlappingSparseRangesWhenLhsContainsRhs() {
            Range<Integer> lhs = r(p(1, 10), p(11, 11));
            Range<Integer> rhs = r(p(2, 3), p(5, 6));
            Assert.assertTrue(lhs.overlaps(rhs));
        }
        
        @Test
        public void canDetectOverlappingSparseRangesWhenRhsContainsLhs() {
            Range<Integer> lhs = r(p(2, 3), p(5, 6));
            Range<Integer> rhs = r(p(1, 10), p(11, 11));
            Assert.assertTrue(lhs.overlaps(rhs));
        }
    }

    public static class MixedRangeTest {
        @Test
        public void canDetectNonOverlappingSparseRangesWhenRhsIsNestedInLhs(){
            Range<Integer> lhs = r(p(0, 1), p(4, 5));
            Assert.assertFalse(lhs.overlaps(r(2,3)));
        }
    }
    public static class DenseRangeTest {

        @Test
        public void canDetectNonOverlappingRanges() {
            Assert.assertFalse(r(0, 10).overlaps(r(13, 15)));
        }

        @Test
        public void canDetectNonOverlappingRangesInverted() {
            Assert.assertFalse(r(13, 15).overlaps(r(0, 10)));
        }

        @Test
        public void canDetectOverlappingRanges() {
            Assert.assertTrue(r(0, 10).overlaps(r(5, 15)));
        }

        @Test
        public void canDetectOverlappingRangesInverted() {
            Assert.assertTrue(r(5, 15).overlaps(r(0, 10)));
        }

        @Test
        public void canDetectOverlapping() {
            Assert.assertTrue(r(10, 15).overlaps(r(0, 11)));
        }

        @Test
        public void canDetectOverlappingInverted() {
            Assert.assertTrue(r(0, 10).overlaps(r(10, 15)));
        }

        @Test
        public void sameRangeOverlaps() {
            Assert.assertTrue(r(0, 10).overlaps(r(0, 10)));
        }

        @Test
        public void lowerBoundIsIncludedInRange() {
            Assert.assertTrue(r(0, 10).contains(0));
        }

        @Test
        public void upperBoundIsIncludedInRange() {
            Assert.assertTrue(r(0, 10).contains(10));
        }

        @Test
        public void canIterateDegenerateRange() {
            Assert.assertEquals(new Integer(0), r(0, 0).iterator().next());
        }
    }
}
