package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

public class BooleanTryParserTest {

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Boolean> got = new BooleanTryParser().apply(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Boolean> got = new BooleanTryParser().apply("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new BooleanTryParser();
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidTrueStringYieldsTrue() {
        final Maybe<Boolean> got = new BooleanTryParser().apply("true");
        Assert.assertEquals(Maybe.just(true), got);
    }

    @Test
    public void parsingValidFalseStringYieldsFalse() {
        final Maybe<Boolean> got = new BooleanTryParser().apply("false");
        Assert.assertEquals(Maybe.just(false), got);
    }
}