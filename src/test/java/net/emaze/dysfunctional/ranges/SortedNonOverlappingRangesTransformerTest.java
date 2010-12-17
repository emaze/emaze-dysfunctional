package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SortedNonOverlappingRangesTransformerTest {

    @Test
    public void canMergeTwoContiguousNonOverlappingRanges() {
        DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 1, 1);
        DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 2, 2);
        List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        Assert.assertEquals(1, got.size());
    }
    
    @Test
    public void nonOverlappingRangesNonCountiguousRangesAreNotMerged() {
        DenseRange<Integer> a = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 1, 1);
        DenseRange<Integer> b = new DenseRange<Integer>(RangeMother.sequencer, RangeMother.comparator, 3, 3);
        List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(RangeMother.sequencer, RangeMother.comparator).perform(Arrays.asList(a, b));
        Assert.assertEquals(2, got.size());
    }
}
