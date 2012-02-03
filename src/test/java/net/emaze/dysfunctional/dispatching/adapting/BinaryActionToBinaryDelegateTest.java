package net.emaze.dysfunctional.dispatching.adapting;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinaryActionToBinaryDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new BinaryActionToBinaryDelegate<O, O>(null);
    }

    @Test
    public void callingAdapterCallsAdapted() {
        final AtomicLong calls = new AtomicLong();
        final BinaryAction<O, O> adaptee = Spies.monitor(new BinaryNoop<O, O>(), calls);
        final BinaryDelegate<Void, O, O> del = new BinaryActionToBinaryDelegate<O, O>(adaptee);
        del.perform(O.ONE, O.ANOTHER);

        Assert.assertEquals(1l, calls.get());
    }

    @Test
    public void callingAdapterPassesFirstArgument() {
        final Box<O> param1 = Box.empty();
        final BinaryAction<O, O> adaptee = Spies.spy1st(new BinaryNoop<O, O>(), param1);
        final BinaryDelegate<Void, O, O> del = new BinaryActionToBinaryDelegate<O, O>(adaptee);
        del.perform(O.ONE, O.ANOTHER);

        Assert.assertEquals(O.ONE, param1.getContent());
    }

    @Test
    public void callingAdapterPassesSecondArgument() {
        final Box<O> param2 = Box.empty();
        final BinaryAction<O, O> adaptee = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
        final BinaryDelegate<Void, O, O> del = new BinaryActionToBinaryDelegate<O, O>(adaptee);
        del.perform(O.ONE, O.ANOTHER);

        Assert.assertEquals(O.ANOTHER, param2.getContent());
    }
}
