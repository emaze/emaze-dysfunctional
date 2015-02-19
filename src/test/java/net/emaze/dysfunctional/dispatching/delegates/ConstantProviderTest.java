package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
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
        final Supplier<O> provider = new ConstantProvider<O>(O.ONE);
        final O provided = provider.get();
        Assert.assertEquals(O.ONE, provided);
    }
}
