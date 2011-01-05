package net.emaze.dysfunctional.reductions;

import net.emaze.dysfunctional.reductions.CountingReductor;
import net.emaze.dysfunctional.adapting.ArrayIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CountingReductorTest {

    @Test
    public void canCountAnIterator() {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        CountingReductor<Integer> cc = new CountingReductor<Integer>();
        long count = cc.consume(new ArrayIterator<Integer>(arr));
        Assert.assertEquals(4, count);
    }

    @Test(expected=IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        new CountingReductor<Integer>().consume(null);
    }
}
