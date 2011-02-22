package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LongTryParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new LongTryParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new LongTryParser(Character.MIN_RADIX - 1);
    }

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Long> got = new LongTryParser(10).perform(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Long> got = new LongTryParser(10).perform("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Long> got = new LongTryParser(10).perform("1");
        Assert.assertEquals(Maybe.just(1l), got);
    }
}