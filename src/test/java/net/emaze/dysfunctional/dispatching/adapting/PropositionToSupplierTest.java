package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import org.junit.Assert;
import org.junit.Test;

public class PropositionToSupplierTest {
    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPropositionYieldsException() {
        new PropositionToSupplier(null);
    }

    @Test
    public void callingThePropositionYieldsProviderResult() {
        final Boolean expected = Boolean.TRUE;
        final Supplier<Boolean> supplier = new PropositionToSupplier(new Yes());
        Assert.assertEquals(expected, supplier.get());
    }
}