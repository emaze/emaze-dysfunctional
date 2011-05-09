package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringEndsWithTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringEndsWith(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringEndsWith("a").accept(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEndsWith("a");
        p.accept(new Object());
    }    
    
    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringEndsWith("a").accept("a"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringEndsWith("a").accept("A"));
    }
}