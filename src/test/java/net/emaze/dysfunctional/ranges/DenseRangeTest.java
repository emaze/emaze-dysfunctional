package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
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
    DenseRangeTest.Functions.class,
    DenseRangeTest.Degenerations.class
})
public class DenseRangeTest {

    public static class Functions {

        @Test
        public void sameRangeHasSameHashcode() {
            Assert.assertEquals(RangeMother.r(0, 10).hashCode(), RangeMother.r(0, 10).hashCode());
        }

        @Test
        public void toStringReflectsRange() {
            Assert.assertEquals("[0-10]", RangeMother.r(0, 10).toString());
        }

        @Test
        public void rangeIsNotEqualToNull() {
            Assert.assertFalse(RangeMother.r(0, 10).equals(null));
        }

        @Test
        public void rangeIsEqualToItself() {
            DenseRange<Integer> range = RangeMother.r(0, 1);
            Assert.assertTrue(range.equals(range));
        }

        @Test
        public void rangeIsEqualToRangeWithDifferentBounds() {
            DenseRange<Integer> former = RangeMother.r(0, 1);
            DenseRange<Integer> latter = RangeMother.r(0, 2);
            Assert.assertFalse(former.equals(latter));
        }

        @Test
        public void rangesWithSameBoundsHaveSameOrder() {
            DenseRange<Integer> former = RangeMother.r(0, 10);
            DenseRange<Integer> latter = RangeMother.r(0, 10);
            Assert.assertEquals(Order.EQ.order(), former.compareTo(latter));
        }

        @Test
        public void densifiedDenseRangeIsEqualsToItselfInList() {
            DenseRange<Integer> range = RangeMother.r(0, 10);
            Assert.assertEquals(Arrays.asList(range), range.densified());
        }

        @Test
        public void elementInRangeIsContained() {
            DenseRange<Integer> range = RangeMother.r(0, 2);
            Assert.assertTrue(range.contains(1));
        }

        @Test
        public void elementPriorToLowerBoundIsNotContained() {
            DenseRange<Integer> range = RangeMother.r(1, 2);
            Assert.assertFalse(range.contains(0));
        }

        @Test
        public void elementAfterUpperBoundIsNotContained() {
            DenseRange<Integer> range = RangeMother.r(1, 2);
            Assert.assertFalse(range.contains(3));
        }
    }

    public static class Degenerations {

        @Test(expected = IllegalArgumentException.class)
        public void creatingDenseRangeWithNullSequencerYieldsException() {
            new DenseRange<Integer>(null, RangeMother.comparator, 0, 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingDenseRangeWithNullComparatorYieldsException() {
            new DenseRange<Integer>(RangeMother.sequencer, null, 0, 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingDenseRangeWithNullLowerBoundYieldsException() {
            new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, null, 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingDenseRangeWithNullUpperBoundYieldsException() {
            new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 0, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void creatingDenseRangeWithUpperBoundLesserThenLowerBoundYieldsException() {
            new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 10, 0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void checkingForOverlapsWithNullYieldsException() {
            RangeMother.r(0, 10).overlaps(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void comparingWithNullYieldsException() {
            RangeMother.r(0, 10).compareTo(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void checkingIfContainsForNullYieldsException() {
            RangeMother.r(0, 10).contains(null);
        }
        
        @Test(expected = ClassCastException.class)
        public void callingErasureWithWrongTypeYieldsException() {
            Comparable c = RangeMother.r(0, 10);
            c.compareTo(new Object());
        }   
        
        @Test
        public void madness() {
            final IntegerSequencingPolicy seqPolicy = new IntegerSequencingPolicy();
            final ComparableComparator<Integer> comparator = new ComparableComparator<Integer>();
            DenseRange<Integer> a = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeLeft, 0, 10);
            DenseRange<Integer> b = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeBoth, 10,20);
            
            Assert.assertFalse(a.overlaps(b));
            
        }
        
        
        @Test
        public void madness2() {
            final IntegerSequencingPolicy seqPolicy = new IntegerSequencingPolicy();
            final ComparableComparator<Integer> comparator = new ComparableComparator<Integer>();
            DenseRange<Integer> a = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeBoth, 0, 10);
            DenseRange<Integer> b = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeRight, 10, 20);
            
            Assert.assertFalse(a.overlaps(b));
            
        }
        
         @Test
        public void madness3() {
             final IntegerSequencingPolicy seqPolicy = new IntegerSequencingPolicy();
            final ComparableComparator<Integer> comparator = new ComparableComparator<Integer>();
            DenseRange<Integer> a = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeLeft, 0, 10);
            DenseRange<Integer> b = new DenseRange<Integer>(seqPolicy, comparator, Endpoints.IncludeRight, 10, 20);
            final Range<Integer> union = RangeOps.union(seqPolicy, comparator, a, b);
            System.out.println(union);
            Assert.assertTrue(union instanceof SparseRange);
            
        }
    }
}
