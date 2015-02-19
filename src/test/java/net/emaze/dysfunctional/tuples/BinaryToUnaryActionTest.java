package net.emaze.dysfunctional.tuples;

import java.util.function.Consumer;
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
public class BinaryToUnaryActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new BinaryToUnaryAction<O, O>(null);
    }

    @Test
    public void adaptingPassesFirstValueOfPairAsFirstParameter() {
        final Box<O> box = Box.empty();
        final BiConsumer<O, O> spy = Spies.spy1st(new BinaryNoop<O, O>(), box);
        final Consumer<Pair<O, O>> action = new BinaryToUnaryAction<O, O>(spy);
        action.accept(Pair.of(O.ONE, O.IGNORED));
        Assert.assertEquals(O.ONE, box.getContent());
    }
    @Test
    public void adaptingPassesSecondValueOfPairAsSecondParameter() {
        final Box<O> box = Box.empty();
        final BiConsumer<O, O> spy = Spies.spy2nd(new BinaryNoop<O, O>(), box);
        final Consumer<Pair<O, O>> action = new BinaryToUnaryAction<O, O>(spy);
        action.accept(Pair.of(O.IGNORED, O.ONE));
        Assert.assertEquals(O.ONE, box.getContent());
    }
}
