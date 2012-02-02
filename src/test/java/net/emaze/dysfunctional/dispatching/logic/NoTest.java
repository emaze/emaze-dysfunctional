package net.emaze.dysfunctional.dispatching.logic;

import org.junit.Assert;
import org.junit.Test;

public class NoTest {

    @Test
    public void noJustReturnsFalse() {
        Assert.assertFalse(new No().state());
    }
}