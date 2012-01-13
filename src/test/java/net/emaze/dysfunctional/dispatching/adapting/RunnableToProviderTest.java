package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.options.Box;
import org.junit.Assert;
import org.junit.Test;

public class RunnableToProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullRunnableYieldsException() {
        new RunnableToProvider(null);
    }

    @Test
    public void callingTheRunnableCallsTheProvider() {
        final Box<Boolean> called = Box.empty();

        new RunnableToProvider(new Runnable() {

            @Override
            public void run() {
                called.setContent(Boolean.TRUE);
            }
        }).provide();
        Assert.assertTrue(called.getContent());
    }
}