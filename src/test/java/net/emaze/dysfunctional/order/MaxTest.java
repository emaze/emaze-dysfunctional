package net.emaze.dysfunctional.order;

import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MaxTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMaxWithNullComparatorYieldsException() {
        new Max<Object>(null);
    }

    @Test
    public void canEvaluateMaxForComparables() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        int got = new Max<Integer>(comp).perform(1, 2);
        Assert.assertEquals(2, got);
    }
}