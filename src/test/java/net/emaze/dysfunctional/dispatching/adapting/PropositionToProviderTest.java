package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import org.junit.Assert;
import org.junit.Test;

public class PropositionToProviderTest {
    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPropositionYieldsException() {
        new PropositionToProvider(null);
    }

    @Test
    public void callingThePropositionYieldsProviderResult() {
        final Boolean expected = Boolean.TRUE;
        final Supplier<Boolean> provider = new PropositionToProvider(new Yes());
        Assert.assertEquals(expected, provider.get());
    }
}