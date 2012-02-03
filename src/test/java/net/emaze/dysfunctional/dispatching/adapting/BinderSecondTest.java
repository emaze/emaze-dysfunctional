package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderSecondTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderSecondWithNullPredicateYieldsException() {
        new BinderSecond<String, String, String>(null, "useless");
    }

    @Test
    public void secondParamIsCorrectlyBound() {
        final Delegate<String,String> delegate = new BinderSecond<String, String, String>(new ConcatenateString(), "bound");
        final String got = delegate.perform("passed");
        Assert.assertEquals("passedbound", got);
    }

}