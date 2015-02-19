package net.emaze.dysfunctional.numbers;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class MultiplyTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new Multiply<O, O, O>(null);
    }

    @Test
    public void canMultiplyWithAPolicy() {
        BiFunction<O, O, O> multiply = new Multiply<O, O, O>(new MultiplyPolicy<O, O, O>() {

            @Override
            public O multiply(O lhs, O rhs) {
                return O.YET_ANOTHER;
            }
        });
        Assert.assertEquals(O.YET_ANOTHER, multiply.apply(O.ONE, O.ANOTHER));
    }
}