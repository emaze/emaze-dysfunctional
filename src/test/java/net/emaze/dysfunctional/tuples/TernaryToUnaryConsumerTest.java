package net.emaze.dysfunctional.tuples;

import java.util.function.Consumer;
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
public class TernaryToUnaryConsumerTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new TernaryToUnaryConsumer<O, O, O>(null);
    }

    @Test
    public void adaptingPassesFirstValueOfPairAsFirstParameter() {
        final Box<O> box = Box.empty();
        final TriConsumer<O, O, O> spy = Spies.spy1st(new TernaryNoop<O, O, O>(), box);
        final Consumer<Triple<O, O, O>> consumer = new TernaryToUnaryConsumer<O, O, O>(spy);
        consumer.accept(Triple.of(O.ONE, O.IGNORED, O.IGNORED));
        Assert.assertEquals(O.ONE, box.getContent());
    }

    @Test
    public void adaptingPassesSecondValueOfPairAsSecondParameter() {
        final Box<O> box = Box.empty();
        final TriConsumer<O, O, O> spy = Spies.spy2nd(new TernaryNoop<O, O, O>(), box);
        final Consumer<Triple<O, O, O>> consumer = new TernaryToUnaryConsumer<O, O, O>(spy);
        consumer.accept(Triple.of(O.IGNORED, O.ONE, O.IGNORED));
        Assert.assertEquals(O.ONE, box.getContent());
    }

    @Test
    public void adaptingPassesThirdValueOfPairAsThirdParameter() {
        final Box<O> box = Box.empty();
        final TriConsumer<O, O, O> spy = Spies.spy3rd(new TernaryNoop<O, O, O>(), box);
        final Consumer<Triple<O, O, O>> consumer = new TernaryToUnaryConsumer<O, O, O>(spy);
        consumer.accept(Triple.of(O.IGNORED, O.IGNORED, O.ONE));
        Assert.assertEquals(O.ONE, box.getContent());
    }
}
