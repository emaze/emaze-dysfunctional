package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.CapturingDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DelegateToActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new DelegateToAction<O>(null);
    }

    @Test
    public void adapterCorrectlyPassesParamToAdapted() {
        final CapturingDelegate<O, O> capturer = new CapturingDelegate<O, O>(new Identity<O>());
        final Action<O> adapted = new DelegateToAction<O>(capturer);
        adapted.perform(O.ONE);
        Assert.assertEquals(O.ONE, capturer.first.getContent());
    }
}
