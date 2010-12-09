package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NegatorTest {

    @Test
    public void negatingAlwaysYieldsFalse() {
        final Predicate<Object> p = new Negator<Object>(new Always<Object>());
        boolean got = p.test(null);
        Assert.assertEquals(false, got);
    }

    @Test
    public void negatingNeverYieldsFalse() {
        final Predicate<Object> p = new Negator<Object>(new Never<Object>());
        boolean got = p.test(null);
        Assert.assertEquals(true, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingNeverWithNullPredicateYieldsAnException() {
        new Negator<Object>(null);
    }
}
