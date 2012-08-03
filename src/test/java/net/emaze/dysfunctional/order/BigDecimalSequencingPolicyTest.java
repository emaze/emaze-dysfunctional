package net.emaze.dysfunctional.order;

import java.math.BigDecimal;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class BigDecimalSequencingPolicyTest {
    private final BigDecimalSequencingPolicy policy = new BigDecimalSequencingPolicy();

    @Test
    public void canEvaluateNext(){
        Assert.assertEquals(Maybe.just(BigDecimal.ONE), policy.next(BigDecimal.ZERO));
    }
    @Test
    public void canEvaluatePrev(){
        Assert.assertEquals(BigDecimal.ZERO, policy.prev(BigDecimal.ONE));
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
        Assert.assertEquals(new BigDecimalSequencingPolicy().hashCode(), new BigDecimalSequencingPolicy().hashCode());
    }
    @Test
    public void twoPoliciesAreEquals(){
        Assert.assertEquals(new BigDecimalSequencingPolicy(), new BigDecimalSequencingPolicy());
    }


}