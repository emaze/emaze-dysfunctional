package net.emaze.dysfunctional.order;

import java.util.Comparator;
import java.util.function.BiFunction;
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

    @Test(expected = ClassCastException.class)
    public void callingErasureWithWrongTypeYieldsException() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        BiFunction d = new MakeOrder<Integer>(comp);
        d.apply(new Object(), new Object());
    }

    @Test
    public void canOrderComparablesWhenLhsIsLesser() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        Pair<Integer, Integer> got = new MakeOrder<Integer>(comp).apply(1, 2);
        Assert.assertEquals(Pair.of(1, 2), got);
    }

    @Test
    public void canOrderComparablesWhenLhsIsGreater() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        Pair<Integer, Integer> got = new MakeOrder<Integer>(comp).apply(2, 1);
        Assert.assertEquals(Pair.of(1, 2), got);
    }

    @Test
    public void canOrderComparablesWhenLhsIsSameOrderAsRhs() {
        Comparator<Integer> comp = new ComparableComparator<Integer>();
        Pair<Integer, Integer> got = new MakeOrder<Integer>(comp).apply(1, 1);
        Assert.assertEquals(Pair.of(1, 1), got);
    }
}
