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
public class PredicateBinderSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderSecondOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final Box<O> param2 = Box.empty();
        final TernaryPredicate<O, O, O> spy = Spies.spy2nd(new TernaryAlways<O, O, O>(), param2);
        final BinaryPredicate<O, O> adapted = new PredicateBinderSecondOfThree<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
