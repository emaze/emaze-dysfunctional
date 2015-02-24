package net.emaze.dysfunctional.dispatching;

import java.util.function.Function;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TransformingSupplierTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new TransformingSupplier<O, O>(Function.identity(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new TransformingSupplier<O, O>(null, new ConstantSupplier<O>(O.ONE));
    }
    
    @Test
    public void canTransformProvidedElement() {
        final Supplier<O> supplier = new TransformingSupplier<O, O>(Function.identity(), new ConstantSupplier<O>(O.ONE));
        final O got = supplier.get();
        Assert.assertEquals(O.ONE, got);
    }
}
