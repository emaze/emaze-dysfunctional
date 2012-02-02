package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingPropositionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingSpyWithNullAdaptedYieldsException() {
        new CapturingProposition(null, Box.<Boolean>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingSpyWithNullBoxYieldsException() {
        new CapturingProposition(new Yes(), null);
    }

    @Test
    public void callingCapturesTheResult() {
        final Box<Boolean> result = Box.empty();
        final Proposition spy = new CapturingProposition(new Yes(), result);
        spy.state();
        Assert.assertEquals(Box.of(true), result);
    }
}
