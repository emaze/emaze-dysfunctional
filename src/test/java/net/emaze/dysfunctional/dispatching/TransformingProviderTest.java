package net.emaze.dysfunctional.dispatching;

import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
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
        new TransformingProvider<O, O>(Function.identity(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingProvider<O, O>(null, new ConstantProvider<O>(O.ONE));
    }
    
    @Test
    public void canTransformProvidedElement() {
        final Supplier<O> provider = new TransformingProvider<O, O>(Function.identity(), new ConstantProvider<O>(O.ONE));
        final O got = provider.get();
        Assert.assertEquals(O.ONE, got);
    }
}
