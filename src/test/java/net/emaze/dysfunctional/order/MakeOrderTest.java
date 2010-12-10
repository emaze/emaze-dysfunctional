package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class MakeOrderTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingMakeOrderWithNullComparatorYieldsException() {
        new MakeOrder<Object>(null);
    }

    @Test
    public void canOrderComparables() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        Pair<Integer, Integer> got = new MakeOrder<Integer>(comp).perform(1, 2);
        Assert.assertEquals(new Pair<Integer,Integer>(1,2), got);
    }
}
