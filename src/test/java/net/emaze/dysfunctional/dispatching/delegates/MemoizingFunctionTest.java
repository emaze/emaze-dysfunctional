package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.emaze.dysfunctional.Maps;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MemoizingFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new MemoizingFunction<O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullCacheYieldsException() {
        new MemoizingFunction<O, O>(new ConstantFunction<O, O>(O.ONE), null);
    }

    @Test
    public void canObtainProxiedElement() {
        final Function<O, O> function = new MemoizingFunction<>(new ConstantFunction<>(O.ONE));
        final O got = function.apply(O.IGNORED);
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceForTheSameParameter() {
        final AtomicInteger count = new AtomicInteger(1);
        final Function<O, Integer> function = new MemoizingFunction<>(o -> count.getAndIncrement());
        function.apply(O.ONE);
        final int got = function.apply(O.ONE);
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProxiedDelegateOnlyOnceWhenYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Function<O, Integer> function = new MemoizingFunction<>(o -> {
            count.getAndIncrement();
            return null;
        });
        function.apply(O.ONE);
        function.apply(O.ONE);
        Assert.assertEquals(1, count.get());
    }

    @Test
    public void canInjectCache() {
        final Map<O, O> cache = Maps.<O, O>builder().add(O.ONE, O.ANOTHER).toMap();
        final Function<O, O> function = new MemoizingFunction<>(new ConstantFunction<>(O.ONE), cache);
        final O got = function.apply(O.ONE);
        Assert.assertEquals(O.ANOTHER, got);
    }
}
