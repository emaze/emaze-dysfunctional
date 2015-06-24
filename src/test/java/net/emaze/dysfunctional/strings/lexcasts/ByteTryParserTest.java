package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
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
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new ByteTryParser(10);
        d.apply(new Object());
    }        

    @Test
    public void parsingNullStringYieldsNothing() {
        final Optional<Byte> got = new ByteTryParser(10).apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Byte> got = new ByteTryParser(10).apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Optional<Byte> got = new ByteTryParser(10).apply("1");
        Assert.assertEquals(Optional.of((byte) 1), got);
    }
}