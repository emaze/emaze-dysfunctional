package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FloatParserTest {

    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new FloatParser().apply(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new FloatParser().apply("A");
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new FloatParser();
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Float got = new FloatParser().apply("1.");
        Assert.assertEquals(Float.valueOf(1), got);
    }
}