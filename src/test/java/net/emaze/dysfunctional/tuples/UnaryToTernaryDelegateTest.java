package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import java.util.function.UnaryOperator;
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
        final Function<Triple<O, O, O>, Triple<O, O, O>> delegate = UnaryOperator.identity();
        final UnaryToTernaryDelegate<O, O, O, Triple<O, O, O>> adapted = new UnaryToTernaryDelegate<>(delegate);
        Triple<O, O, O> got = adapted.apply(O.ONE, O.ONE, O.ONE);
        Assert.assertEquals(Triple.of(O.ONE, O.ONE, O.ONE), got);
    }
}
