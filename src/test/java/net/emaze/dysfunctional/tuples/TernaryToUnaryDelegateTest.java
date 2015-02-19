package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
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
        final Function<Triple<O, O, O>, Triple<O, O, O>> delegate = new TernaryToUnaryDelegate<>(new TernaryIdentity<>());
        final Triple<O, O, O> expected = Triple.of(O.ONE, O.ANOTHER, O.ANOTHER);
        final Triple<O, O, O> got = delegate.apply(expected);
        Assert.assertEquals(expected, got);
    }
}
