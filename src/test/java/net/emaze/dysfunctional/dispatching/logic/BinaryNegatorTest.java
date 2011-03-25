package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.BinaryNever;
import net.emaze.dysfunctional.dispatching.logic.BinaryNegator;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryNegatorTest {

    @Test
    public void negatingAlwaysYieldsFalse() {
        final BinaryPredicate<O, O> p = new BinaryNegator<O, O>(new BinaryAlways<O, O>());
        boolean got = p.accept(null, null);
        Assert.assertEquals(false, got);
    }

    @Test
    public void negatingNeverYieldsFalse() {
        final BinaryPredicate<O, O> p = new BinaryNegator<O, O>(new BinaryNever<O, O>());
        boolean got = p.accept(null, null);
        Assert.assertEquals(true, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingNeverWithNullPredicateYieldsAnException() {
        new BinaryNegator<O, O>(null);
    }
}
