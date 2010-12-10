package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LongSequencingPolicyTest {

    private final LongSequencingPolicy policy = new LongSequencingPolicy();

    @Test
    public void canEvaluateNext(){
        Assert.assertEquals(new Long(1l), policy.next(0l));
    }
    @Test
    public void canEvaluatePrev(){
        Assert.assertEquals(new Long(0l), policy.prev(1l));
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
        Assert.assertEquals(new LongSequencingPolicy().hashCode(), new LongSequencingPolicy().hashCode());
    }
    @Test
    public void twoPoliciesAreEquals(){
        Assert.assertEquals(new LongSequencingPolicy(), new LongSequencingPolicy());
    }

}