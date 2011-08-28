package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderSecondOfThreeTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderSecondWithNullPredicateYieldsException() {
        new BinderSecondOfThree<String, String, String, String>(null, "useless");
    }

    @Test
    public void secondParamIsCorrectlyBound() {
        BinaryDelegate<String,String, String> delegate = new BinderSecondOfThree<String, String, String, String>(new ConcatenateThreeStrings(), "bound");
        String got = delegate.perform("passed", "passed");
        Assert.assertEquals("passedboundpassed", got);
    }


}