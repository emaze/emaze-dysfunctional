package net.emaze.dysfunctional.order;

import java.math.BigInteger;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NextBigIntegerSequencingPolicyTest {

    private final NextBigIntegerSequencingPolicy policy = new NextBigIntegerSequencingPolicy();

    @Test
    public void canEvaluateNext() {
        Assert.assertEquals(Maybe.just(BigInteger.ONE), policy.next(BigInteger.ZERO));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextOfNullYieldsException() {
        policy.next(null);
    }

    @Test
    public void twoPoliciesHaveSameHashCode() {
        Assert.assertEquals(new NextBigIntegerSequencingPolicy().hashCode(), new NextBigIntegerSequencingPolicy().hashCode());
    }

    @Test
    public void twoPoliciesAreEquals() {
        Assert.assertEquals(new NextBigIntegerSequencingPolicy(), new NextBigIntegerSequencingPolicy());
    }
}