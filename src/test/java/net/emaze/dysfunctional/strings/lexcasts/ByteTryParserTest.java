package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ByteTryParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new ByteTryParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new ByteTryParser(Character.MIN_RADIX - 1);
    }

    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Byte> got = new ByteTryParser(10).perform(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Byte> got = new ByteTryParser(10).perform("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Byte> got = new ByteTryParser(10).perform("1");
        Assert.assertEquals(Maybe.just((byte) 1), got);
    }
}