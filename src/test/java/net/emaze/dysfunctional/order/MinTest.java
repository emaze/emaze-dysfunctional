package net.emaze.dysfunctional.order;

import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MinTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMinWithNullComparatorYieldsException() {
        new Min<Object>(null);
    }

    @Test
    public void canEvaluateMaxForComparables() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        int got = new Min<Integer>(comp).perform(1, 2);
        Assert.assertEquals(1, got);
    }

}