package net.emaze.dysfunctional.dispatching.adapting;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryConsumerToFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new BinaryConsumerToFunction<O, O>(null);
    }

    @Test
    public void callingAdapterCallsAdapted() {
        final AtomicLong calls = new AtomicLong();
        final BiConsumer<O, O> adaptee = Spies.monitor(new BinaryNoop<O, O>(), calls);
        final BiFunction<O, O, Void> del = new BinaryConsumerToFunction<>(adaptee);
        del.apply(O.ONE, O.ANOTHER);

        Assert.assertEquals(1l, calls.get());
    }

    @Test
    public void callingAdapterPassesFirstArgument() {
        final Box<O> param1 = Box.empty();
        final BiConsumer<O, O> adaptee = Spies.spy1st(new BinaryNoop<O, O>(), param1);
        final BiFunction<O, O, Void> del = new BinaryConsumerToFunction<>(adaptee);
        del.apply(O.ONE, O.ANOTHER);

        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void callingAdapterPassesSecondArgument() {
        final Box<O> param2 = Box.empty();
        final BiConsumer<O, O> adaptee = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
        final BiFunction<O, O, Void> del = new BinaryConsumerToFunction<>(adaptee);
        del.apply(O.ONE, O.ANOTHER);

        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
