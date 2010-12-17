package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IsTrueTest {

    @Test
    public void trueIsTrue() {
        Assert.assertTrue(new IsTrue().test(Boolean.TRUE));
    }

    @Test
    public void falseIsNotTrue() {
        Assert.assertFalse(new IsTrue().test(Boolean.FALSE));
    }
}
