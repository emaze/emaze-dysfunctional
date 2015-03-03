package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConsumerBinderFirstTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ConsumerBinderFirst<O, O>(null, O.ONE);
    }

    @Test
    public void canBindFirstParameter() {
        final Box<O> param1 = Box.empty();
        final BiConsumer<O, O> spy = Spies.spy1st(new BinaryNoop<O, O>(), param1);
        final ConsumerBinderFirst<O, O> adapted = new ConsumerBinderFirst<O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }
}