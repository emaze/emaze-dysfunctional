package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.emaze.dysfunctional.Maps;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MemoizingDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new MemoizingDelegate<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullCacheYieldsException() {
        new MemoizingDelegate<O, O>(new ConstantDelegate<O, O>(O.ONE), null);
    }

    @Test
    public void canObtainProxiedElement() {
        final Delegate<O, O> delegate = new MemoizingDelegate<O, O>(new ConstantDelegate<O, O>(O.ONE));
        final O got = delegate.perform(O.IGNORED);
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceForTheSameParameter() {
        final AtomicInteger count = new AtomicInteger(1);
        final Delegate<Integer, O> delegate = new MemoizingDelegate<Integer, O>(new Delegate<Integer, O>() {
            @Override
            public Integer perform(O o) {
                return count.getAndIncrement();
            }
        });
        delegate.perform(O.ONE);
        final int got = delegate.perform(O.ONE);
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceWhenYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Delegate<Integer, O> delegate = new MemoizingDelegate<Integer, O>(new Delegate<Integer, O>() {
            @Override
            public Integer perform(O o) {
                count.getAndIncrement();
                return null;
            }
        });
        delegate.perform(O.ONE);
        delegate.perform(O.ONE);
        Assert.assertEquals(1, count.get());
    }

    @Test
    public void canInjectCache() {
        final Map<O, O> cache = Maps.<O, O>builder().add(O.ONE, O.ANOTHER).toMap();
        final Delegate<O, O> delegate = new MemoizingDelegate<O, O>(new ConstantDelegate<O, O>(O.ONE), cache);
        final O got = delegate.perform(O.ONE);
        Assert.assertEquals(O.ANOTHER, got);
    }
}
