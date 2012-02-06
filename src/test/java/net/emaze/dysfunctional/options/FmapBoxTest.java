package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
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
        new FmapBox<O, O>(new Identity<O>()).perform(null);
    }

    @Test
    public void callingFmapOnNothingDoesntCallNestedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Delegate<O, O> delegate = Spies.monitor(new Identity<O>(), calls);
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        lifted.perform(Box.<O>empty());
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnNothingYieldsNothing() {
        final Delegate<O, O> delegate = new Identity<O>();
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        final Box<O> got = lifted.perform(Box.<O>empty());
        Assert.assertEquals(Box.<O>empty(), got);
    }

    @Test
    public void callingFmapOnJustSomethingYieldsJustSomething() {
        final Delegate<O, O> delegate = new Identity<O>();
        final FmapBox<O, O> lifted = new FmapBox<O, O>(delegate);
        final Box<O> got = lifted.perform(Box.of(O.ONE));
        Assert.assertEquals(Box.of(O.ONE), got);
    }
}