package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
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
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringStartsWith("a");
        p.test(new Object());
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