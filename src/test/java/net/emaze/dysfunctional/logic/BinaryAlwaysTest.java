package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.BinaryAlways;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class BinaryAlwaysTest {
    @Test
    public void yieldsTrueWithNulls(){
        Assert.assertTrue(new BinaryAlways<Object,Object>().test(null, null));
    }
}