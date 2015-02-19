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
public class UnaryToBinaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new UnaryToBinaryDelegate<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Function<Pair<O, O>, Pair<O, O>> delegate = UnaryOperator.identity();
        final UnaryToBinaryDelegate<O, O, Pair<O, O>> adapted = new UnaryToBinaryDelegate<>(delegate);
        Pair<O, O> got = adapted.apply(O.ONE, O.ONE);
        Assert.assertEquals(Pair.of(O.ONE, O.ONE), got);
    }
}
