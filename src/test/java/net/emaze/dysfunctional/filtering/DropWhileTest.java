package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.logic.Always;
import net.emaze.dysfunctional.logic.Never;
import net.emaze.dysfunctional.logic.NotNull;
import net.emaze.dysfunctional.logic.Predicate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class DropWhileTest {

    @Test
    public void alwayDroppingAlwaysReturnFalse() {
        Predicate<Object> pred = new DropWhile<Object>(new Always<Object>());
        Assert.assertFalse(pred.test(null));
    }

    @Test
    public void neverDroppingAlwaysReturnTrue() {
        Predicate<Object> pred = new DropWhile<Object>(new Never<Object>());
        Assert.assertTrue(pred.test(null));
    }

    @Test
    public void dropsUntilConditionMetThenNeverDrops() {
        Predicate<Object> pred = new DropWhile<Object>(new NotNull<Object>());
        List<Boolean> results = new ArrayList<Boolean>();
        results.add(pred.test(new Object()));
        results.add(pred.test(null));
        results.add(pred.test(new Object()));
        Assert.assertEquals(Arrays.asList(false, true, true), results);
    }
}
