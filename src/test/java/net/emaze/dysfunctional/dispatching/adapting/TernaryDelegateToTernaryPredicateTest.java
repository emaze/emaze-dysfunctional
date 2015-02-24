package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.SecondParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.options.Box;
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
        final Box<O> param1 = Box.empty();
        final TriFunction<O, Boolean, O, Boolean> spy = Spies.spy1st(new SecondParamOfThree<O, Boolean, O>(), param1);
        final TriPredicate<O, Boolean, O> adapted = new TernaryDelegateToTernaryPredicate<>(spy);
        adapted.test(O.ONE, true, O.IGNORED);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void adapterCorrectlyPassesSecondParamToAdapted() {
        final Box<O> param2 = Box.empty();
        final TriFunction<Boolean, O, O, Boolean> spy = Spies.spy2nd(new FirstParamOfThree<Boolean, O, O>(), param2);
        final TriPredicate<Boolean, O, O> adapted = new TernaryDelegateToTernaryPredicate<>(spy);
        adapted.test(true, O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param2.getContent());
    }

    @Test
    public void adapterCorrectlyPassesThirdParamToAdapted() {
        final Box<O> param3 = Box.empty();
        final TriFunction<Boolean, O, O, Boolean> spy = Spies.spy3rd(new FirstParamOfThree<Boolean, O, O>(), param3);
        final TriPredicate<Boolean, O, O> adapted = new TernaryDelegateToTernaryPredicate<>(spy);
        adapted.test(true, O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param3.getContent());
    }
}
