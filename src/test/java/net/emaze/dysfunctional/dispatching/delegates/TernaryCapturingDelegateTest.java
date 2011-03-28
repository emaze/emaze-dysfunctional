package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new TernaryCapturingDelegate<O, O, O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final TernaryCapturingDelegate<O, O, O, O> capturer = new TernaryCapturingDelegate<O, O, O, O>(new ThirdParam<O, O, O>());
        capturer.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertTrue(
                O.ONE.equals(capturer.first.getContent())
                && O.ANOTHER.equals(capturer.second.getContent())
                && O.YET_ANOTHER.equals(capturer.third.getContent()));
    }
}
