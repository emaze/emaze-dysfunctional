package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryToUnaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TernaryToUnaryDelegate<O, O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Delegate<Triple<O, O, O>, Triple<O, O, O>> delegate = new TernaryToUnaryDelegate<Triple<O, O, O>, O, O, O>(new TernaryIdentity<O, O, O>());
        final Triple<O, O, O> expected = Triple.of(O.ONE, O.ANOTHER, O.ANOTHER);
        final Triple<O, O, O> got = delegate.perform(expected);
        Assert.assertEquals(expected, got);
    }
}
