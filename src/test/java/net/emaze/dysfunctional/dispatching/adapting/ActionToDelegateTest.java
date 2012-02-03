package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ActionToDelegateTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullActionYieldsException() {
        new ActionToDelegate<Object>(null);
    }

    @Test// you probably expect this (expected = ClassCastException.class)
    public void passingWrongTypeToErasureJustForwardsToTheNestedAction() {
        final Delegate d = new ActionToDelegate<O>(new Noop<O>());
        d.perform(new Object());
    }

    @Test
    public void callingAdapterCallsAdapted() {
        final Box<Object> box = new Box<Object>();
        final Action<Object> adaptee = Spies.spy(new Noop<Object>(), box);
        final Delegate<Void, Object> del = new ActionToDelegate<Object>(adaptee);
        final Object anObject = new Object();
        del.perform(anObject);
        Assert.assertEquals(anObject, box.getContent());
    }
}
