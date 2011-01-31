package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.TernaryNever;
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
