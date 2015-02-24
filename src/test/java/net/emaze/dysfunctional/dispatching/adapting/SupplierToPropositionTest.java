package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import java.util.function.BooleanSupplier;
import org.junit.Assert;
import org.junit.Test;

public class SupplierToPropositionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new SupplierToProposition(null);
    }

    @Test
    public void callingThePropositionYieldsProviderResult() {
        final Boolean expected = Boolean.TRUE;
        final BooleanSupplier proposition = new SupplierToProposition(new ConstantSupplier<Boolean>(expected));
        Assert.assertEquals(expected, proposition.getAsBoolean());
    }
}