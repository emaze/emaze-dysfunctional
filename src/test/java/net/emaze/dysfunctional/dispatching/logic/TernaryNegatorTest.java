package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.TernaryNever;
import net.emaze.dysfunctional.dispatching.logic.TernaryNegator;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryNegatorTest {

    @Test
    public void negatingAlwaysYieldsFalse() {
        final TriPredicate<O, O, O> p = new TernaryNegator<O, O, O>(new TernaryAlways<O, O, O>());
        boolean got = p.test(null, null, null);
        Assert.assertEquals(false, got);
    }

    @Test
    public void negatingNeverYieldsFalse() {
        final TriPredicate<O, O, O> p = new TernaryNegator<O, O, O>(new TernaryNever<O, O, O>());
        boolean got = p.test(null, null, null);
        Assert.assertEquals(true, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingNeverWithNullPredicateYieldsAnException() {
        new TernaryNegator<O, O, O>(null);
    }
}
