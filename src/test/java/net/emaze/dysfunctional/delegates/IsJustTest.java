package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author dangelocola
 */
public class IsJustTest {

    @Test
    public void justHasValue() {
        Predicate<Maybe<Integer>> p = new IsJust<Integer>();
        Assert.assertTrue(p.test(Maybe.just(1)));
    }

    @Test
    public void nothingHasNoValue() {
        Predicate<Maybe<Integer>> p = new IsJust<Integer>();
        Assert.assertFalse(p.test(Maybe.<Integer>nothing()));
    }
}
