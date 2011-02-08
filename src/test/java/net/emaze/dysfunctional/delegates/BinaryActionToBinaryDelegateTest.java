package net.emaze.dysfunctional.delegates;

import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
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
        final Box<Pair<O, O>> box = new Box<Pair<O, O>>();
        final BinaryAction<O, O> adaptee = new BinaryBoxingAction<O, O>(box);
        final BinaryDelegate<Void, O, O> del = new BinaryActionToBinaryDelegate<O, O>(adaptee);
        final O former = new O();
        final O latter = new O();

        del.perform(former, latter);

        Assert.assertEquals(Pair.of(former, latter), box.getContent());
    }

    public class BinaryBoxingAction<T1, T2> implements BinaryAction<T1, T2> {

        private Box<Pair<T1, T2>> box;

        public BinaryBoxingAction(Box<Pair<T1, T2>> box) {
            this.box = box;
        }

        @Override
        public void perform(T1 former, T2 latter) {
            box.setContent(Pair.of(former, latter));
        }
    }
}
