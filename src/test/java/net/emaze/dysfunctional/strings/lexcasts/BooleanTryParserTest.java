package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class BooleanTryParserTest {

    @Test
    public void parsingNullStringYieldsNothing() {
        final Optional<Boolean> got = new BooleanTryParser().apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Boolean> got = new BooleanTryParser().apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new BooleanTryParser();
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidTrueStringYieldsTrue() {
        final Optional<Boolean> got = new BooleanTryParser().apply("true");
        Assert.assertEquals(Optional.of(true), got);
    }

    @Test
    public void parsingValidFalseStringYieldsFalse() {
        final Optional<Boolean> got = new BooleanTryParser().apply("false");
        Assert.assertEquals(Optional.of(false), got);
    }
}