package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PureMaybeTest {

    @Test
    public void callingWithNullYieldsJustNull() {
        final Maybe<Object> got = new PureMaybe<Object>().perform(null);
        Assert.assertEquals(Maybe.just(null), got);
    }

    @Test
    public void callingPerformWithSomethingYieldsJustSomething() {
        final Object source = new Object();
        final Maybe<Object> got = new PureMaybe<Object>().perform(source);
        Assert.assertEquals(Maybe.just(source), got);
    }
}