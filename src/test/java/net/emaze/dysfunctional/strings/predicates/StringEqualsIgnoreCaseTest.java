package net.emaze.dysfunctional.strings.predicates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringEqualsIgnoreCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringEqualsIgnoreCase(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringEqualsIgnoreCase("a").accept(null);
    }

    @Test
    public void testingEqualsYieldsTrue() {
        Assert.assertTrue(new StringEqualsIgnoreCase("a").accept("A"));
    }

    @Test
    public void testingNotEqualsYieldsFalse() {
        Assert.assertFalse(new StringEqualsIgnoreCase("a").accept("b"));
    }
}