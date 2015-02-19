package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import java.util.function.Predicate;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateBinderFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderFirst<O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param1 = Box.empty();
        final BinaryPredicate<O, O> spy = Spies.spy1st(new BinaryAlways<O, O>(), param1);
        final Predicate<O> adapted = new PredicateBinderFirst<O, O>(spy, O.ONE);
        adapted.test(O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
