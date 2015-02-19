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
public class UnaryToBinaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new UnaryToBinaryAction<O, O>(null);
    }

    @Test
    public void canAdapt() {
        final Box<Pair<O,O>> box = Box.empty();
        final Consumer<Pair<O, O>> action = Spies.spy(new Noop<Pair<O, O>>(), box);
        final UnaryToBinaryAction<O, O> adapted = new UnaryToBinaryAction<O, O>(action);
        adapted.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(Pair.of(O.ONE, O.ANOTHER), box.getContent());
    }
}
