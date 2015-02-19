package net.emaze.dysfunctional.numbers;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class SubtractTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new Subtract<O, O, O>(null);
    }

    @Test
    public void canSubtractWithAPolicy() {
        BiFunction<O, O, O> subtract = new Subtract<O, O, O>(new SubtractPolicy<O, O, O>() {

            @Override
            public O subtract(O lhs, O rhs) {
                return O.YET_ANOTHER;
            }
        });
        Assert.assertEquals(O.YET_ANOTHER, subtract.apply(O.ONE, O.ANOTHER));
    }
}