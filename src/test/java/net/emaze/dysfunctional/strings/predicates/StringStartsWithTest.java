package net.emaze.dysfunctional.strings.predicates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringStartsWithTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringStartsWith(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringStartsWith("a").test(null);
    }

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringStartsWith("a").test("a"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringStartsWith("a").test("A"));
    }
}