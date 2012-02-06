package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TripleTest {

    @Test
    public void formerParamIsFirst() {
        Triple<Integer, Integer, Integer> t = Triple.of(1, 2, 3);
        Assert.assertEquals(1, (int) t.first());
    }

    @Test
    public void secondParamIsSecond() {
        Triple<Integer, Integer, Integer> t = Triple.of(1, 2, 3);
        Assert.assertEquals(2, (int) t.second());
    }

    @Test
    public void thirdParamIsThird() {
        Triple<Integer, Integer, Integer> t = Triple.of(1, 2, 3);
        Assert.assertEquals(3, (int) t.third());
    }

    @Test
    public void triplesWithSameParamsAreEqual() {
        Triple<Integer, Integer, Integer> former = Triple.of(1, 2, 3);
        Triple<Integer, Integer, Integer> latter = Triple.of(1, 2, 3);
        Assert.assertTrue(former.equals(latter));
    }

    @Test
    public void pairsWithSameParamsHaveSameHashcode() {
        Triple<Integer, Integer, Integer> former = Triple.of(1, 2, 3);
        Triple<Integer, Integer, Integer> latter = Triple.of(1, 2, 3);
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    @Test
    public void toStringContainsElements() {
        String p = Triple.of(1, 2, 3).toString();
        Assert.assertTrue(p.contains("1") && p.contains("2") && p.contains("3"));
    }

    @Test
    public void tripleAndObjectAreNotEquals() {
        Triple<Integer, Integer, Integer> t = Triple.of(1, 2, 3);
        Assert.assertFalse(t.equals(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullFirstDelegateYieldsException() {
        Triple.of(O.ONE, O.ONE, O.ONE).fmap(null, new Identity<O>(), new Identity<O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullSecondDelegateYieldsException() {
        Triple.of(O.ONE, O.ONE, O.ONE).fmap(new Identity<O>(), null, new Identity<O>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullThirdDelegateYieldsException() {
        Triple.of(O.ONE, O.ONE, O.ONE).fmap(new Identity<O>(), new Identity<O>(), null);
    }

    @Test
    public void firstDelegateOfFmapTransformsFirstType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).fmap(new ConstantDelegate<O, O>(O.ANOTHER), new Identity<O>(), new Identity<O>());
        Assert.assertEquals(O.ANOTHER, mapped.first());
    }

    @Test
    public void secondDelegateOfFmapTransformsSecondType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).fmap(new Identity<O>(), new ConstantDelegate<O, O>(O.ANOTHER), new Identity<O>());
        Assert.assertEquals(O.ANOTHER, mapped.second());
    }

    @Test
    public void thirdDelegateOfFmapTransformsThirdType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).fmap(new Identity<O>(), new Identity<O>(), new ConstantDelegate<O, O>(O.ANOTHER));
        Assert.assertEquals(O.ANOTHER, mapped.third());
    }
}
