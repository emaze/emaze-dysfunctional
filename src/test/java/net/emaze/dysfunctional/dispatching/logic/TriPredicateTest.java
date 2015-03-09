package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class TriPredicateTest {

    @Test
    public void negatingAlwaysYieldsFalse() {
        final TriPredicate<O, O, O> p = new TernaryAlways<O, O, O>().negate();
        final boolean got = p.test(null, null, null);
        Assert.assertEquals(false, got);
    }

    @Test
    public void negatingNeverYieldsFalse() {
        final TriPredicate<O, O, O> p = new TernaryNever<O, O, O>().negate();
        final boolean got = p.test(null, null, null);
        Assert.assertEquals(true, got);
    }
}
