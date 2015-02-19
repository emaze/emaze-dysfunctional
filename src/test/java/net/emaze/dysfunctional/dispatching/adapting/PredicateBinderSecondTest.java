package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import java.util.function.BiPredicate;
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
public class PredicateBinderSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateBinderSecond<O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final Box<O> param2 = Box.empty();
        final BiPredicate<O, O> spy = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
        final Predicate<O> adapted = new PredicateBinderSecond<O, O>(spy, O.ONE);
        adapted.test(O.ANOTHER);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
