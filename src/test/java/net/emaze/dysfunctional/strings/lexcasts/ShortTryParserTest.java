package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ShortTryParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new ShortTryParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new ShortTryParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new ShortTryParser(10);
        d.apply(new Object());
    }        

    @Test
    public void parsingNullStringYieldsNothing() {
        final Optional<Short> got = new ShortTryParser(10).apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Short> got = new ShortTryParser(10).apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Optional<Short> got = new ShortTryParser(10).apply("1");
        Assert.assertEquals(Optional.of((short)1), got);
    }
}