package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DoubleTryParserTest {

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Double> got = new DoubleTryParser().perform(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Double> got = new DoubleTryParser().perform("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Double> got = new DoubleTryParser().perform("1.");
        Assert.assertEquals(Maybe.just(1d), got);
    }
}