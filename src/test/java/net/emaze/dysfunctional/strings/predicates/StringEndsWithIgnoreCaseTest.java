package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringEndsWithIgnoreCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringEndsWithIgnoreCase(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringEndsWithIgnoreCase("a").accept(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEndsWithIgnoreCase("a");
        p.accept(new Object());
    }    

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringEndsWithIgnoreCase("a").accept("A"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringEndsWithIgnoreCase("a").accept("b"));
    }
}