package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FlipTripleTest {

    @Test(expected = IllegalArgumentException.class)
    public void flippingNullYieldsException() {
        new FlipTriple<O, O, O>().apply(null);
    }

    @Test
    public void flippingInvertsValues() {
        final Triple<String, O, Integer> source = Triple.of("ONE", O.ONE, 1);
        final Triple<Integer, O, String> got = new FlipTriple<String, O, Integer>().apply(source);
        Assert.assertEquals(Triple.of(1, O.ONE, "ONE"), got);
    }
}