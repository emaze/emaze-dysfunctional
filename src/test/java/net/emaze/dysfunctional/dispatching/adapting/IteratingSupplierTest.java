package net.emaze.dysfunctional.dispatching.adapting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.Iterations;
import java.util.function.Supplier;
import java.util.Optional;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IteratingSupplierTest {

    @Test
    public void callingProviderYieldsIteratorElementsInOrder() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final Supplier<Optional<O>> supplier = new IteratingSupplier<O>(iterator);

        final List<Optional<O>> expected = Arrays.asList(Optional.of(O.ONE), Optional.of(O.ANOTHER), Optional.<O>empty());
        Assert.assertEquals(expected, Arrays.asList(supplier.get(), supplier.get(), supplier.get()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void adaptingNullIteratorYieldsException() {
        new IteratingSupplier<Integer>(null);
    }
}