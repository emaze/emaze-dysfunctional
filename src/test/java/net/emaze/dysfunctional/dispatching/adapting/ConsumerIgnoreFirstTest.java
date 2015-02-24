package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumerIgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ConsumerIgnoreFirst<O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param = Box.empty();
        final Consumer<O> spy = Spies.spy(new Noop<O>(), param);
        final ConsumerIgnoreFirst<O, O> adapted = new ConsumerIgnoreFirst<O, O>(spy);
        adapted.accept(O.IGNORED, O.ONE);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
