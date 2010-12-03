package net.emaze.dysfunctional.delegates;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class AtIndexTest {


    @Test
    public void atIndexIsZeroBased() {
        AtIndex<Void> nth = new AtIndex<Void>(0);
        Assert.assertTrue(nth.test(null));
    }

}