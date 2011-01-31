package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.TernaryAlways;
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
