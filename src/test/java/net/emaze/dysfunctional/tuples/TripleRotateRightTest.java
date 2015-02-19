package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class TripleRotateRightTest {

    @Test(expected = IllegalArgumentException.class)
    public void rotatingNullTripleYieldsException() {
        new TripleRotateRight<O, O, O>().apply(null);
    }

    @Test
    public void rotatingTripleYieldsCorrectValues() {
        final Triple<String, Integer, O> source = Triple.of("ONE", 1, O.ONE);
        final Triple<O, String, Integer> got = new TripleRotateRight<String, Integer, O>().apply(source);
        Assert.assertEquals(Triple.of(O.ONE, "ONE", 1), got);
    }
}