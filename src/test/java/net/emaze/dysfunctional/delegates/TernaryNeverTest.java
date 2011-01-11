package net.emaze.dysfunctional.delegates;

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class TernaryNeverTest {

    @Test
    public void neverYieldsFalseWithNulls() {
        Assert.assertFalse(new TernaryNever<Object, Object, Object>().test(null, null, null));
    }
}
