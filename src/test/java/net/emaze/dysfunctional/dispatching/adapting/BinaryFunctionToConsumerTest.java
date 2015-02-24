package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryFunctionToConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new BinaryFunctionToConsumer<O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final BiFunction<O, O, O> spy = Spies.spy1st(new FirstParam<O, O>(), param1);
        final BiConsumer<O, O> adapted = new BinaryFunctionToConsumer<O, O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final BiFunction<O, O, O> spy = Spies.spy2nd(new FirstParam<O, O>(), param2);
        final BiConsumer<O, O> adapted = new BinaryFunctionToConsumer<O, O, O>(spy);
        adapted.accept(O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
