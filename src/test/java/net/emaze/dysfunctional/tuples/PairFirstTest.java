package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PairFirstTest {

    @Test(expected=IllegalArgumentException.class)
    public void fetchingFromNullPairYieldsException() {
        new PairFirst<Object, Object>().perform(null);
    }
    
    @Test
    public void fetchesTheFirstElement() {
        final Pair<Integer, Integer> pair = Pair.of(1, 2);
        int got = new PairFirst<Integer, Integer>().perform(pair);
        Assert.assertEquals(1,got);
    }

}