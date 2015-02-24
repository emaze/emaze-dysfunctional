package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
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
        final Function<String, String> function = new BinderFirst<String, String, String>(new ConcatenateString(), "bound");
        final String got = function.apply("passed");
        Assert.assertEquals("boundpassed", got);
    }


}