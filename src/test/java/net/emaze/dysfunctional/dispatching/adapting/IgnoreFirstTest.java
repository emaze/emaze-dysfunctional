package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreFirst<O, O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param = Box.empty();
        final Delegate<O, O> spy = Spies.spy1st(new Identity<O>(), param);
        final IgnoreFirst<O, O, O> adapted = new IgnoreFirst<O, O, O>(spy);
        adapted.perform(O.IGNORED, O.ONE);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
