package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class TernaryNeverTest {

    @Test
    public void neverYieldsFalseWithNulls() {
        Assert.assertFalse(new TernaryNever<O, O, O>().test(null, null, null));
    }
}
