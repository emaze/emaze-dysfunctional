package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
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
        final Function<String, String> delegate = new BinderSecond<String, String, String>(new ConcatenateString(), "bound");
        final String got = delegate.apply("passed");
        Assert.assertEquals("passedbound", got);
    }

}