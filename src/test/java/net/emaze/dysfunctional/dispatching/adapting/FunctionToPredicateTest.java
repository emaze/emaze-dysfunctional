package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class FunctionToPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new FunctionToPredicate<O>(null);
    }

    @Test
    public void adapterCorrectlyPassesParamToAdapted() {
        final Predicate<Boolean> adapted = new FunctionToPredicate<Boolean>(Function.identity());
        final boolean got = adapted.test(true);
        Assert.assertEquals(true, got);
    }
}
