package net.emaze.dysfunctional.ranges;

import java.lang.Integer;
import java.util.Collections;
import java.util.List;
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
        public void test() {
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
    }
}
