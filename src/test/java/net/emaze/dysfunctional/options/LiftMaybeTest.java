package net.emaze.dysfunctional.options;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LiftMaybeTest {

    @Test
    public void callingPerformWithNullYieldsNothing() {
        final Maybe<Object> got = new LiftMaybe<Object>().perform(null);
        Assert.assertEquals(Maybe.nothing(), got);
    }

    @Test
    public void callingPerformWithSomethingYieldsJustSomething() {
        final Object source = new Object();
        final Maybe<Object> got = new LiftMaybe<Object>().perform(source);
        Assert.assertEquals(Maybe.just(source), got);
    }
}