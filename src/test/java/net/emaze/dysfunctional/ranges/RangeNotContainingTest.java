package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import org.junit.Assert;
import org.junit.Test;
import static net.emaze.dysfunctional.ranges.RangeMother.*;

/**
 *
 * @author rferranti
 */
public class RangeNotContainingTest {
    
    @Test
    public void acceptsWhenContains() {
        DenseRange<Integer> range = r(0, 10);
        Predicate<DenseRange<Integer>> p = new RangeNotContaining<Integer>(0); 
        Assert.assertTrue(p.test(range));
    }

}