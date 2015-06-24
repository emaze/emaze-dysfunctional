package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DoubleTryParserTest {
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new DoubleTryParser();
        d.apply(new Object());
    }        

    @Test
    public void parsingNullStringYieldsNothing() {
        final Optional<Double> got = new DoubleTryParser().apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Double> got = new DoubleTryParser().apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Optional<Double> got = new DoubleTryParser().apply("1.");
        Assert.assertEquals(Optional.of(1d), got);
    }
}