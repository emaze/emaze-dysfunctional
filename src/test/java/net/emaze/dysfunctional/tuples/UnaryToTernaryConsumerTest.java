package net.emaze.dysfunctional.tuples;

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
public class UnaryToTernaryConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new UnaryToTernaryConsumer<O, O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Box<Triple<O, O, O>> box = Box.empty();
        final Consumer<Triple<O, O, O>> consumer = Spies.spy(new Noop<Triple<O, O, O>>(), box);
        final UnaryToTernaryConsumer<O, O, O> adapted = new UnaryToTernaryConsumer<O, O, O>(consumer);
        adapted.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(Triple.of(O.ONE, O.ANOTHER, O.YET_ANOTHER), box.getContent());
    }
}
