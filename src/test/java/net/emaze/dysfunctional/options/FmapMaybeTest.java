package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class FmapMaybeTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingFmapWithNullDelegateYieldsException() {
        new FmapMaybe<Object, Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingFmapWithNullDelegateYieldsException() {
        new FmapMaybe<O, O>(new Identity<O>()).perform(null);
    }

    @Test
    public void callingFmapOnNothingDoesntCallNestedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Delegate<O, O> delegate = Spies.monitor(new Identity<O>(), calls);
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        lifted.perform(Maybe.<O>nothing());
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnNothingYieldsNothing() {
        final Delegate<O, O> delegate = new Identity<O>();
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        final Maybe<O> got = lifted.perform(Maybe.<O>nothing());
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }

    @Test
    public void callingFmapOnJustSomethingYieldsJustSomething() {
        final Delegate<O, O> delegate = new Identity<O>();
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        final Maybe<O> got = lifted.perform(Maybe.just(O.ONE));
        Assert.assertEquals(Maybe.just(O.ONE), got);
    }
}