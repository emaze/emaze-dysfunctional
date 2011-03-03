package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TripleThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void fetchingFromNullTripleYieldsException() {
        new TripleThird<Object, Object, Object>().perform(null);
    }

    @Test
    public void fetchesTheThirdElement() {
        final Triple<Integer, Integer, Integer> pair = Triple.of(1, 2, 3);
        int got = new TripleThird<Integer, Integer, Integer>().perform(pair);
        Assert.assertEquals(3, got);
    }
}
