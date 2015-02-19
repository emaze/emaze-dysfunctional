package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author dangelocola, rferranti
 */
public class IsJustTest {

    @Test
    public void justMatches() {
        Predicate<Optional<Integer>> p = new IsJust<Integer>();
        Assert.assertTrue(p.test(Optional.of(1)));
    }

    @Test
    public void nothingIsNotJust() {
        Predicate<Optional<Integer>> p = new IsJust<Integer>();
        Assert.assertFalse(p.test(Optional.<Integer>empty()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingAgainstNullYieldsException() {
        new IsJust<Object>().test(null);
    }
}
