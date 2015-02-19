package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
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
        final Optional<Float> got = new FloatTryParser().apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Float> got = new FloatTryParser().apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Optional<Float> got = new FloatTryParser().apply("1.");
        Assert.assertEquals(Optional.of(1f), got);
    }
}