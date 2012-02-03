package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderThirdTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderSecondWithNullPredicateYieldsException() {
        new BinderThird<String, String, String, String>(null, "useless");
    }

    @Test
    public void thirdParamIsCorrectlyBound() {
        final BinaryDelegate<String,String, String> delegate = new BinderThird<String, String, String, String>(new ConcatenateThreeStrings(), "bound");
        final String got = delegate.perform("passed", "passed");
        Assert.assertEquals("passedpassedbound", got);
    }

}