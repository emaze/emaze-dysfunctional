package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class TernaryAlwaysTest {

    @Test
    public void yieldTrueWithNull() {
        Assert.assertTrue(new TernaryAlways<O, O, O>().test(null, null, null));
    }
}
