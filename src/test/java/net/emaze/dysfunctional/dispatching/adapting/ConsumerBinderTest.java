package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConsumerBinderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new ConsumerBinder<O>(null, O.ONE);
    }

    @Test
    public void callingTheAdapterCallsAdaptedWithBoundParameter() {
        final Box<O> param = Box.empty();
        final Consumer<O> spy = Spies.spy1st(new Noop<O>(), param);
        final Runnable runnable = new ConsumerBinder<O>(spy, O.ONE);
        runnable.run();
        Assert.assertEquals(O.ONE, param.getContent());
    }
}