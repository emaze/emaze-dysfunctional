package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.adapting.ConcatenateString;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Flipper;
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
        final BinaryDelegate<String, String, String> flipped = new Flipper<String, String, String>(concat);
        Assert.assertEquals("latterformer", flipped.perform("former", "latter"));
    }
}
