package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreSecond<O, O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final Box<O> param = Box.empty();
        final Delegate<O, O> spy = Spies.spy1st(new Identity<O>(), param);        
        final IgnoreSecond<O, O, O> adapted = new IgnoreSecond<O, O, O>(spy);
        adapted.perform(O.ONE, O.IGNORED);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
