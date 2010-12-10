package net.emaze.dysfunctional.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class UntilCountTest {

    @Test
    public void untilZeroAlwaysYieldsFalse() {
        UntilCount<String> untilCount = new UntilCount<String>(0);
        Assert.assertFalse(untilCount.test("unused"));
    }

    @Test
    public void untilTwoYieldsTwoTrueThenFalse() {
        UntilCount<String> untilCount = new UntilCount<String>(2);
        List<Boolean> results = new ArrayList<Boolean>();
        results.add(untilCount.test("unused"));
        results.add(untilCount.test("unused"));
        results.add(untilCount.test("unused"));
        Assert.assertEquals(Arrays.asList(true,true,false), results);
    }
    
}