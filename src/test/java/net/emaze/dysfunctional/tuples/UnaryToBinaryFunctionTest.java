package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class UnaryToBinaryFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new UnaryToBinaryFunction<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Function<Pair<O, O>, Pair<O, O>> function = Function.identity();
        final UnaryToBinaryFunction<O, O, Pair<O, O>> adapted = new UnaryToBinaryFunction<>(function);
        Pair<O, O> got = adapted.apply(O.ONE, O.ONE);
        Assert.assertEquals(Pair.of(O.ONE, O.ONE), got);
    }
}
