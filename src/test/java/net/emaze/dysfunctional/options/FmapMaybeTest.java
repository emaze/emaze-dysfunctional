package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import java.util.function.Function;
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
        new FmapMaybe<O, O>(new Identity<O>()).apply(null);
    }

    @Test
    public void callingFmapOnNothingDoesntCallNestedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Function<O, O> delegate = Spies.monitor(new Identity<O>(), calls);
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        lifted.apply(Maybe.<O>nothing());
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingFmapOnNothingYieldsNothing() {
        final Function<O, O> delegate = new Identity<O>();
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        final Maybe<O> got = lifted.apply(Maybe.<O>nothing());
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }

    @Test
    public void callingFmapOnJustSomethingYieldsJustSomething() {
        final Function<O, O> delegate = new Identity<O>();
        final FmapMaybe<O, O> lifted = new FmapMaybe<O, O>(delegate);
        final Maybe<O> got = lifted.apply(Maybe.just(O.ONE));
        Assert.assertEquals(Maybe.just(O.ONE), got);
    }
}