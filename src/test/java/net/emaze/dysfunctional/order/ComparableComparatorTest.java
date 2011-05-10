package net.emaze.dysfunctional.order;

import junit.framework.Assert;
import net.emaze.dysfunctional.options.Box;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComparableComparatorTest {

    @Test
    public void twoComparableComparatorsAreEqual() {
        final ComparableComparator<Integer> former = new ComparableComparator<Integer>();
        final ComparableComparator<Integer> latter = new ComparableComparator<Integer>();
        Assert.assertEquals(former, latter);
    }

    @Test
    public void twoComparableComparatorsHaveSameHashcode() {
        final ComparableComparator<Integer> former = new ComparableComparator<Integer>();
        final ComparableComparator<Integer> latter = new ComparableComparator<Integer>();
        Assert.assertEquals(former.hashCode(), latter.hashCode());
    }

    @Test
    public void camparingDelegatesToComparable() {
        final ComparableComparator<ComparableBean> comparator = new ComparableComparator<ComparableBean>();
        final ComparableBean comparable = new ComparableBean();
        comparator.compare(comparable, comparable);
        Assert.assertTrue(comparable.called.getContent());
    }

    public static class ComparableBean implements Comparable<ComparableBean> {

        public Box<Boolean> called = Box.of(false);

        @Override
        public int compareTo(ComparableBean o) {
            called.setContent(true);
            return Order.SAME_ORDER;
        }
    }
}
