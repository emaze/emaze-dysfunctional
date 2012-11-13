package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.options.Maybe;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class JustBeforeNothingComparatorTest {

    @Test
    public void twoInstancesOfnothingHaveSameOrder() {
        final Comparator<Maybe<Integer>> comparator = new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>());
        final Maybe<Integer> former = Maybe.<Integer>nothing();
        final Maybe<Integer> latter = Maybe.<Integer>nothing();
        Assert.assertEquals(Order.EQ, Order.of(comparator.compare(former, latter)));
    }

    @Test
    public void nothingIsGreaterThanJust() {
        final Comparator<Maybe<Integer>> comparator = new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>());
        final Maybe<Integer> former = Maybe.<Integer>nothing();
        final Maybe<Integer> latter = Maybe.<Integer>just(1);
        Assert.assertEquals(Order.GT, Order.of(comparator.compare(former, latter)));
    }

    @Test
    public void justIsLesserThanNothing() {
        final Comparator<Maybe<Integer>> comparator = new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>());
        final Maybe<Integer> former = Maybe.<Integer>just(1);
        final Maybe<Integer> latter = Maybe.<Integer>nothing();
        Assert.assertEquals(Order.LT, Order.of(comparator.compare(former, latter)));
    }

    @Test
    public void nestedComparatorIsTakenIntoAccount() {
        final Comparator<Maybe<Integer>> comparator = new JustBeforeNothingComparator<Integer>(new ComparableComparator<Integer>());
        final Maybe<Integer> former = Maybe.<Integer>just(2);
        final Maybe<Integer> latter = Maybe.<Integer>just(1);
        Assert.assertEquals(Order.GT, Order.of(comparator.compare(former, latter)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullComparatorYieldsException() {
        new JustBeforeNothingComparator<Object>(null);
    }

    @Test
    public void twoInstancesWithSameInnerComparatorAreEquals() {
        final ComparableComparator<Integer> inner = new ComparableComparator<Integer>();
        Assert.assertEquals(new JustBeforeNothingComparator<Integer>(inner), new JustBeforeNothingComparator<Integer>(inner));
    }

    @Test
    public void twoInstancesWithDifferentInnerComparatorsAreNotEquals() {
        final JustBeforeNothingComparator<Integer> former = new JustBeforeNothingComparator<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return 0;
            }
        });
        final JustBeforeNothingComparator<Integer> latter = new JustBeforeNothingComparator<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return 0;
            }
        });
        Assert.assertFalse(former.equals(latter));
    }

    @Test
    public void twoInstancesWithSameInnerComparatorHaveSameHashCode() {
        final ComparableComparator<Integer> inner = new ComparableComparator<Integer>();
        Assert.assertEquals(new JustBeforeNothingComparator<Integer>(inner).hashCode(), new JustBeforeNothingComparator<Integer>(inner).hashCode());
    }
}