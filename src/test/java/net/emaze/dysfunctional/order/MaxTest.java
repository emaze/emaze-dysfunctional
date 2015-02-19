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
    public void canEvaluateMaxForComparablesWhenLhsIsLesser() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        int got = new Max<Integer>(comp).apply(1, 2);
        Assert.assertEquals(2, got);
    }
    @Test
    public void canEvaluateMaxForComparablesWhenLhsIsGreater() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        int got = new Max<Integer>(comp).apply(2, 1);
        Assert.assertEquals(2, got);
    }
    
    @Test
    public void canHandleSameOrder() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        int got = new Max<Integer>(comp).apply(2, 2);
        Assert.assertEquals(2, got);
    }

}