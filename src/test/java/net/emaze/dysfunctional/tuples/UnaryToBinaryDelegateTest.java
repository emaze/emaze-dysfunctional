package net.emaze.dysfunctional.tuples;

import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
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
        final Function<Pair<O, O>, Pair<O, O>> delegate = new Identity<Pair<O, O>>();
        final UnaryToBinaryDelegate<Pair<O, O>, O, O> adapted = new UnaryToBinaryDelegate<Pair<O, O>, O, O>(delegate);
        Pair<O, O> got = adapted.perform(O.ONE, O.ONE);
        Assert.assertEquals(Pair.of(O.ONE, O.ONE), got);
    }
}
