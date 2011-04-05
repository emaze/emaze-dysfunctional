package net.emaze.dysfunctional.strings.lexcasts;

import org.junit.Assert;
import org.junit.Test;

public class BooleanParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new BooleanParser().perform(null);
    }

    @Test(expected = RuntimeException.class)
    public void parsingInvalidStringYieldsException() {
        new BooleanParser().perform("A");
    }

    @Test
    public void parsingValidTrueStringYieldsTrue() {
        final boolean got = new BooleanParser().perform("true");
        Assert.assertTrue(got);
    }

    @Test
    public void parsingValidFalseStringYieldsFalse() {
        final boolean got = new BooleanParser().perform("false");
        Assert.assertFalse(got);
    }
}