package net.emaze.dysfunctional.delegates;

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class TernaryAlwaysTest {

    @Test
    public void yieldTrueWithNull() {
        Assert.assertTrue(new TernaryAlways<Object, Object, Object>().test(null, null, null));
    }
}
