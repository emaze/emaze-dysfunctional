package net.emaze.dysfunctional.numbers;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class CircularCounterTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingModuloZeroCounterYieldsException() {
        new CircularCounter(0);
    }
    
    @Test
    public void counterStartsWithZero() {
        CircularCounter counter = new CircularCounter(1);
        Assert.assertEquals(0 , counter.get());
    }
    
    @Test
    public void moduloOneCounterAlwaysReturnsZero() {
        CircularCounter counter = new CircularCounter(1);
        Assert.assertEquals(0 , counter.incrementAndGet());
    }
    
    @Test
    public void getAndIncrementReturnsPreviousValue() {
        CircularCounter counter = new CircularCounter(10);
        Assert.assertEquals(0 , counter.getAndIncrement());
    }
    
    @Test
    public void getAndDecrementReturnsPreviousValue() {
        CircularCounter counter = new CircularCounter(10);
        Assert.assertEquals(0 , counter.getAndDecrement());
    }
    
    @Test
    public void decrementAndGetReturnsNewValue() {
        CircularCounter counter = new CircularCounter(10);
        Assert.assertEquals(-1 , counter.decrementAndGet());
    }

}