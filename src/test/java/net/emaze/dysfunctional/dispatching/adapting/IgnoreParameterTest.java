package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class IgnoreParameterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new IgnoreParameter<O, O>(null);
    }
    
    @Test
    public void callingTheAdapterYieldsAdaptedResult(){
        final O got = new IgnoreParameter<O, O>(new ConstantProvider<O>(O.ONE)).apply(O.ANOTHER);
        Assert.assertEquals(O.ONE, got);
    }
}