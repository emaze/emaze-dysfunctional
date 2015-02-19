package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Test;
import org.junit.Assert;

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
        final Supplier<String> provider = new Binder<String, String>(new Identity<String>(), "bound");
        final String got = provider.get();
        Assert.assertEquals("bound", got);
    }

}