package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.spying.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PredicateIgnoreFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreFirst<O, O>(null);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param = Box.empty();
        final Predicate<O> spy = Spies.spy1st(new Always<O>(), param);
        final BinaryPredicate<O, O> adapted = new PredicateIgnoreFirst<O, O>(spy);
        adapted.accept(O.IGNORED, O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
