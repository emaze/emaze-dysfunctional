package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IntegerParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new IntegerParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new IntegerParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new IntegerParser(10).apply(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new IntegerParser(10).apply("A");
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new IntegerParser(10);
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final int got = new IntegerParser(10).apply("1");
        Assert.assertEquals(1, got);
    }
}