package net.emaze.dysfunctional.strings.predicates;

import net.emaze.dysfunctional.dispatching.logic.Predicate;
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

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEqualsIgnoreCase("a");
        p.accept(new Object());
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