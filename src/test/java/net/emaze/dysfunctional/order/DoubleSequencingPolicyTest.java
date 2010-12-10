package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DoubleSequencingPolicyTest {
    private final DoubleSequencingPolicy policy = new DoubleSequencingPolicy();

    @Test
    public void canEvaluateNext(){
        Assert.assertEquals(new Double(1.), policy.next(0.));
    }
    @Test
    public void canEvaluatePrev(){
        Assert.assertEquals(new Double(0.), policy.prev(1.));
    }

    @Test(expected=IllegalArgumentException.class)
    public void nextOfNullYieldsException() {
        policy.next(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void prevOfNullYieldsException() {
        policy.prev(null);
    }

    @Test
    public void twoPoliciesHaveSameHashCode(){
        Assert.assertEquals(new DoubleSequencingPolicy().hashCode(), new DoubleSequencingPolicy().hashCode());
    }
    @Test
    public void twoPoliciesAreEquals(){
        Assert.assertEquals(new DoubleSequencingPolicy(), new DoubleSequencingPolicy());
    }


}