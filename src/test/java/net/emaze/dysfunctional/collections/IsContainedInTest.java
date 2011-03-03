package net.emaze.dysfunctional.collections;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IsContainedInTest {

    @Test(expected=IllegalArgumentException.class)
    public void testTest() {
        new IsContainedIn<Object>(null);
    }
    
    @Test
    public void canCheckIfNullIsContained() {
        final List<Object> container = Arrays.asList((Object)null);
        boolean test = new IsContainedIn<Object>(container).test(null);
        Assert.assertTrue(test);
    }

}