package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.IsTrue;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IsTrueTest {

    @Test
    public void trueIsTrue() {
        Assert.assertTrue(new IsTrue().accept(Boolean.TRUE));
    }

    @Test
    public void falseIsNotTrue() {
        Assert.assertFalse(new IsTrue().accept(Boolean.FALSE));
    }
}
