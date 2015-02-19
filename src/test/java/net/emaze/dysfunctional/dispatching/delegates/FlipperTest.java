package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.adapting.ConcatenateString;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FlipperTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingFlipperWithNullDelegateYieldsException() {
        new Flipper<String, String, String>(null);
    }

    @Test
    public void decoratingWithFlipperFlipsParams() {
        final ConcatenateString concat = new ConcatenateString();
        final BiFunction<String, String, String> flipped = new Flipper<String, String, String>(concat);
        Assert.assertEquals("latterformer", flipped.apply("former", "latter"));
    }
}
