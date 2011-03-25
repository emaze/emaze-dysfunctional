package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.Equals;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class EqualsTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingEqualsWithNullLhsYieldsException() {
        new Equals<O>(null);
    }

    @Test
    public void nonEqualObjectYieldsFalse() {
        Assert.assertFalse(new Equals<O>(O.ONE).test(O.ANOTHER));
    }

    @Test
    public void equalObjectYieldsTrue() {
        Assert.assertTrue(new Equals<O>(O.ONE).test(O.ONE));
    }
}
