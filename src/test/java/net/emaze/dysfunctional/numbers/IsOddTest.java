package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IsOddTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new IsOdd<O, O>(null, O.ONE);
    }

    @Test
    public void isOddWhenModulusIsNotEqualToZero() {
        final O zero = O.ONE;
        final Predicate<O> odd = new IsOdd<O, O>(new ModulusPolicy<O, O, Integer>() {

            @Override
            public O modulus(O lhs, Integer modulus) {
                return O.ANOTHER;
            }
        }, zero);

        Assert.assertTrue(odd.accept(O.ONE));
    }

    @Test
    public void isNotOddWhenModulusIsEqualToZero() {
        final O zero = O.ONE;
        final Predicate<O> odd = new IsOdd<O, O>(new ModulusPolicy<O, O, Integer>() {

            @Override
            public O modulus(O lhs, Integer modulus) {
                return O.ONE;
            }
        }, zero);

        Assert.assertFalse(odd.accept(O.ONE));
    }
}