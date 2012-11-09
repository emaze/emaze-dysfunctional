package net.emaze.dysfunctional.order;

import java.math.BigDecimal;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class AddOneBigDecimalSequencingPolicyTest {

    private final AddOneBigDecimalSequencingPolicy policy = new AddOneBigDecimalSequencingPolicy();

    @Test
    public void canEvaluateNext() {
        Assert.assertEquals(Maybe.just(BigDecimal.ONE), policy.next(BigDecimal.ZERO));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextOfNullYieldsException() {
        policy.next(null);
    }

    @Test
    public void twoPoliciesHaveSameHashCode() {
        Assert.assertEquals(new AddOneBigDecimalSequencingPolicy().hashCode(), new AddOneBigDecimalSequencingPolicy().hashCode());
    }

    @Test
    public void twoPoliciesAreEquals() {
        Assert.assertEquals(new AddOneBigDecimalSequencingPolicy(), new AddOneBigDecimalSequencingPolicy());
    }
}