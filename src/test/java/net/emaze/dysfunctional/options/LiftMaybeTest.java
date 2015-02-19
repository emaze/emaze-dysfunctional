package net.emaze.dysfunctional.options;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LiftMaybeTest {

    @Test
    public void callingPerformWithNullYieldsNothing() {
        final Optional<Object> got = new LiftMaybe<Object>().apply(null);
        Assert.assertEquals(Optional.empty(), got);
    }

    @Test
    public void callingPerformWithSomethingYieldsJustSomething() {
        final Object source = new Object();
        final Optional<Object> got = new LiftMaybe<Object>().apply(source);
        Assert.assertEquals(Optional.of(source), got);
    }
}