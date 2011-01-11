package net.emaze.dysfunctional.delegates;

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class AlwaysTest {

    @Test
    public void yieldsTrueWithNull(){
        Assert.assertTrue(new Always<Object>().test(null));
    }
}