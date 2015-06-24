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
        Triple.of(O.ONE, O.ONE, O.ONE).map(null, Function.identity(), Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullSecondDelegateYieldsException() {
        Triple.of(O.ONE, O.ONE, O.ONE).map(Function.identity(), null, Function.identity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fmapWithNullThirdDelegateYieldsException() {
        Triple.of(O.ONE, O.ONE, O.ONE).map(Function.identity(), Function.identity(), null);
    }

    @Test
    public void firstDelegateOfFmapTransformsFirstType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).map(new ConstantFunction<O, O>(O.ANOTHER), Function.identity(), Function.identity());
        Assert.assertEquals(O.ANOTHER, mapped.first());
    }

    @Test
    public void secondDelegateOfFmapTransformsSecondType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).map(Function.identity(), new ConstantFunction<O, O>(O.ANOTHER), Function.identity());
        Assert.assertEquals(O.ANOTHER, mapped.second());
    }

    @Test
    public void thirdDelegateOfFmapTransformsThirdType() {
        final Triple<O, O, O> mapped = Triple.of(O.ONE, O.ONE, O.ONE).map(Function.identity(), Function.identity(), new ConstantFunction<O, O>(O.ANOTHER));
        Assert.assertEquals(O.ANOTHER, mapped.third());
    }

    @Test
    public void flippingInvertsValues() {
        final Triple<String, O, Integer> source = Triple.of("ONE", O.ONE, 1);
        final Triple<Integer, O, String> got = source.flip();
        Assert.assertEquals(Triple.of(1, O.ONE, "ONE"), got);
    }

    @Test
    public void rotatingTripleToLeftYieldsCorrectValues() {
        final Triple<String, Integer, O> source = Triple.of("ONE", 1, O.ONE);
        final Triple<Integer, O, String> got = source.rotateLeft();
        Assert.assertEquals(Triple.of(1, O.ONE, "ONE"), got);
    }

    @Test
    public void rotatingTripleToRightYieldsCorrectValues() {
        final Triple<String, Integer, O> source = Triple.of("ONE", 1, O.ONE);
        final Triple<O, String, Integer> got = source.rotateRight();
        Assert.assertEquals(Triple.of(O.ONE, "ONE", 1), got);
    }
}
