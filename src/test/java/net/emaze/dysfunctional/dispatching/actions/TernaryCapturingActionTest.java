package net.emaze.dysfunctional.dispatching.actions;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new TernaryCapturingAction<O, O, O>(null);
    }

    @Test
    public void parametersAreCaptured() {
        final TernaryCapturingAction<O, O, O> capturer = new TernaryCapturingAction<O, O, O>(new TernaryNoop<O, O, O>());
        capturer.perform(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertTrue(
                O.ONE.equals(capturer.first.getContent())
                && O.ANOTHER.equals(capturer.second.getContent())
                && O.YET_ANOTHER.equals(capturer.third.getContent())
        );
    }
}
