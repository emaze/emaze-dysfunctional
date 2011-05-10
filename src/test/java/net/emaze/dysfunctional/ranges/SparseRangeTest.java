package net.emaze.dysfunctional.ranges;

import java.lang.Integer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.emaze.dysfunctional.order.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SparseRangeTest.Functions.class,
    SparseRangeTest.Degenerations.class
})
public class SparseRangeTest {

    public static class Functions {

        @Test
        public void rangeIsNotEqualsToNull() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 2), RangeMother.p(3, 5));
            Assert.assertFalse(sr.equals(null));
        }

        @Test
        public void rangeIsEqualsToItSelf() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 2), RangeMother.p(3, 5));
            Assert.assertTrue(sr.equals(sr));
        }

        @Test
        public void sameRangesLeadsToSameHashCode() {
            SparseRange<Integer> former = RangeMother.r(RangeMother.p(0, 2), RangeMother.p(2, 3));
            SparseRange<Integer> latter = RangeMother.r(RangeMother.p(0, 3));
            Assert.assertEquals(former.hashCode(), latter.hashCode());
        }

        @Test
        public void containsElementIfAnyRangeContainsIt() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 2), RangeMother.p(3, 5));
            Assert.assertTrue(sr.contains(4));
        }

        @Test
        public void canIterateSparseRange() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 2));
            Assert.assertNotNull(sr.iterator());
        }

        @Test
        public void canCreateSparseRangeWithUnsortedDenseRanges() {
            SparseRange<Integer> sr = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(2, 3), RangeMother.r(0, 1));
        }

        @Test
        public void sameDensifiedRangeHaveSameOrder() {
            SparseRange<Integer> former = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(0, 1), RangeMother.r(1, 2), RangeMother.r(4, 5));
            SparseRange<Integer> latter = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(0, 2), RangeMother.r(4, 5));
            Assert.assertEquals(Order.SAME_ORDER, former.compareTo(latter));
        }

        @Test
        public void lowestRangeLowerBoundIsLowerBound() {
            SparseRange<Integer> range = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(2, 3), RangeMother.r(0, 1));
            Assert.assertEquals(Integer.valueOf(0), range.lower());
        }

        @Test
        public void upperRangeUpperBoundIsUpperBound() {
            SparseRange<Integer> range = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(2, 3), RangeMother.r(0, 1));
            Assert.assertEquals(Integer.valueOf(3), range.upper());
        }

        @Test
        public void canDensifySparseRange() {
            SparseRange<Integer> range = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(0, 1), RangeMother.r(1, 2), RangeMother.r(4, 5));
            Assert.assertEquals(Arrays.asList(RangeMother.r(0, 2), RangeMother.r(4, 5)), range.densified());
        }

        @Test
        public void toStringShowsDensifiedRanges() {
            SparseRange<Integer> sr = new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, RangeMother.r(0, 1), RangeMother.r(1, 2), RangeMother.r(4, 5));
            Assert.assertEquals("[0-2,4-5]", sr.toString());
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeWithNullSequencerYieldsException() {
            new SparseRange<Integer>(null, RangeMother.comparator, RangeMother.r(0, 1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeWithNullComparatorYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, null, RangeMother.r(0, 1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeWithNullRangeYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, (DenseRange<Integer>[]) null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeWithEmptyRangeYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeFromListWithNullSequencerYieldsException() {
            new SparseRange<Integer>(null, RangeMother.comparator, RangeMother.r(0, 1).densified());
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeFromListWithNullComparatorYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, null, RangeMother.r(0, 1).densified());
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeFromListWithNullRangeYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, (List<DenseRange<Integer>>) null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingSparseRangeFromListWithEmptyRangeYieldsException() {
            new SparseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Collections.<DenseRange<Integer>>emptyList());
        }

        @Test(expected = IllegalArgumentException.class)
        public void checkingContainsAgainstNullYieldsException() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 10));
            sr.contains(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void checkingOverlapsAgainstNullYieldsException() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 10));
            sr.overlaps(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void comparingAgainstNullYieldsException() {
            SparseRange<Integer> sr = RangeMother.r(RangeMother.p(0, 10));
            sr.compareTo(null);
        }

        @Test(expected = ClassCastException.class)
        public void callingErasureWithWrongTypeYieldsException() {
            Comparable c = RangeMother.r(RangeMother.p(0, 10));
            c.compareTo(new Object());
        }
    }
}
