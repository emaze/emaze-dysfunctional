package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.dispatching.delegates.ConstantProvider;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
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
        final Proposition proposition = new ProviderToProposition(new ConstantProvider<Boolean>(expected));
        Assert.assertEquals(expected, proposition.state());
    }
}