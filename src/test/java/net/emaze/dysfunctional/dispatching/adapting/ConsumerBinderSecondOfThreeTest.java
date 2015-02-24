package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumerBinderSecondOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ConsumerBinderSecondOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final Box<O> param2 = Box.empty();
        final TriConsumer<O, O, O> spy = Spies.spy2nd(new TernaryNoop<O, O, O>(), param2);
        final ConsumerBinderSecondOfThree<O, O, O> adapted = new ConsumerBinderSecondOfThree<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(param2.getContent(), O.ONE);
    }
}
