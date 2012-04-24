package net.emaze.dysfunctional.order;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest {

    @Test
    public void canProvideAnEqualityOrderFromComparationResult() {
        final Order got = Order.of(0);
        Assert.assertEquals(Order.EQ, got);
    }
    
    @Test
    public void canProvideALessThanOrderFromComparationResult() {
        final Order got = Order.of(-3);
        Assert.assertEquals(Order.LT, got);
    }
    
    @Test
    public void canProvideAGreaterThanOrderFromComparationResult() {
        final Order got = Order.of(3);
        Assert.assertEquals(Order.GT, got);
    }
    
    @Test
    public void canProvideAnEqualityOrderFromComparator() {
        final Order got = Order.of(new ComparableComparator<Integer>(), 1, 1);
        Assert.assertEquals(Order.EQ, got);
    }
    
    @Test
    public void canProvideALessThanOrderFromComparator() {
        final Order got = Order.of(new ComparableComparator<Integer>(), 0, 1);
        Assert.assertEquals(Order.LT, got);
    }
    
    @Test
    public void canProvideAGreaterThanOrderFromComparator() {
        final Order got = Order.of(new ComparableComparator<Integer>(), 1, 0);
        Assert.assertEquals(Order.GT, got);
    }
}