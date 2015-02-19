package net.emaze.dysfunctional.dispatching.logic;

import org.junit.Assert;
import org.junit.Test;

public class YesTest {

    @Test
    public void yesJustReturnsTrue() {
        Assert.assertTrue(new Yes().getAsBoolean());
    }
}