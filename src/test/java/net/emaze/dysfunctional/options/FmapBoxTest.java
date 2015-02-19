package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapBoxTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new FmapBox<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullBoxYieldsException() {
        new FmapBox<O, O>(UnaryOperator.identity()).apply(null);
    }

    @Test
    public void callingFmapOnNothingDoesntCallNestedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> delegate = Spies.monitor(UnaryOperator.identity(), calls);
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        lifted.apply(Box.<O>empty());
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnNothingYieldsNothing() {
        final Function<O, O> delegate = UnaryOperator.identity();
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        final Box<O> got = lifted.apply(Box.<O>empty());
        Assert.assertEquals(Box.<O>empty(), got);
    }

    @Test
    public void callingFmapOnJustSomethingYieldsJustSomething() {
        final Function<O, O> delegate = UnaryOperator.identity();
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        final Box<O> got = lifted.apply(Box.of(O.ONE));
        Assert.assertEquals(Box.of(O.ONE), got);
    }
}