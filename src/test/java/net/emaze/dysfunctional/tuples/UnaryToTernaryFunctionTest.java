package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class UnaryToTernaryFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new UnaryToTernaryFunction<O, O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Function<Triple<O, O, O>, Triple<O, O, O>> function = Function.identity();
        final UnaryToTernaryFunction<O, O, O, Triple<O, O, O>> adapted = new UnaryToTernaryFunction<>(function);
        Triple<O, O, O> got = adapted.apply(O.ONE, O.ONE, O.ONE);
        Assert.assertEquals(Triple.of(O.ONE, O.ONE, O.ONE), got);
    }
}
