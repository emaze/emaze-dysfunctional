package net.emaze.dysfunctional.numbers.operations;

import org.junit.Assert;
import org.junit.Test;

public class LongOperationsTest {

    private LongOperations ops = new LongOperations();

    @Test
    public void canSum() {
        Assert.assertEquals(3l, ops.sum(1l, 2l).longValue());
    }

    @Test
    public void canSubtract() {
        Assert.assertEquals(2l, ops.subtract(3l, 1l).longValue());
    }

    @Test
    public void canMultiply() {
        Assert.assertEquals(6l, ops.multiply(3l, 2l).longValue());
    }

    @Test
    public void canDivide() {
        Assert.assertEquals(3l, ops.divide(6l, 2l).longValue());
    }

    @Test
    public void canPerformModulus() {
        Assert.assertEquals(0l, ops.modulus(6l, 2).longValue());
    }
}