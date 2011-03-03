package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PairSecondTest {

    @Test(expected=IllegalArgumentException.class)
    public void fetchingFromNullPairYieldsException() {
        new PairSecond<Object, Object>().perform(null);
    }
    
    @Test
    public void fetchesTheSecondElement() {
        final Pair<Integer, Integer> pair = Pair.of(1, 2);
        int got = new PairSecond<Integer, Integer>().perform(pair);
        Assert.assertEquals(2,got);
    }

}