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
public class ConsumerBinderSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullPredicateYieldsException() {
        new ConsumerBinderSecond<O, O>(null, O.ONE);
    }

    @Test
    public void canBindSecondParameter() {
        final Box<O> param2 = Box.empty();
        final BiConsumer<O, O> spy = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
        final ConsumerBinderSecond<O, O> adapted = new ConsumerBinderSecond<O, O>(spy, O.ONE);
        adapted.accept(O.ANOTHER);
        Assert.assertEquals(param2.getContent(), O.ONE);
    }
}