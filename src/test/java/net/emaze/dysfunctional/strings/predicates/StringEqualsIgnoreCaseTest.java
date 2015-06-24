package net.emaze.dysfunctional.strings.predicates;

import java.util.function.Predicate;
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
        new StringEqualsIgnoreCase("a").test(null);
    }

    @Test(expected = ClassCastException.class)
    public void passingNonStringToErasureYieldsException() {
        Predicate p = new StringEqualsIgnoreCase("a");
        p.test(new Object());
    }    
    
    @Test
    public void testingEqualsYieldsTrue() {
        Assert.assertTrue(new StringEqualsIgnoreCase("a").test("A"));
    }

    @Test
    public void testingNotEqualsYieldsFalse() {
        Assert.assertFalse(new StringEqualsIgnoreCase("a").test("b"));
    }
}