package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
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

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new LongTryParser(10);
        d.apply(new Object());
    }        
    
    @Test
    public void parsingNullStringYieldsNothing() {
        final Maybe<Long> got = new LongTryParser(10).apply(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Long> got = new LongTryParser(10).apply("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Long> got = new LongTryParser(10).apply("1");
        Assert.assertEquals(Maybe.just(1l), got);
    }
}