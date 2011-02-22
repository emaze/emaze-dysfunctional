package net.emaze.dysfunctional.strings.lexcasts;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DoubleParserTest {

    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new DoubleParser().perform(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new DoubleParser().perform("A");
    }
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Double got = new DoubleParser().perform("1.");
        Assert.assertEquals(Double.valueOf(1), got);
    }
}