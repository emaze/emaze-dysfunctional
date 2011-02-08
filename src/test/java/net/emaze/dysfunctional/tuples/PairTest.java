package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PairTest {

    @Test
    public void formerParamIsFirst() {
        Pair<Integer,Integer> p = Pair.of(1,2);
        Assert.assertEquals(1, (int)p.first());
    }
    
    @Test
    public void latterParamIsSecond() {
        Pair<Integer,Integer> p = Pair.of(1,2);
        Assert.assertEquals(2, (int)p.second());
    }
    
    @Test
    public void pairsWithSameParamsAreEqual() {
        Pair<Integer,Integer> former = Pair.of(1,2);
        Pair<Integer,Integer> latter = Pair.of(1,2);
        Assert.assertTrue(former.equals(latter));
    }
    
    @Test
    public void pairsWithSameParamsHaveSameHashcode() {
        Pair<Integer,Integer> former = Pair.of(1,2);
        Pair<Integer,Integer> latter = Pair.of(1,2);
        Assert.assertEquals(former.hashCode(),latter.hashCode());
    }
    @Test
    public void toStringContainsElements() {
        String p = Pair.of(1,2).toString();
        Assert.assertTrue(p.contains("1") && p.contains("2"));
    }

    @Test
    public void pairAndObjectAreNotEquals() {
        Pair<Integer, Integer> p = Pair.of(1, 2);
        Assert.assertFalse(p.equals(new Object()));
    }
}