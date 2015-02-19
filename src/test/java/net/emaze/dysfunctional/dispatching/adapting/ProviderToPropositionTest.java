package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import java.util.function.BooleanSupplier;
import org.junit.Assert;
import org.junit.Test;

public class ProviderToPropositionTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullProviderYieldsException() {
        new ProviderToProposition(null);
    }

    @Test
    public void callingThePropositionYieldsProviderResult() {
        final Boolean expected = Boolean.TRUE;
        final BooleanSupplier proposition = new ProviderToProposition(new ConstantProvider<Boolean>(expected));
        Assert.assertEquals(expected, proposition.getAsBoolean());
    }
}