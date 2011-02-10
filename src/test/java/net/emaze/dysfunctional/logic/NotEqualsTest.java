package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NotEqualsTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingNotEqualsWithNullLhsYieldsException() {
        new NotEquals<O>(null);
    }

    @Test
    public void nonEqualObjectYieldsTrue() {
        Assert.assertTrue(new NotEquals<O>(O.ONE).test(O.ANOTHER));
    }

    @Test
    public void equalObjectYieldsFalse() {
        Assert.assertFalse(new NotEquals<O>(O.ONE).test(O.ONE));
    }
}
