package net.emaze.dysfunctional.numbers.operations;

import org.junit.Assert;
import org.junit.Test;

public class IntegerOperationsTest {

    private IntegerOperations ops = new IntegerOperations();

    @Test
    public void canSum() {
        Assert.assertEquals(3, ops.sum(1, 2).intValue());
    }

    @Test
    public void canSubtract() {
        Assert.assertEquals(2, ops.subtract(3, 1).intValue());
    }

    @Test
    public void canMultiply() {
        Assert.assertEquals(6, ops.multiply(3, 2).intValue());
    }

    @Test
    public void canDivide() {
        Assert.assertEquals(3, ops.divide(6, 2).intValue());
    }

    @Test
    public void canPerformModulus() {
        Assert.assertEquals(0, ops.modulus(6, 2).intValue());
    }
}