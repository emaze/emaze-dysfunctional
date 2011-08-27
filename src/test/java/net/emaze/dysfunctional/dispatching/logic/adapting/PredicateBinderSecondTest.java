package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.BinaryCapturingPredicate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
        final BinaryPredicate<O, O> mock = new BinaryCapturingPredicate<O, O>(new BinaryAlways<O, O>(), Box.<O>empty(), param2);
        final Predicate<O> adapted = new PredicateBinderSecond<O, O>(mock, O.ONE);
        adapted.accept(O.ANOTHER);
        Assert.assertEquals(O.ONE, param2.getContent());
    }
}
