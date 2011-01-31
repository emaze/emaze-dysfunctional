package net.emaze.dysfunctional.logic;

import net.emaze.dysfunctional.logic.Never;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author rferranti
 */
public class NeverTest {

    @Test
    public void neverYieldsFalseWithNull(){
        Assert.assertFalse(new Never<Object>().test(null));
    }


}