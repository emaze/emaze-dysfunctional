package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;
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
        final BiFunction<String,String, String> delegate = new BinderSecondOfThree<String, String, String, String>(new ConcatenateThreeStrings(), "bound");
        final String got = delegate.apply("passed", "passed");
        Assert.assertEquals("passedboundpassed", got);
    }


}