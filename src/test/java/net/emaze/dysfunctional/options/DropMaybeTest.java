package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DropMaybeTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingPerformWithNullYieldsException() {
        new DropMaybe<Object>().apply(null);
    }

    @Test
    public void callingPerformWithNothingYieldsNull() {
        Object got = new DropMaybe<Object>().apply(Maybe.nothing());
        Assert.assertNull(got);
    }

    @Test
    public void callingPerformWithJustSomethingYieldsSomething() {
        Object got = new DropMaybe<Object>().apply(Maybe.just(new Object()));
        Assert.assertNotNull(got);
    }
}
