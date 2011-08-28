package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DelegateToPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new DelegateToPredicate<O>(null);
    }

    @Test
    public void adapterCorrectlyPassesParamToAdapted() {
        final Predicate<Boolean> adapted = new DelegateToPredicate<Boolean>(new Identity<Boolean>());
        boolean got = adapted.accept(true);
        Assert.assertEquals(true, got);
    }
}
