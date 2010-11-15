package net.emaze.dysfunctional.ranges;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import junit.framework.Assert;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import net.emaze.dysfunctional.order.SequencingPolicy;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SortedNonOverlappingRangesTransformerTest {
    private final SequencingPolicy<Integer> sequencer = new IntegerSequencingPolicy();
    private final Comparator<Integer> comparator = new ComparableComparator<Integer>();
    
    @Test
    public void canMergeTwoContiguousNonOverlappingRanges() {
        DenseRange<Integer> a = new DenseRange<Integer>(sequencer, comparator, 0, 1);
        DenseRange<Integer> b = new DenseRange<Integer>(sequencer, comparator, 2, 3);
        List<DenseRange<Integer>> got = new SortedNonOverlappingRangesTransformer<Integer>(sequencer, comparator).perform(Arrays.asList(a,b));
        Assert.assertEquals(1, got.size());
    }
}
