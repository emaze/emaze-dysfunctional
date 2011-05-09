package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
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
        new ShortParser(10).perform(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Delegate d = new ShortParser(10);
        d.perform(new Object());
    }        

    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new ShortParser(10).perform("A");
    }
    
    @Test
    public void parsingValidStringYieldsValue() {
        final short got = new ShortParser(10).perform("1");
        Assert.assertEquals(1, got);
    }
}