package net.emaze.dysfunctional.strings.lexcasts;

import java.util.function.Function;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IntegerTryParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void excedingMaxRadixYieldsException() {
        new IntegerTryParser(Character.MAX_RADIX + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinRadixYieldsException() {
        new IntegerTryParser(Character.MIN_RADIX - 1);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Function d = new IntegerTryParser(10);
        d.apply(new Object());
    }        

    @Test
    public void parsingNullStringYieldsNothing() {
        final Optional<Integer> got = new IntegerTryParser(10).apply(null);
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingInvalidStringYieldsNothing() {
        final Optional<Integer> got = new IntegerTryParser(10).apply("A");
        Assert.assertFalse(got.isPresent());
    }

    @Test
    public void parsingValidStringYieldsJustValue() {
        final Optional<Integer> got = new IntegerTryParser(10).apply("1");
        Assert.assertEquals(Optional.of(1), got);
    }
}