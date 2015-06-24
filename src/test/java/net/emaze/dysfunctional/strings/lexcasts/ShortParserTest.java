package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ShortParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new ShortParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new ShortParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new ShortParser(10).apply(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new ShortParser(10);
        d.apply(new Object());
    }        

    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new ShortParser(10).apply("A");
    }
    
    @Test
    public void parsingValidStringYieldsValue() {
        final short got = new ShortParser(10).apply("1");
        Assert.assertEquals(1, got);
    }
}