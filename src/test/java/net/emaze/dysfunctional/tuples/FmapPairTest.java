package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class FmapPairTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new FmapPair<O, O, O>(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullPairYieldsException() {
        new FmapPair<O, O, O>(new FirstParam<O, O>()).perform(null);
    }
}
