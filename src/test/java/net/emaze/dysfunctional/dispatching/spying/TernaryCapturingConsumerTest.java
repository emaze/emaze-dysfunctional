package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryCapturingConsumerTest {

    private static final TernaryNoop<O, O, O> NOOP = new TernaryNoop<O, O, O>();

    @Test(expected = IllegalArgumentException.class)
    public void wrappingNullActionYieldsException() {
        new TernaryCapturingConsumer<O, O, O>(null, Box.<O>empty(), Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForFirstParamYieldsException() {
        new TernaryCapturingConsumer<O, O, O>(NOOP, null, Box.<O>empty(), Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForSecondParamYieldsException() {
        new TernaryCapturingConsumer<O, O, O>(NOOP, Box.<O>empty(), null, Box.<O>empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void passingNullBoxForThirdParamYieldsException() {
        new TernaryCapturingConsumer<O, O, O>(NOOP, Box.<O>empty(), Box.<O>empty(), null);
    }

    @Test
    public void firstParameterIsCaptured() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriConsumer<O, O, O> consumer = new TernaryCapturingConsumer<O, O, O>(NOOP, param1, param2, param3);
        consumer.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void secondParameterIsCaptured() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriConsumer<O, O, O> consumer = new TernaryCapturingConsumer<O, O, O>(NOOP, param1, param2, param3);
        consumer.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }

    @Test
    public void thirdParameterIsCaptured() {
        final Box<O> param1 = Box.empty();
        final Box<O> param2 = Box.empty();
        final Box<O> param3 = Box.empty();
        final TriConsumer<O, O, O> consumer = new TernaryCapturingConsumer<O, O, O>(NOOP, param1, param2, param3);
        consumer.accept(O.ONE, O.ANOTHER, O.YET_ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, param3.getContent());
    }
}
