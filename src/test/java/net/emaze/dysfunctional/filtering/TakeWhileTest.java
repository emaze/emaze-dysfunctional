package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.delegates.Always;
import net.emaze.dysfunctional.delegates.Never;
import net.emaze.dysfunctional.delegates.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TakeWhileTest {

    @Test
    public void alwayDroppingAlwaysReturnFalse() {
        Predicate<Object> pred = new TakeWhile<Object>(new Always<Object>());
        Assert.assertTrue(pred.test(null));
    }

    @Test
    public void neverDroppingAlwaysReturnTrue() {
        Predicate<Object> pred = new TakeWhile<Object>(new Never<Object>());
        Assert.assertFalse(pred.test(null));
    }
}
