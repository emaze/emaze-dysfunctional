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
    public void canCountFromIterable() {
        Assert.assertEquals(2l, Reductions.count(list));
    }

    @Test
    public void canCountAsIntegerFromIterator() {
        Assert.assertEquals(2l, Reductions.counti(list.iterator()));
    }

    @Test
    public void canReduceFromIterator() {
        Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(list.iterator(), new Count<Integer>(), 0l));
    }

    @Test
    public void canReduceFromIterable() {
        Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(list, new Count<Integer>(), 0l));
    }

    @Test
    public void canReduceFromArray() {
        Assert.assertEquals(Long.valueOf(2l), Reductions.reduce(array, new Count<Integer>(), 0l));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallReduceWithNullIterable() {
        final Iterable<Object> iterable = null;
        Reductions.reduce(iterable, new Count<Object>(), 0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountWithNullIterable() {
        final Iterable<Object> iterable = null;
        Reductions.count(iterable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallCountiWithNullIterable() {
        final Iterable<Object> iterable = null;
        Reductions.counti(iterable);
    }
}
