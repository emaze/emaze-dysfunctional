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
public class ConsumerIgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ConsumerIgnoreSecond<O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final Box<O> param = Box.empty();
        final Consumer<O> spy = Spies.spy(new Noop<O>(), param);
        final ConsumerIgnoreSecond<O, O> adapted = new ConsumerIgnoreSecond<O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
