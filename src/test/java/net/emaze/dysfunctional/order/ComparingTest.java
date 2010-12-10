package net.emaze.dysfunctional.order;

import java.lang.Integer;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComparingTest {

    @Test
    public void canEvaluateIfLhsIsLesserForComparables() {
        Assert.assertTrue(Comparing.lhsIsLesser(1, 2));
    }

    @Test
    public void canEvaluateIfLhsIsLesserWithComparator() {
        Assert.assertTrue(Comparing.lhsIsLesser(1, 2, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsLesserThanEqualsForComparables() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 1));
    }

    @Test
    public void canEvaluateIfLhsIsLesserThanEqualsWithComparator() {
        Assert.assertTrue(Comparing.lhsIsLesserThanEquals(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterForComparables() {
        Assert.assertTrue(Comparing.lhsIsGreater(2, 1));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterWithComparator() {
        Assert.assertTrue(Comparing.lhsIsGreater(2, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsForComparables() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(1, 1));
    }

    @Test
    public void canEvaluateIfLhsIsGreaterThanEqualsWithComparator() {
        Assert.assertTrue(Comparing.lhsIsGreaterThanEquals(1, 1, new ComparableComparator<Integer>()));
    }

    @Test
    public void canEvaluateSameOrderForComparables() {
        Assert.assertTrue(Comparing.sameOrder(1, 1));
    }

    @Test
    public void canEvaluateSameOrderWithComparator() {
        Assert.assertTrue(Comparing.sameOrder(1, 1, new ComparableComparator<Integer>()));
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
        Assert.assertEquals(new Pair<Integer, Integer>(1, 2), Comparing.ordered(1, 2, new ComparableComparator<Integer>()));
    }
}
