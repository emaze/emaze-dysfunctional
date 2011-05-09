package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
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
        Delegate d = new FloatTryParser();
        d.perform(new Object());
    }

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Float> got = new FloatTryParser().perform(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Float> got = new FloatTryParser().perform("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Float> got = new FloatTryParser().perform("1.");
        Assert.assertEquals(Maybe.just(1f), got);
    }
}