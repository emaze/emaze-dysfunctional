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
public class ConsumerBinderFirstOfThreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ConsumerBinderFirstOfThree<O, O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param1 = Box.empty();
        final TriConsumer<O, O, O> spy = Spies.spy1st(new TernaryNoop<O, O, O>(), param1);
        final ConsumerBinderFirstOfThree<O, O, O> adapted = new ConsumerBinderFirstOfThree<O, O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER, O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}
