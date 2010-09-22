package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StrictOrderingFloatComparatorTest {

    private final StrictOrderingFloatComparator comparator = new StrictOrderingFloatComparator();

    private static final int FORMER_IS_GREATER = 1;
    private static final int LATTER_IS_GREATER = -1;
    private static final int ELEMENTS_ARE_EQUAL = 0;


    @Test
    public void positiveZeroIsGreaterThanNegativeZero() {
        Assert.assertEquals(LATTER_IS_GREATER,comparator.compare(-0.0f, +0.0f));
    }
    
    @Test
    public void oneIsGreaterThanPositiveZero() {
        Assert.assertEquals(FORMER_IS_GREATER,comparator.compare(1f, +0.0f));
    }
    @Test
    public void oneIsGreaterThanNegativeZero() {
        Assert.assertEquals(FORMER_IS_GREATER,comparator.compare(1f, -0.0f));
    }
    
    @Test
    public void negativeOneIsLesserThanPositiveZero() {
        Assert.assertEquals(LATTER_IS_GREATER,comparator.compare(-1f, +0.0f));
    }
    
    @Test
    public void positiveOneIsLesserThanPositiveZero() {
        Assert.assertEquals(FORMER_IS_GREATER,comparator.compare(+1f, -0.0f));
    }
    
    @Test
    public void notANumberIsEqualsToHimself() {
        Assert.assertFalse(Float.NaN == Float.NaN);
        Assert.assertEquals(ELEMENTS_ARE_EQUAL,comparator.compare(Float.NaN, Float.NaN));
    }
}
