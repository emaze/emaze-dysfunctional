package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryDelegateToBinaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryDelegateToBinaryAction<O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>());
        final BinaryAction<O, O> adapted = new BinaryDelegateToBinaryAction<O, O>(capturer);
        adapted.perform(O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, capturer.first.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final BinaryCapturingDelegate<O, O, O> capturer = new BinaryCapturingDelegate<O, O, O>(new FirstParam<O, O>());
        final BinaryAction<O, O> adapted = new BinaryDelegateToBinaryAction<O, O>(capturer);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, capturer.second.getContent());
    }
}
