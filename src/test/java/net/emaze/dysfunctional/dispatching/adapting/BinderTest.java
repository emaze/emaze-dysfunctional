package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import java.util.function.Supplier;
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
        final Supplier<String> supplier = new Binder<String, String>(Function.identity(), "bound");
        final String got = supplier.get();
        Assert.assertEquals("bound", got);
    }

}