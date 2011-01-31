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
        new DropMaybe<Object>().perform(null);
    }

    @Test
    public void callingPerformWithNothingYieldsNull() {
        Object got = new DropMaybe<Object>().perform(Maybe.nothing());
        Assert.assertNull(got);
    }

    @Test
    public void callingPerformWithJustSomethingYieldsSomething() {
        Object got = new DropMaybe<Object>().perform(Maybe.just(new Object()));
        Assert.assertNotNull(got);
    }
}
