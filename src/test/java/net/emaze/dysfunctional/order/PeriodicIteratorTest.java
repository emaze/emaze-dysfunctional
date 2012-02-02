package net.emaze.dysfunctional.order;

import java.util.Arrays;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Filtering;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class PeriodicIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSequencerYieldsException() {
        new PeriodicIterator<O>(null, O.ONE);
    }

    @Test
    public void callingNextYieldsElementsInOrder() {
        final PeriodicSequencingPolicy<Integer> sequencer = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 1);
        final PeriodicIterator<Integer> periodicIterator = new PeriodicIterator<Integer>(sequencer, 0);
        Assert.assertEquals(Arrays.asList(0, 1), Consumers.all(Filtering.take(2, periodicIterator)));
    }
}
