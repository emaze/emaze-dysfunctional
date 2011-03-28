package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.adapting.Binder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        final Provider<String> provider = new Binder<String, String>(new Identity<String>(), "bound");
        final String got = provider.provide();
        Assert.assertEquals("bound", got);
    }

}