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
public class AtIndexTest {


    @Test
    public void atIndexIsZeroBased() {
        AtIndex<Void> nth = new AtIndex<Void>(0);
        Assert.assertTrue(nth.test(null));
    }
    
    @Test
    public void atReturnsTrueOnlyAtIndex() {
        AtIndex<Void> at = new AtIndex<Void>(1);
        List<Boolean> got = new ArrayList<Boolean>();
        got.add(at.test(null));
        got.add(at.test(null));
        got.add(at.test(null));
        Assert.assertEquals(Arrays.asList(false, true, false), got);
    }

}