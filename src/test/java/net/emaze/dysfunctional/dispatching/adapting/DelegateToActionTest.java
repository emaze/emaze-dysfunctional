package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.Action;
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
public class DelegateToActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new DelegateToAction<O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesParamToAdapted() {
        final Box<O> param = Box.empty();
        final Delegate<O, O> spy = Spies.spy1st(new Identity<O>(), param);
        final Action<O> adapted = new DelegateToAction<O, O>(spy);
        adapted.perform(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
