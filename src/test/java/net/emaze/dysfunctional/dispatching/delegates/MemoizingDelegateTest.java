package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
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
        final Function<O, O> delegate = new MemoizingDelegate<>(new ConstantDelegate<>(O.ONE));
        final O got = delegate.apply(O.IGNORED);
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceForTheSameParameter() {
        final AtomicInteger count = new AtomicInteger(1);
        final Function<O, Integer> delegate = new MemoizingDelegate<>(o -> count.getAndIncrement());
        delegate.apply(O.ONE);
        final int got = delegate.apply(O.ONE);
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceWhenYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Function<O, Integer> delegate = new MemoizingDelegate<>(o -> {
            count.getAndIncrement();
            return null;
        });
        delegate.apply(O.ONE);
        delegate.apply(O.ONE);
        Assert.assertEquals(1, count.get());
    }

    @Test
    public void canInjectCache() {
        final Map<O, O> cache = Maps.<O, O>builder().add(O.ONE, O.ANOTHER).toMap();
        final Function<O, O> delegate = new MemoizingDelegate<>(new ConstantDelegate<>(O.ONE), cache);
        final O got = delegate.apply(O.ONE);
        Assert.assertEquals(O.ANOTHER, got);
    }
}
