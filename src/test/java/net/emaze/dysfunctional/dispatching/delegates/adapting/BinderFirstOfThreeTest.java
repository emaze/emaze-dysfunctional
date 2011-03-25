package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.adapting.BinderFirstOfThree;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderFirstOfThreeTest {


    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderSecondWithNullPredicateYieldsException() {
        new BinderFirstOfThree<String, String, String, String>(null, "useless");
    }

    @Test
    public void thirdParamIsCorrectlyBound() {
        BinaryDelegate<String,String, String> delegate = new BinderFirstOfThree<String, String, String, String>(new ConcatenateThreeStrings(), "bound");
        String got = delegate.perform("passed", "passed");
        Assert.assertEquals("boundpassedpassed", got);
    }


}