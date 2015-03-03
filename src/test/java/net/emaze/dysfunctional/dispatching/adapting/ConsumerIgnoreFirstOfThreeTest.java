package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumerIgnoreFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ConsumerIgnoreFirstOfThree<O, O, O>(null);
    }

    @Test
    public void canIgnoreFirstParameter() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final BiConsumer<O, O> spy = Spies.spy(new BinaryNoop<O, O>(), param1, param2);        
        final ConsumerIgnoreFirstOfThree<O, O, O> adapted = new ConsumerIgnoreFirstOfThree<O, O, O>(spy);
        adapted.accept(O.IGNORED, O.ONE, O.ANOTHER);
        Assert.assertTrue(param1.getContent().equals(O.ONE) && param2.getContent().equals(O.ANOTHER));
    }
}