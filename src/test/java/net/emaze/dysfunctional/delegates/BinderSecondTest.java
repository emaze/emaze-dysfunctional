package net.emaze.dysfunctional.delegates;

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
    public void firstParamIsCorrectlyBound() {
        Delegate<String,String> delegate = new BinderSecond<String, String, String>(new ConcatenateString(), "bound");
        String got = delegate.perform("passed");
        Assert.assertEquals("passedbound", got);
    }

}