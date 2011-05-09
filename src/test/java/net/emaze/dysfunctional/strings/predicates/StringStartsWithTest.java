package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
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
        new StringStartsWith("a").accept(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringStartsWith("a");
        p.accept(new Object());
    }    

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringStartsWith("a").accept("a"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringStartsWith("a").accept("A"));
    }
}