package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateBinderThirdTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderThird<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindThirdParameter() {
        final Box<O> param3 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy3rd(new TernaryAlways<O, O, O>(), param3);
        final BinaryPredicate<O, O> adapted = new PredicateBinderThird<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param3.getContent());
    }
}
