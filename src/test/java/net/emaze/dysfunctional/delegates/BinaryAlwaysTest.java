package net.emaze.dysfunctional.delegates;

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