package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderFirstTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderFirstWithNullDelegateYieldsException() {
        new BinderFirst<String, String, String>(null, "useless");
    }

    @Test
    public void firstParamIsCorrectlyBound() {
        final Delegate<String,String> delegate = new BinderFirst<String, String, String>(new ConcatenateString(), "bound");
        final String got = delegate.perform("passed");
        Assert.assertEquals("boundpassed", got);
    }


}