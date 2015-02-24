package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Function;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ConstantFunctionTest {

    @Test
    public void constantDelegateAlwaysYieldsTheSameValue() {
        Function<Integer, O> function = new ConstantFunction<>(O.ONE);
        Assert.assertEquals(O.ONE, function.apply(123));
    }
}