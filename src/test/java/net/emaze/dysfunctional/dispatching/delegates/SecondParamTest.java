package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SecondParamTest {

    @Test
    public void canCaptureSecondParam() {
        final O got = new SecondParam<O, O>().perform(O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, got);
    }

}