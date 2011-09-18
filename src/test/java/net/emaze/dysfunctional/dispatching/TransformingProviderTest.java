package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingProviderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new TransformingProvider<O, O>(new Identity<O>(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingProvider<O, O>(null, new ConstantProvider<O>(O.ONE));
    }
    
    @Test
    public void canTransformProvidedElement() {
        final Provider<O> provider = new TransformingProvider<O, O>(new Identity<O>(), new ConstantProvider<O>(O.ONE));
        final O got = provider.provide();
        Assert.assertEquals(O.ONE, got);
    }
}
