package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import net.emaze.dysfunctional.options.Maybe;
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
        final Maybe<Double> got = new DoubleTryParser().apply(null);
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Maybe<Double> got = new DoubleTryParser().apply("A");
        Assert.assertFalse(got.hasValue());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Maybe<Double> got = new DoubleTryParser().apply("1.");
        Assert.assertEquals(Maybe.just(1d), got);
    }
}