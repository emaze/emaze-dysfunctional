package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Consumer;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DelegateToActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullYieldsException() {
        new DelegateToAction<O, O>(null);
    }

    @Test
    public void adapterCorrectlyPassesParamToAdapted() {
        final Box<O> param = Box.empty();
        final Function<O, O> spy = Spies.spy1st(new Identity<O>(), param);
        final Consumer<O> adapted = new DelegateToAction<O, O>(spy);
        adapted.accept(O.ONE);
        Assert.assertEquals(O.ONE, param.getContent());
    }
}
