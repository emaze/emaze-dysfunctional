package net.emaze.dysfunctional.options;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class LiftDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingLiftDelegateWithNullDelegateYieldsException() {
        new LiftDelegate<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingLiftDelegateWithNullDelegateYieldsException() {
        new LiftDelegate<O, O>(new Identity<O>()).perform(null);
    }

    @Test
    public void callingLiftDelegateOnNothingDoesntCallNestedDelegate() {
        final AtomicLong calls = new AtomicLong();
        final Delegate<O, O> delegate = Spies.monitor(new Identity<O>(), calls);
        final LiftDelegate<O, O> lifted = new LiftDelegate<O, O>(delegate);
        final Maybe<O> got = lifted.perform(Maybe.<O>nothing());
        Assert.assertEquals(0l, calls.get());
    }

    @Test
    public void callingLiftDelegateOnNothingYieldsNothing() {
        final Delegate<O, O> delegate = new Identity<O>();
        final LiftDelegate<O, O> lifted = new LiftDelegate<O, O>(delegate);
        final Maybe<O> got = lifted.perform(Maybe.<O>nothing());
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }

    @Test
    public void callingLiftDelegateOnJustSomethingYieldsJustSomething() {
        final Delegate<O, O> delegate = new Identity<O>();
        final LiftDelegate<O, O> lifted = new LiftDelegate<O, O>(delegate);
        final Maybe<O> got = lifted.perform(Maybe.just(O.ONE));
        Assert.assertEquals(Maybe.just(O.ONE), got);
    }
}