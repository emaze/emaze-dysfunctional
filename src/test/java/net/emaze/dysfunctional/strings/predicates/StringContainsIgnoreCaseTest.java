package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringContainsIgnoreCaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullNeedleYieldsException() {
        new StringContainsIgnoreCase(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testingWithNullHaystackYieldsException() {
        new StringContainsIgnoreCase("a").test(null);
    }
    
    @Test(expected=ClassCastException.class)
    public void passingNonStringToErasureYieldsException(){
        Predicate p = new StringContainsIgnoreCase("a");
        p.test(new Object());
    }

    @Test
    public void testingContainedNeedleYieldsTrue() {
        Assert.assertTrue(new StringContainsIgnoreCase("a").test("A"));
    }

    @Test
    public void testingNotContainedNeedleYieldsFalse() {
        Assert.assertFalse(new StringContainsIgnoreCase("a").test("b"));
    }
}