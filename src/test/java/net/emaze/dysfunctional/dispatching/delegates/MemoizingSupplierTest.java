package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicInteger;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MemoizingSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new MemoizingSupplier<O>(null);
    }

    @Test
    public void canObtainProvidedElement() {
        final Supplier<O> supplier = new MemoizingSupplier<O>(new ConstantSupplier<O>(O.ONE));
        final O got = supplier.get();
        Assert.assertEquals(O.ONE, got);
    }

    @Test
    public void canInvokeProviderOnlyOnce() {
        final AtomicInteger count = new AtomicInteger(1);
        final Supplier<Integer> supplier = new MemoizingSupplier<Integer>(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return count.getAndIncrement();
            }
        });
        supplier.get();
        final int got = supplier.get();
        Assert.assertEquals(1, got);
    }

    @Test
    public void canInvokeProviderOnlyOnceWhenInnerYieldsNull() {
        final AtomicInteger count = new AtomicInteger(0);
        final Supplier<Integer> supplier = new MemoizingSupplier<Integer>(new Supplier<Integer>() {
            @Override
            public Integer get() {
                count.getAndIncrement();
                return null;
            }
        });
        supplier.get();
        supplier.get();
        Assert.assertEquals(1, count.get());
    }
}
