package net.emaze.dysfunctional.options;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class PureMaybeTest {

    @Test(expected = NullPointerException.class)
    public void callingWithNullYieldsJustNull() {
        new PureMaybe<>().apply(null);
    }

    @Test
    public void callingPerformWithSomethingYieldsJustSomething() {
        final Object source = new Object();
        final Optional<Object> got = new PureMaybe<>().apply(source);
        Assert.assertEquals(Optional.of(source), got);
    }
}
