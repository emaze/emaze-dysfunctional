package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class SupplierToRunnableTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new SupplierToRunnable(null);
    }

    @Test
    public void callingRunnableCallsTheAdaptedProvider() {
        final Box<Boolean> called = Box.empty();
        new SupplierToRunnable(new Supplier<O>() {

            @Override
            public O get() {
                called.setContent(Boolean.TRUE);
                return O.IGNORED;
            }
        }).run();
        Assert.assertTrue(called.getContent());
    }
}