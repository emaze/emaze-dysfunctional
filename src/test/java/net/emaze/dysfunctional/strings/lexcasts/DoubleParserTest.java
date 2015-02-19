package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DoubleParserTest {

    @Test(expected=IllegalArgumentException.class)
    public void parsingNullStringYieldsException() {
        new DoubleParser().apply(null);
    }
    
    @Test(expected=NumberFormatException.class)
    public void parsingInvalidStringYieldsException() {
        new DoubleParser().apply("A");
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new DoubleParser();
        d.apply(new Object());
    }        
    
    @Test
    public void parsingValidStringYieldsValue() {
        final Double got = new DoubleParser().apply("1.");
        Assert.assertEquals(Double.valueOf(1), got);
    }
}