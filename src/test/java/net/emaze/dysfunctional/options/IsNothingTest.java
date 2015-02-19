package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IsNothingTest {

    @Test
    public void justIsNotNothing() {
        Predicate<Optional<Integer>> p = new IsNothing<Integer>();
        Assert.assertFalse(p.test(Optional.of(1)));
    }

    @Test
    public void nothingMatches() {
        Predicate<Optional<Integer>> p = new IsNothing<Integer>();
        Assert.assertTrue(p.test(Optional.<Integer>empty()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingAgainstNullYieldsException() {
        new IsNothing<Object>().test(null);
    }
}
