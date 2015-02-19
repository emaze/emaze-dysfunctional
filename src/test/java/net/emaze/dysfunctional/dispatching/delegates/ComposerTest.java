package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.UnaryOperator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComposerTest {

    @Test
    public void canComposeTwoDelegates() {
        Composer<Integer, Integer, Integer> c = new Composer<Integer, Integer, Integer>(UnaryOperator.identity(), UnaryOperator.identity());
        Assert.assertEquals(1, (int)c.apply(1));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullFormerDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(null, UnaryOperator.identity());
    }

    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullLatterDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(UnaryOperator.identity(), null);
    }
}
