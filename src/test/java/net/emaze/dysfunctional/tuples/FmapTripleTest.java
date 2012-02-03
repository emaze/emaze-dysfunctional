package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class FmapTripleTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new FmapTriple<O, O, O, O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullPairYieldsException() {
        new FmapTriple<O, O, O, O>(new FirstParamOfThree<O, O, O>()).perform(null);
    }
}