package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FloatTryParserTest {

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new FloatTryParser();
        d.apply(new Object());
    }

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Float> got = new FloatTryParser().apply(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Float> got = new FloatTryParser().apply("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Float> got = new FloatTryParser().apply("1.");
        Assert.assertEquals(Maybe.just(1f), got);
    }
}