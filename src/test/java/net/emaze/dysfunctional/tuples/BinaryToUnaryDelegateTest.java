package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryToUnaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new BinaryToUnaryDelegate<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Delegate<Pair<O, O>, Pair<O, O>> delegate = new BinaryToUnaryDelegate<Pair<O, O>, O, O>(new BinaryIdentity<O, O>());
        final Pair<O, O> expected = Pair.of(O.ONE, O.ANOTHER);
        final Pair<O, O> got = delegate.perform(expected);
        Assert.assertEquals(expected, got);
    }
}
