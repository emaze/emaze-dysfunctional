package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BinderTest {


    @Test(expected=IllegalArgumentException.class)
    public void creatingBinderWithNullDelegateYieldsException() {
        new Binder<String, String>(null, "useless");
    }

    @Test
    public void paramIsCorrectlyBound() {
        final Supplier<String> provider = new Binder<String, String>(UnaryOperator.identity(), "bound");
        final String got = provider.get();
        Assert.assertEquals("bound", got);
    }

}