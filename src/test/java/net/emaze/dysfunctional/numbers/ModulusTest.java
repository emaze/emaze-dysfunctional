package net.emaze.dysfunctional.numbers;

import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class ModulusTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new Modulus<O, O, O>(null);
    }

    @Test
    public void canPerformModulusWithAPolicy() {
        BiFunction<O, O, O> multiply = new Modulus<O, O, O>(new ModulusPolicy<O, O, O>() {

            @Override
            public O modulus(O lhs, O rhs) {
                return O.YET_ANOTHER;
            }
        });
        Assert.assertEquals(O.YET_ANOTHER, multiply.apply(O.ONE, O.ANOTHER));
    }
}