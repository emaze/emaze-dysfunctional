package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

public class RunnableToSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullRunnableYieldsException() {
        new RunnableToSupplier(null);
    }

    @Test
    public void callingTheRunnableCallsTheProvider() {
        final Box<Boolean> called = Box.empty();

        new RunnableToSupplier(new Runnable() {

            @Override
            public void run() {
                called.setContent(Boolean.TRUE);
            }
        }).get();
        Assert.assertTrue(called.getContent());
    }
}