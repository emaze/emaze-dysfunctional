package net.emaze.dysfunctional.dispatching;

import junit.framework.Assert;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class TapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullActionYieldsException() {
        new Tapper<O>(null);
    }

    @Test
    public void tappingAnActionYieldsPossiblyMutatedOriginalValue() {
        Delegate<O, O> tapper = new Tapper<O>(new Noop<O>());
        Assert.assertSame(O.ONE, tapper.perform(O.ONE));
    }
}
