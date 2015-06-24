package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringContainsTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringContains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringContains("a").test(null);
    }
    
    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringContains("a");
        p.test(new Object());
    }


    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringContains("a").test("a"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringContains("a").test("A"));
    }
}