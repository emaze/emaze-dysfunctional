package net.emaze.dysfunctional.dispatching.delegates;

import java.util.concurrent.atomic.AtomicInteger;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class OnlyOnceProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new OnlyOnceProvider<O>(null);
    }

    @Test
    public void canObtainProvidedElement() {
        final Provider<O> provider = new OnlyOnceProvider<O>(new ConstantProvider<O>(O.ONE));
        final O got = provider.provide();
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProviderOnlyOnce() {
        final AtomicInteger count = new AtomicInteger(1);
        final Provider<Integer> provider = new OnlyOnceProvider<Integer>(new Provider<Integer>() {
            @Override
            public Integer provide() {
                return count.getAndIncrement();
            }
        });
        provider.provide();
        final int got = provider.provide();
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProviderOnlyOnceWhenInnerYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Provider<Integer> provider = new OnlyOnceProvider<Integer>(new Provider<Integer>() {
            @Override
            public Integer provide() {
                count.getAndIncrement();
                return null;
            }
        });
        provider.provide();
        provider.provide();
        Assert.assertEquals(1, count.get());
    }
}
