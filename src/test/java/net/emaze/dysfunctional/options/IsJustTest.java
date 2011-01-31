package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.delegates.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author dangelocola, rferranti
 */
public class IsJustTest {

    @Test
    public void justMatches() {
        Predicate<Maybe<Integer>> p = new IsJust<Integer>();
        Assert.assertTrue(p.test(Maybe.just(1)));
    }

    @Test
    public void nothingIsNotJust() {
        Predicate<Maybe<Integer>> p = new IsJust<Integer>();
        Assert.assertFalse(p.test(Maybe.<Integer>nothing()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingAgainstNullYieldsException() {
        new IsJust<Object>().test(null);
    }
}
