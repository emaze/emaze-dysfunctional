package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryToUnaryFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new BinaryToUnaryFunction<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Function<Pair<O, O>, Pair<O, O>> function = new BinaryToUnaryFunction<>(new BinaryIdentity<O, O>());
        final Pair<O, O> expected = Pair.of(O.ONE, O.ANOTHER);
        final Pair<O, O> got = function.apply(expected);
        Assert.assertEquals(expected, got);
    }
}
