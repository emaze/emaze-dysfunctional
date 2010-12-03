package net.emaze.dysfunctional.delegates;

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

}