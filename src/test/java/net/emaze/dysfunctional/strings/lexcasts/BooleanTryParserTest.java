package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

public class BooleanTryParserTest {

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Boolean> got = new BooleanTryParser().perform(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Boolean> got = new BooleanTryParser().perform("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidTrueStringYieldsTrue() {
        final Maybe<Boolean> got = new BooleanTryParser().perform("true");
        Assert.assertEquals(Maybe.just(true), got);
    }

    @Test
    public void parsingValidFalseStringYieldsFalse() {
        final Maybe<Boolean> got = new BooleanTryParser().perform("false");
        Assert.assertEquals(Maybe.just(false), got);
    }
}