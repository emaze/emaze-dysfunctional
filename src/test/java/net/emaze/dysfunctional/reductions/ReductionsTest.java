package net.emaze.dysfunctional.reductions;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ReductionsTest {

    private List<Integer> list = Arrays.asList(1, 2);
    private Integer[] array = new Integer[]{1, 2};

    @Test
    public void canCountFromIterator() {
        Assert.assertEquals(2l, Reductions.count(list.iterator()));
    }

    @Test
    public void testCount_Iterable() {
        Assert.assertEquals(2l, Reductions.count(list));
    }

    @Test
    public void canCountFromArray() {
        Assert.assertEquals(2l, Reductions.count(array));
    }
}
