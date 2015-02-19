package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConstantDelegateTest {

    @Test
    public void constantDelegateAlwaysYieldsTheSameValue() {
        Function<Integer, O> delegate = new ConstantDelegate<>(O.ONE);
        Assert.assertEquals(O.ONE, delegate.apply(123));
    }
}