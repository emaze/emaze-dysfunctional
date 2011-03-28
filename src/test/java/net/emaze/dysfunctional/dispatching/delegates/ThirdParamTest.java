package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ThirdParamTest {

    @Test
    public void canCaptureThirdParam() {
        final O got = new ThirdParam<O, O, O>().perform(O.IGNORED, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, got);
    }

}