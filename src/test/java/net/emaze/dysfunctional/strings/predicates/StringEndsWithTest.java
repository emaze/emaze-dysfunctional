package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
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
        new StringEndsWith("a").test(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEndsWith("a");
        p.test(new Object());
    }    
    
    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringEndsWith("a").test("a"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringEndsWith("a").test("A"));
    }
}