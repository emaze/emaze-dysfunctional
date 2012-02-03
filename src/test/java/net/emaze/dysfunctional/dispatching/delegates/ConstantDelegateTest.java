package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConstantDelegateTest {

    @Test
    public void constantDelegateAlwaysYieldsTheSameValue() {
        Delegate<O, Integer> delegate = new ConstantDelegate<O, Integer>(O.ONE);
        Assert.assertEquals(O.ONE, delegate.perform(123));
    }
}