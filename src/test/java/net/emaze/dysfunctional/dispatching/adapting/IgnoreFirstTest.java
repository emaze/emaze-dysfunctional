package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreFirst<O, O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param = Box.empty();
        final Function<O, O> spy = Spies.spy1st(UnaryOperator.identity(), param);
        final IgnoreFirst<O, O, O> adapted = new IgnoreFirst<O, O, O>(spy);
        adapted.apply(O.IGNORED, O.ONE);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
