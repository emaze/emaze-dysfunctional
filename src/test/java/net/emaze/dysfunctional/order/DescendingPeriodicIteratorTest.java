package net.emaze.dysfunctional.order;

import java.util.Arrays;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.filtering.Filtering;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class DescendingPeriodicIteratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullSequencerYieldsException() {
        new DescendingPeriodicIterator<O>(null, O.ONE);
    }

    @Test
    public void callingNextYieldsElementsInDescendingOrder() {
        final PeriodicSequencingPolicy<Integer> sequencer = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 2);
        final DescendingPeriodicIterator<Integer> periodicIterator = new DescendingPeriodicIterator<Integer>(sequencer, 0);
        Assert.assertEquals(Arrays.asList(0, 2, 1), Consumers.all(Filtering.first(3, periodicIterator)));
    }
}
