package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.Always;
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