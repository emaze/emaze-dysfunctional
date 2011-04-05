package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.SecondParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TernaryCapturingDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryDelegateToTernaryPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new TernaryDelegateToTernaryPredicate<O, O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesFirstParamToAdapted() {
        TernaryCapturingDelegate<Boolean, O, Boolean, O> adaptee = new TernaryCapturingDelegate<Boolean, O, Boolean, O>(new SecondParamOfThree<O, Boolean, O>());
        final TernaryPredicate<O, Boolean, O> adapted = new TernaryDelegateToTernaryPredicate<O, Boolean, O>(adaptee);
        adapted.accept(O.ONE, true, O.IGNORED);
        Assert.assertEquals(O.ONE, adaptee.first.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        TernaryCapturingDelegate<Boolean, Boolean, O, O> adaptee = new TernaryCapturingDelegate<Boolean, Boolean, O, O>(new FirstParamOfThree<Boolean, O, O>());
        final TernaryPredicate<Boolean, O, O> adapted = new TernaryDelegateToTernaryPredicate<Boolean, O, O>(adaptee);
        adapted.accept(true, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, adaptee.second.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        TernaryCapturingDelegate<Boolean, Boolean, O, O> adaptee = new TernaryCapturingDelegate<Boolean, Boolean, O, O>(new FirstParamOfThree<Boolean, O, O>());
        final TernaryPredicate<Boolean, O, O> adapted = new TernaryDelegateToTernaryPredicate<Boolean, O, O>(adaptee);
        adapted.accept(true, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, adaptee.third.getContent());
    }
}
