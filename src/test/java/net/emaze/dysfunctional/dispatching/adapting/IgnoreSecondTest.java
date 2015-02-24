package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import net.emaze.dysfunctional.Spies;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IgnoreSecondTest {

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullDelegateYieldsException() {
        new IgnoreSecond<O, O, O>(null);
    }

    @Test
    public void canIgnoreSecondParameter() {
        final Box<O> param = Box.empty();
        final Function<O, O> spy = Spies.spy1st(Function.identity(), param);        
        final IgnoreSecond<O, O, O> adapted = new IgnoreSecond<O, O, O>(spy);
        adapted.apply(O.ONE, O.IGNORED);
        Assert.assertEquals(param.getContent(), O.ONE);
    }
}
