package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class TripleRotateLeftTest {

    @Test(expected = IllegalArgumentException.class)
    public void rotatingNullTripleYieldsException() {
        new TripleRotateLeft<O, O, O>().perform(null);
    }

    @Test
    public void rotatingTripleYieldsCorrectValues() {
        final Triple<String, Integer, O> source = Triple.of("ONE", 1, O.ONE);
        final Triple<Integer, O, String> got = new TripleRotateLeft<String, Integer, O>().perform(source);
        Assert.assertEquals(Triple.of(1, O.ONE, "ONE"), got);
    }
}