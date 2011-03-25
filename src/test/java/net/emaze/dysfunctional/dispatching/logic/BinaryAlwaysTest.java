package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class BinaryAlwaysTest {
    @Test
    public void yieldsTrueWithNulls(){
        Assert.assertTrue(new BinaryAlways<Object,Object>().accept(null, null));
    }
}