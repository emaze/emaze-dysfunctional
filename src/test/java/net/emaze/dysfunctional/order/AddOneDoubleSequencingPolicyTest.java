package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class AddOneDoubleSequencingPolicyTest {

    private final AddOneDoubleSequencingPolicy policy = new AddOneDoubleSequencingPolicy();

    @Test
    public void canEvaluateNext() {
        Assert.assertEquals(Maybe.just(new Double(1.)), policy.next(0.));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextOfNullYieldsException() {
        policy.next(null);
    }

    @Test
    public void twoPoliciesHaveSameHashCode() {
        Assert.assertEquals(new AddOneDoubleSequencingPolicy().hashCode(), new AddOneDoubleSequencingPolicy().hashCode());
    }

    @Test
    public void twoPoliciesAreEquals() {
        Assert.assertEquals(new AddOneDoubleSequencingPolicy(), new AddOneDoubleSequencingPolicy());
    }
}