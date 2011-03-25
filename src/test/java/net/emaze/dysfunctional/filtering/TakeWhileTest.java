package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.dispatching.logic.NotNull;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
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

    @Test
    public void takesWhileConditionMetThenNeverTakes() {
        Predicate<Object> pred = new TakeWhile<Object>(new NotNull<Object>());
        List<Boolean> results = new ArrayList<Boolean>();
        results.add(pred.test(new Object()));
        results.add(pred.test(null));
        results.add(pred.test(new Object()));
        Assert.assertEquals(Arrays.asList(true, false, false), results);
    }

}
