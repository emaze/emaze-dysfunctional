package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ByteParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new ByteParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new ByteParser(Character.MIN_RADIX - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new ByteParser(10).apply(null);
    }

    @Test(expected = NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new ByteParser(10).apply("A");
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new ByteParser(10);
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final byte got = new ByteParser(10).apply("1");
        Assert.assertEquals(1, got);
    }
}