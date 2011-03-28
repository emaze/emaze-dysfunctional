package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FirstParamOfThreeTest {

    @Test
    public void canCaptureFirstParam() {
        final O got = new FirstParamOfThree<O, O, O>().perform(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals(O.ONE, got);
    }
}
