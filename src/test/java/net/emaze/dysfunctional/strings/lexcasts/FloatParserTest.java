package net.emaze.dysfunctional.strings.lexcasts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
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
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Delegate d = new FloatParser();
        d.perform(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Float got = new FloatParser().perform("1.");
        Assert.assertEquals(Float.valueOf(1), got);
    }
}