package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateBinderFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderFirstOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param1 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy1st(new TernaryAlways<O, O, O>(), param1);
        final PredicateBinderFirstOfThree<O, O, O> adapted = new PredicateBinderFirstOfThree<O, O, O>(spy, O.ONE);
        adapted.test(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
