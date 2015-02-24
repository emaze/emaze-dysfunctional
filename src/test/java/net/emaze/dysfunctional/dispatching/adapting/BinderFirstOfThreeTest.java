package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BiFunction;
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
        final BiFunction<String,String, String> function = new BinderFirstOfThree<String, String, String, String>(new ConcatenateThreeStrings(), "bound");
        final String got = function.apply("passed", "passed");
        Assert.assertEquals("boundpassedpassed", got);
    }


}