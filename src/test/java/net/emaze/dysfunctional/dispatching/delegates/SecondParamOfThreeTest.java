package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SecondParamOfThreeTest {

    @Test
    public void canCaptureSecondParam() {
        final O got = new SecondParamOfThree<O, O, O>().perform(O.IGNORED, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, got);
    }
}
