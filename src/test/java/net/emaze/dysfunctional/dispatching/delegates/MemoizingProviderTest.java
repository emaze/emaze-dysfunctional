package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicInteger;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MemoizingProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new MemoizingProvider<O>(null);
    }

    @Test
    public void canObtainProvidedElement() {
        final Supplier<O> provider = new MemoizingProvider<O>(new ConstantProvider<O>(O.ONE));
        final O got = provider.get();
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProviderOnlyOnce() {
        final AtomicInteger count = new AtomicInteger(1);
        final Supplier<Integer> provider = new MemoizingProvider<Integer>(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return count.getAndIncrement();
            }
        });
        provider.get();
        final int got = provider.get();
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProviderOnlyOnceWhenInnerYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Supplier<Integer> provider = new MemoizingProvider<Integer>(new Supplier<Integer>() {
            @Override
            public Integer get() {
                count.getAndIncrement();
                return null;
            }
        });
        provider.get();
        provider.get();
        Assert.assertEquals(1, count.get());
    }
}
