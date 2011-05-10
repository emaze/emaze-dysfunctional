package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StrictOrderingDoubleComparatorTest {

    private final StrictOrderingDoubleComparator comparator = new StrictOrderingDoubleComparator();

    @Test
    public void positiveZeroIsGreaterThanNegativeZero() {
        Assert.assertEquals(Order.LHS_IS_GREATER, comparator.compare(+0.0, -0.0));
    }

    @Test
    public void negativeZeroIsLesserThanPositiveZero() {
        Assert.assertEquals(Order.LHS_IS_LESSER, comparator.compare(-0.0, +0.0));
    }

    @Test
    public void oneIsGreaterThanPositiveZero() {
        Assert.assertEquals(Order.LHS_IS_GREATER, comparator.compare(1., +0.0));
    }

    @Test
    public void oneIsGreaterThanNegativeZero() {
        Assert.assertEquals(Order.LHS_IS_GREATER, comparator.compare(1., -0.0));
    }

    @Test
    public void negativeOneIsLesserThanPositiveZero() {
        Assert.assertEquals(Order.LHS_IS_LESSER, comparator.compare(-1., +0.0));
    }

    @Test
    public void positiveOneIsLesserThanPositiveZero() {
        Assert.assertEquals(Order.LHS_IS_GREATER, comparator.compare(+1., -0.0));
    }

    @Test
    public void notANumberIsEqualsToHimself() {
        Assert.assertFalse(Float.NaN == Float.NaN);
        Assert.assertEquals(Order.SAME_ORDER, comparator.compare(Double.NaN, Double.NaN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingWithNullLhsYieldsException() {
        comparator.compare(null, 0.);
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingWithNullRhsYieldsException() {
        comparator.compare(0., null);
    }

    @Test
    public void twoComparatorsHaveSameHashCode() {
        Assert.assertEquals(new StrictOrderingDoubleComparator().hashCode(), new StrictOrderingDoubleComparator().hashCode());
    }

    @Test
    public void twoComparatorsAreEquals() {
        Assert.assertEquals(new StrictOrderingDoubleComparator(), new StrictOrderingDoubleComparator());
    }
}
