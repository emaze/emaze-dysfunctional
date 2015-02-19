package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FlipPairTest {

    @Test(expected = IllegalArgumentException.class)
    public void flippingNullYieldsException() {
        new FlipPair<O, O>().apply(null);
    }

    @Test
    public void flippingInvertsValues() {
        final Pair<String, O> source = Pair.of("ONE", O.ONE);
        final Pair<O, String> got = new FlipPair<String, O>().apply(source);
        Assert.assertEquals(Pair.of(O.ONE, "ONE"), got);
    }
}