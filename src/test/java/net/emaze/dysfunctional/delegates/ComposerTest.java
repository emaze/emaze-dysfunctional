package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComposerTest {

    @Test
    public void canComposeTwoDelegates() {
        Composer<Integer, Integer, Integer> c = new Composer<Integer, Integer, Integer>(new Identity<Integer>(), new Identity<Integer>());
        Assert.assertEquals(1, (int)c.perform(1));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullFormerDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(null, new Identity<Integer>());
    }

    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullLatterDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(new Identity<Integer>(), null);
    }
}
