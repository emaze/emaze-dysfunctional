package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ProviderToRunnableTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new ProviderToRunnable(null);
    }

    @Test
    public void callingRunnableCallsTheAdaptedProvider() {
        final Box<Boolean> called = Box.empty();
        new ProviderToRunnable(new Provider<O>() {

            @Override
            public O provide() {
                called.setContent(Boolean.TRUE);
                return O.IGNORED;
            }
        }).run();
        Assert.assertTrue(called.getContent());
    }
}