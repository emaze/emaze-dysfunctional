package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
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
        new StringEndsWithIgnoreCase("a").test(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEndsWithIgnoreCase("a");
        p.test(new Object());
    }    

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringEndsWithIgnoreCase("a").test("A"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringEndsWithIgnoreCase("a").test("b"));
    }
}