package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryFunctionToConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new TernaryFunctionToConsumer<O, O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        final Box<O> param1 = Box.empty();
        final TriFunction<O, O, O, O> spy = Spies.spy1st(new FirstParamOfThree<O, O, O>(), param1);
        final TriConsumer<O, O, O> adapted = new TernaryFunctionToConsumer<O, O, O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final TriFunction<O, O, O, O> spy = Spies.spy2nd(new FirstParamOfThree<O, O, O>(), param2);
        final TriConsumer<O, O, O> adapted = new TernaryFunctionToConsumer<O, O, O, O>(spy);
        adapted.accept(O.IGNORED, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param2.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final Box<O> param3 = Box.empty();
        final TriFunction<O, O, O, O> spy = Spies.spy3rd(new FirstParamOfThree<O, O, O>(), param3);
        final TriConsumer<O, O, O> adapted = new TernaryFunctionToConsumer<O, O, O, O>(spy);
        adapted.accept(O.IGNORED, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param3.getContent());
    }
}
