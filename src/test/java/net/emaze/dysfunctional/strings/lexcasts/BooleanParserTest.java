package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

public class BooleanParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new BooleanParser().apply(null);
    }

    @Test(expected = RuntimeException.class)
    public void parsingInvalidStringYieldsException() {
        new BooleanParser().apply("A");
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new BooleanParser();
        d.apply(new Object());
    }        

    @Test
    public void parsingValidTrueStringYieldsTrue() {
        final boolean got = new BooleanParser().apply("true");
        Assert.assertTrue(got);
    }

    @Test
    public void parsingValidFalseStringYieldsFalse() {
        final boolean got = new BooleanParser().apply("false");
        Assert.assertFalse(got);
    }    
}