package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConstantProviderTest {

    @Test
    public void elementProvidedByConstantProviderIsTheSameItWasConstructedWith() {
        final Provider<O> provider = new ConstantProvider<O>(O.ONE);
        final O provided = provider.provide();
        Assert.assertEquals(O.ONE, provided);
    }
}
