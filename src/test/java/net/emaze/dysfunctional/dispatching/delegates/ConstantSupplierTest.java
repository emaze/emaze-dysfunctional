package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConstantSupplierTest {

    @Test
    public void elementProvidedByConstantProviderIsTheSameItWasConstructedWith() {
        final Supplier<O> supplier = new ConstantSupplier<O>(O.ONE);
        final O provided = supplier.get();
        Assert.assertEquals(O.ONE, provided);
    }
}
