package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LiftJustTest {

    @Test
    public void callingPerformWithNullYieldsJustNull() {
        final Maybe<Object> got = new LiftJust<Object>().perform(null);
        Assert.assertEquals(Maybe.just(null), got);
    }

    @Test
    public void callingPerformWithSomethingYieldsJustSomething() {
        final Object source = new Object();
        final Maybe<Object> got = new LiftJust<Object>().perform(source);
        Assert.assertEquals(Maybe.just(source), got);
    }


}