package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
        new StringStartsWithIgnoreCase("a").accept(null);
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringStartsWithIgnoreCase("a");
        p.accept(new Object());
    }

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringStartsWithIgnoreCase("a").accept("A"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringStartsWithIgnoreCase("a").accept("b"));
    }
}