package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class PureBoxTest {

    @Test
    public void pureYieldsBoxWithContent() {
        final O value = O.ONE;
        final Box<O> got = new PureBox<O>().apply(value);
        Assert.assertEquals(Box.of(value), got);
    }
}