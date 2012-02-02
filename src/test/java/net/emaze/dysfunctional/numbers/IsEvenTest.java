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
public class IsEvenTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new IsEven<O, O>(null, O.ONE);
    }

    @Test
    public void isEvenWhenModulusIsEqualToZero() {
        final O zero = O.ONE;
        final Predicate<O> even = new IsEven<O, O>(new ModulusPolicy<O, O, Integer>() {

            @Override
            public O modulus(O lhs, Integer modulus) {
                return O.ONE;
            }
        }, zero);

        Assert.assertTrue(even.accept(O.ONE));
    }

    @Test
    public void isNotEvenWhenModulusIsNotEqualToZero() {
        final O zero = O.ONE;
        final Predicate<O> even = new IsEven<O, O>(new ModulusPolicy<O, O, Integer>() {

            @Override
            public O modulus(O lhs, Integer modulus) {
                return O.ANOTHER;
            }
        }, zero);

        Assert.assertFalse(even.accept(O.ONE));
    }
}