package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.dispatching.logic.Always;
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
public class PredicateIgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new PredicateIgnoreSecond<O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final Box<O> param = Box.empty();
        final Predicate<O> spy = Spies.spy1st(new Always<O>(), param);
        final PredicateIgnoreSecond<O, O> adapted = new PredicateIgnoreSecond<O, O>(spy);
        adapted.accept(O.ONE, O.IGNORED);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
