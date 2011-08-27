package net.emaze.dysfunctional.dispatching.logic;

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
        new CapturingPredicate<O>(null, Box.<O>empty());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullBoxYieldsException() {
        new CapturingPredicate<O>(new Always<O>(), null);
    }

    @Test
    public void parametersAreCaptured() {
        final Box<O> param = Box.empty();
        final Predicate<O> pred = new CapturingPredicate<O>(new Always<O>(), param);
        pred.accept(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
