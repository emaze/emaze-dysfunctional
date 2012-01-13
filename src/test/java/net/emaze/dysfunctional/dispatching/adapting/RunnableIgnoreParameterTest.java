package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class RunnableIgnoreParameterTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullRunnableYieldsException() {
        new RunnableIgnoreParameter<O>(null);
    }

    @Test
    public void callingAdapterCallsAdapted() {
        final Box<Boolean> param = Box.empty();
        new RunnableIgnoreParameter<O>(new Runnable() {

            @Override
            public void run() {
                param.setContent(Boolean.TRUE);
            }
        }).perform(O.IGNORED);
        Assert.assertEquals(Boolean.TRUE, param.getContent());
    }
}