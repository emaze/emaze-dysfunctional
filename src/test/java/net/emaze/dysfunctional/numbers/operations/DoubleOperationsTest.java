package net.emaze.dysfunctional.numbers.operations;

import org.junit.Assert;
import org.junit.Test;

public class DoubleOperationsTest {

    DoubleOperations ops = new DoubleOperations();
    final double delta = 0.0001;

    @Test
    public void canSum() {
        Assert.assertEquals(3d, ops.sum(1d, 2d).doubleValue(), delta);
    }

    @Test
    public void canSubtract() {
        Assert.assertEquals(2d, ops.subtract(3d, 1d).doubleValue(), delta);
    }

    @Test
    public void canMultiply() {
        Assert.assertEquals(6d, ops.multiply(3d, 2d).doubleValue(), delta);
    }

    @Test
    public void canDivide() {
        Assert.assertEquals(3d, ops.divide(6d, 2d).doubleValue(), delta);
    }
}