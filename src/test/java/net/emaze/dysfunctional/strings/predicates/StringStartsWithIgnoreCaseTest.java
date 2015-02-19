package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringStartsWithIgnoreCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringStartsWithIgnoreCase(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringStartsWithIgnoreCase("a").test(null);
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringStartsWithIgnoreCase("a");
        p.test(new Object());
    }

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringStartsWithIgnoreCase("a").test("A"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringStartsWithIgnoreCase("a").test("b"));
    }
}