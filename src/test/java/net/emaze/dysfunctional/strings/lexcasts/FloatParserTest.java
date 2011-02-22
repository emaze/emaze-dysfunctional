package net.emaze.dysfunctional.strings.lexcasts;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FloatParserTest {

    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new FloatParser().perform(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new FloatParser().perform("A");
    }
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Float got = new FloatParser().perform("1.");
        Assert.assertEquals(Float.valueOf(1), got);
    }
}