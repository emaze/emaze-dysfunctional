package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.ConstantFunction;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PairTest {

    @Test
    public void formerParamIsFirst() {
        Pair<Integer, Integer> p = Pair.of(1, 2);
        Assert.assertEquals(1, (int) p.first());
    }

    @Test
    public void latterParamIsSecond() {
        Pair<Integer, Integer> p = Pair.of(1, 2);
        Assert.assertEquals(2, (int) p.second());
    }

    @Test
    public void pairsWithSameParamsAreEqual() {
        Pair<Integer, Integer> former = Pair.of(1, 2);
        Pair<Integer, Integer> latter = Pair.of(1, 2);
        Assert.assertTrue(former.equals(latter));
    }

    @Test
    public void pairsWithSameParamsHaveSameHashcode() {
        Pair<Integer, Integer> former = Pair.of(1, 2);
        Pair<Integer, Integer> latter = Pair.of(1, 2);
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    @Test
    public void toStringContainsElements() {
        String p = Pair.of(1, 2).toString();
        Assert.assertTrue(p.contains("1") && p.contains("2"));
    }

    @Test
    public void pairAndObjectAreNotEquals() {
        Pair<Integer, Integer> p = Pair.of(1, 2);
        Assert.assertFalse(p.equals(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullFirstDelegateYieldsException() {
        Pair.of(O.ONE, O.ONE).map(null, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullSecondDelegateYieldsException() {
        Pair.of(O.ONE, O.ONE).map(Function.identity(), null);
    }

    @Test
    public void firstDelegateOfFmapTransformsFirstType() {
        final Pair<O, O> mapped = Pair.of(O.ONE, O.ONE).map(new ConstantFunction<O, O>(O.ANOTHER), Function.identity());
        Assert.assertEquals(O.ANOTHER, mapped.first());
    }

    @Test
    public void secondDelegateOfFmapTransformsSecondType() {
        final Pair<O, O> mapped = Pair.of(O.ONE, O.ONE).map(Function.identity(), new ConstantFunction<O, O>(O.ANOTHER));
        Assert.assertEquals(O.ANOTHER, mapped.second());
    }

    @Test
    public void flippingInvertsValues() {
        final Pair<String, O> source = Pair.of("ONE", O.ONE);
        final Pair<O, String> got = source.flip();
        Assert.assertEquals(Pair.of(O.ONE, "ONE"), got);
    }
}
