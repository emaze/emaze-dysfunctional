package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class UnaryToTernaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new UnaryToTernaryDelegate<O, O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Delegate<Triple<O, O, O>, Triple<O, O, O>> delegate = new Identity<Triple<O, O, O>>();
        final UnaryToTernaryDelegate<Triple<O, O, O>, O, O, O> adapted = new UnaryToTernaryDelegate<Triple<O, O, O>, O, O, O>(delegate);
        Triple<O, O, O> got = adapted.perform(O.ONE, O.ONE, O.ONE);
        Assert.assertEquals(Triple.of(O.ONE, O.ONE, O.ONE), got);
    }
}
