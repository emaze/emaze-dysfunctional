package net.emaze.dysfunctional.delegates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NthTest {

    @Test
    public void nthIsNotZeroBased() {
        Nth<Void> nth = new Nth<Void>(1);
        Assert.assertTrue(nth.test(null));
    }

    @Test
    public void nthReturnsTrueOnlyAtTheNthCall() {
        Nth<Void> nth = new Nth<Void>(2);
        List<Boolean> got = new ArrayList<Boolean>();
        got.add(nth.test(null));
        got.add(nth.test(null));
        got.add(nth.test(null));
        Assert.assertEquals(Arrays.asList(false, true, false), got);
    }
}
