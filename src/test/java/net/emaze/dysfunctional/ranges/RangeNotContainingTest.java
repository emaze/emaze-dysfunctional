package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.delegates.Predicate;
import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.order.IntegerSequencingPolicy;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class RangeNotContainingTest {
    
    @Test
    public void acceptsWhenContains() {
        DenseRange<Integer> range = new DenseRange<Integer>(new IntegerSequencingPolicy(), new ComparableComparator<Integer>(), 0, 10);
        Predicate<DenseRange<Integer>> p = new RangeNotContaining<Integer>(0); 
        Assert.assertTrue(p.test(range));
    }

}