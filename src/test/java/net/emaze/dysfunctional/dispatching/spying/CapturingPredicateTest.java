package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CapturingPredicateTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullPredicateYieldsException() {
        new CapturingPredicate<O>(null, Box.<Boolean>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullResultBoxYieldsException() {
        new CapturingPredicate<O>(new Always<O>(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullParamBoxYieldsException() {
        new CapturingPredicate<O>(new Always<O>(), Box.<Boolean>empty(), null);
    }

    @Test
    public void resultIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param = Box.empty();
        final Predicate<O> pred = new CapturingPredicate<O>(new Always<O>(), result, param);
        pred.accept(O.ONE);
        Assert.assertEquals(true, result.getContent());
    }

    @Test
    public void parameterIsCaptured() {
        final Box<Boolean> result = Box.empty();
        final Box<O> param = Box.empty();
        final Predicate<O> pred = new CapturingPredicate<O>(new Always<O>(), result, param);
        pred.accept(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
