package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TripleSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void fetchingFromNullTripleYieldsException() {
        new TripleSecond<Object, Object, Object>().apply(null);
    }

    @Test
    public void fetchesTheSecondElement() {
        final Triple<Integer, Integer, Integer> pair = Triple.of(1, 2, 3);
        int got = new TripleSecond<Integer, Integer, Integer>().apply(pair);
        Assert.assertEquals(2, got);
    }
}
