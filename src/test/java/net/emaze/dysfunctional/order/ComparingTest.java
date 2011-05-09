package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComparingTest {

    @Test
    public void canEvaluateLhsIsLesserForComparables() {
        Assert.assertTrue(Comparing.lhsIsLesser(1, 2));
    }

    @Test
    public void canEvaluateLhsIsNotLesserForComparables() {
        Assert.assertFalse(Comparing.lhsIsLesser(2, 2));
    }

    @Test
    public void canEvaluateLhsIsLesserWithComparator() {
        Assert.assertTrue(Comparing.lhsIsLesser(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateLhsIsNotLesserWithComparator() {
        Assert.assertFalse(Comparing.lhsIsLesser(2, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateLhsIsLesserThanEqualsWhenSameOrderForComparables() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 1));
    }

    @Test
    public void canEvaluateLhsIsLesserThanEqualsWhenLhsIsLesserForComparables() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 2));
    }

    @Test
    public void canEvaluateLhsIsNotLesserThanEqualsForComparables() {
        Assert.assertFalse(Comparing.lhsIsLesserThanEquals(2, 1));
    }

    @Test
    public void canEvaluateIfLhsIsLesserThanEqualsWhenSameOrderWithComparator() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsLesserThanEqualsWhenLhsIsLesserWithComparator() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsNotLesserThanEqualsWithComparator() {
        Assert.assertFalse(Comparing.lhsIsLesserThanEquals(2, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterForComparables() {
        Assert.assertTrue(Comparing.lhsIsGreater(2, 1));
    }

    @Test
    public void canEvaluateIfLhsIsNotGreaterForComparables() {
        Assert.assertFalse(Comparing.lhsIsGreater(1, 1));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterWithComparator() {
        Assert.assertTrue(Comparing.lhsIsGreater(2, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsNotGreaterWithComparator() {
        Assert.assertFalse(Comparing.lhsIsGreater(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsWhenSameOrderForComparables() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(1, 1));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsWhenLhsIsGreaterForComparables() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(2, 1));
    }

    @Test
    public void canEvaluateIfLhsIsNotGreaterThanEqualsForComparables() {
        Assert.assertFalse(Comparing.lhsIsGreaterThanEquals(1, 2));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsWhenSameOrderWithComparator() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsWhenLhsIsGreaterWithComparator() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(2, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsNotGreaterThanEqualsWithComparator() {
        Assert.assertFalse(Comparing.lhsIsGreaterThanEquals(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateSameOrderForComparables() {
        Assert.assertTrue(Comparing.sameOrder(1, 1));
    }

    @Test
    public void canEvaluateNotSameOrderForComparables() {
        Assert.assertFalse(Comparing.sameOrder(2, 1));
    }

    @Test
    public void canEvaluateSameOrderWithComparator() {
        Assert.assertTrue(Comparing.sameOrder(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateNotSameOrderWithComparator() {
        Assert.assertFalse(Comparing.sameOrder(2, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateMax() {
        Assert.assertEquals(Integer.valueOf(2), Comparing.max(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateMin() {
        Assert.assertEquals(Integer.valueOf(1), Comparing.min(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateOrdered() {
        Assert.assertEquals(Pair.of(1, 2), Comparing.ordered(1, 2, new ComparableComparator<Integer>()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsGreaterWithANullComparator() {
        Comparing.lhsIsGreater(1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsGreaterThanEqualsWithANullComparator() {
        Comparing.lhsIsGreaterThanEquals(1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsLesserWithANullComparator() {
        Comparing.lhsIsLesser(1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsLesserThanEqualsWithANullComparator() {
        Comparing.lhsIsLesserThanEquals(1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsLesserThanEqualsWithANullComparable() {
        Comparing.lhsIsLesserThanEquals(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallSameOrderWithANullComparator() {
        Comparing.sameOrder(1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsGreaterWithANullLhs() {
        Comparing.lhsIsGreater(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsGreaterThanEqualsWithANullLhs() {
        Comparing.lhsIsGreaterThanEquals(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsLesserWithANullLhs() {
        Comparing.lhsIsLesser(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallLhsIsLesserThanEqualsWithANullLhs() {
        Comparing.lhsIsGreaterThanEquals(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCallSameOrderWithANullLhs() {
        Comparing.sameOrder(null, 1);
    }
    
    @Test
    public void facadeIsNotFinal() {
        new Comparing() {};
    }
}
