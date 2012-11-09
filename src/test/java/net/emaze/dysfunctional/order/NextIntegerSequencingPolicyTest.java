package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NextIntegerSequencingPolicyTest {

    private final NextIntegerSequencingPolicy policy = new NextIntegerSequencingPolicy();

    @Test
    public void canEvaluateNext() {
        Assert.assertEquals(Maybe.just(new Integer(1)), policy.next(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextOfNullYieldsException() {
        policy.next(null);
    }

    @Test
    public void twoPoliciesHaveSameHashCode() {
        Assert.assertEquals(new NextIntegerSequencingPolicy().hashCode(), new NextIntegerSequencingPolicy().hashCode());
    }

    @Test
    public void twoPoliciesAreEquals() {
        Assert.assertEquals(new NextIntegerSequencingPolicy(), new NextIntegerSequencingPolicy());
    }
}