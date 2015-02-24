package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.Spies;
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
        final TriPredicate<O, O, O> spy = Spies.spy2nd(new TernaryAlways<O, O, O>(), param2);
        final BiPredicate<O, O> adapted = new PredicateBinderSecondOfThree<O, O, O>(spy, O.ONE);
        adapted.test(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
