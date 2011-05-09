package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
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
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Delegate d = new DoubleParser();
        d.perform(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Double got = new DoubleParser().perform("1.");
        Assert.assertEquals(Double.valueOf(1), got);
    }
}