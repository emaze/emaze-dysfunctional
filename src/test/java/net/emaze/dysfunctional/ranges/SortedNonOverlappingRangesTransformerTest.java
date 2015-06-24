package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import java.util.function.Function;
import java.util.Optional;
import net.emaze.dysfunctional.ranges.Range.Endpoint;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SortedNonOverlappingRangesTransformerTest {

    /**
     * |---| |---|
     */
    @Test
    public void canMergeTwoContiguousNonOverlappingRanges() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(1), Endpoint.Include);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 2, Optional.of(2), Endpoint.Include);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    /**
     * |---| |---|
     */
    @Test
    public void canMergeTwoOverlappingRanges() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(2), Endpoint.Include);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 2, Optional.of(3), Endpoint.Include);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 3));
        Assert.assertEquals(expected, got);
    }

    /**
     * |---| |-|
     */
    @Test
    public void canMergeTwoOverlappingRangesWhenLatterIsSubset() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(2), Endpoint.Include);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 2, Optional.of(2), Endpoint.Include);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    /**
     * |-| |---|
     */
    @Test
    public void canMergeTwoOverlappingRangesWhenFormerIsSubset() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(1), Endpoint.Include);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(2), Endpoint.Include);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 2));
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canMergeSameEmptyRanges() {
        final DenseRange<Integer> a = RangeMother.r(Endpoint.Include, 0, 0, Endpoint.Exclude);
        final DenseRange<Integer> b = RangeMother.r(Endpoint.Include, 0, 0, Endpoint.Exclude);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        Assert.assertTrue(got.isEmpty());
    }

    @Test
    public void canMergeDifferentEmptyRanges() {
        final DenseRange<Integer> a = RangeMother.r(Endpoint.Include, 0, 0, Endpoint.Exclude);
        final DenseRange<Integer> b = RangeMother.r(Endpoint.Include, 1, 1, Endpoint.Exclude);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        Assert.assertTrue(got.isEmpty());
    }

    /**
     * |-|. .|-|
     */
    @Test
    public void nonOverlappingRangesNonCountiguousRangesAreNotMerged() {
        final DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 1, Optional.of(1), Endpoint.Include);
        final DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, Endpoint.Include, 3, Optional.of(3), Endpoint.Include);
        final List<DenseRange<Integer>> got = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator).apply(Arrays.asList(a, b));
        final List<DenseRange<Integer>> expected = Arrays.asList(RangeMother.r(1, 1), RangeMother.r(3, 3));
        Assert.assertEquals(expected, got);
    }

    @Test(expected = ClassCastException.class)
    public void callingErasureWithWrongTypeYieldsException() {
        Function d = new Densify<Integer>(RangeMother.sequencer, RangeMother.comparator);
        d.apply(new Object());
    }
}
