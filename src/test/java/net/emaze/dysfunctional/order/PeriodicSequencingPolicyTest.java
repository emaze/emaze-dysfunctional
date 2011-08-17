package net.emaze.dysfunctional.order;

import org.junit.Test;
import org.junit.Assert;

public class PeriodicSequencingPolicyTest {

    @Test
    public void nextInBounds() {
        final PeriodicSequencingPolicy<Integer> psp = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 1);
        Assert.assertEquals(Integer.valueOf(1), psp.next(0));
    }

    @Test
    public void nextUpperBound() {
        final PeriodicSequencingPolicy<Integer> psp = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 1);
        Assert.assertEquals(Integer.valueOf(0), psp.next(1));
    }

    @Test
    public void prevInBounds() {
        final PeriodicSequencingPolicy<Integer> psp = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 1);
        Assert.assertEquals(Integer.valueOf(0), psp.prev(1));
    }
    
    @Test
    public void prevLowerBound() {
        final PeriodicSequencingPolicy<Integer> psp = new PeriodicSequencingPolicy<Integer>(new IntegerSequencingPolicy(), 0, 1);
        Assert.assertEquals(Integer.valueOf(1), psp.prev(0));
    }
}
