package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LongParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new LongParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new LongParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new LongParser(10).apply(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new LongParser(10).apply("A");
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new LongParser(10);
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final long got = new LongParser(10).apply("1");
        Assert.assertEquals(1, got);
    }
}