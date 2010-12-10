package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IntegerSequencingPolicyTest {


    private final IntegerSequencingPolicy policy = new IntegerSequencingPolicy();

    @Test
    public void canEvaluateNext(){
        Assert.assertEquals(new Integer(1), policy.next(0));
    }
    @Test
    public void canEvaluatePrev(){
        Assert.assertEquals(new Integer(0), policy.prev(1));
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
        Assert.assertEquals(new IntegerSequencingPolicy().hashCode(), new IntegerSequencingPolicy().hashCode());
    }
    @Test
    public void twoPoliciesAreEquals(){
        Assert.assertEquals(new IntegerSequencingPolicy(), new IntegerSequencingPolicy());
    }

}