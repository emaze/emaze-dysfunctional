package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TripleFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void fetchingFromNullTripleYieldsException() {
        new TripleFirst<Object, Object, Object>().perform(null);
    }

    @Test
    public void fetchesTheFirstElement() {
        final Triple<Integer, Integer, Integer> pair = Triple.of(1, 2, 3);
        int got = new TripleFirst<Integer, Integer, Integer>().perform(pair);
        Assert.assertEquals(1, got);
    }
}
