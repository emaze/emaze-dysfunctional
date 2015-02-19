package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class PropositionIgnoreParameterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPropositionYieldsException() {
        new PropositionIgnoreParameter<O>(null);
    }

    @Test
    public void callingThePredicateYieldsPropositionResult() {
        final Boolean expected = Boolean.TRUE;
        final Predicate<O> predicate = new PropositionIgnoreParameter<O>(new Yes());
        Assert.assertEquals(expected, predicate.test(O.IGNORED));
    }

}