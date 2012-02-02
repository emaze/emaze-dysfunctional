package net.emaze.dysfunctional;

import net.emaze.dysfunctional.order.ComparableComparator;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComparingTest {

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

    @Test
    public void facadeIsNotFinal() {
        new Comparing() {
        };
    }
}
