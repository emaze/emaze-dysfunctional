package net.emaze.dysfunctional.tuples;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TripleTest {

    @Test
    public void formerParamIsFirst() {
        Triple<Integer, Integer, Integer> t = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertEquals(1, (int) t.first());
    }

    @Test
    public void secondParamIsSecond() {
        Triple<Integer, Integer, Integer> t = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertEquals(2, (int) t.second());
    }

    @Test
    public void thirdParamIsThird() {
        Triple<Integer, Integer, Integer> t = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertEquals(3, (int) t.third());
    }

    @Test
    public void triplesWithSameParamsAreEqual() {
        Triple<Integer, Integer, Integer> former = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Triple<Integer, Integer, Integer> latter = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertTrue(former.equals(latter));
    }

    @Test
    public void pairsWithSameParamsHaveSameHashcode() {
        Triple<Integer, Integer, Integer> former = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Triple<Integer, Integer, Integer> latter = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    @Test
    public void toStringContainsElements() {
        String p = new Triple<Integer, Integer, Integer>(1, 2, 3).toString();
        Assert.assertTrue(p.contains("1") && p.contains("2") && p.contains("3"));
    }

    @Test
    public void tripleAndObjectAreNotEquals() {
        Triple<Integer, Integer, Integer> t = new Triple<Integer, Integer, Integer>(1, 2, 3);
        Assert.assertFalse(t.equals(new Object()));
    }
}
