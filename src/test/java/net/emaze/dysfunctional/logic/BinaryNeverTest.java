package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.BinaryNever;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class BinaryNeverTest {

    @Test
    public void neverYieldsFalseWithNulls() {
        Assert.assertFalse(new BinaryNever<Object, Object>().test(null, null));
    }
}
