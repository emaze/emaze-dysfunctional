package net.emaze.dysfunctional.numbers.operations;

import org.junit.Assert;
import org.junit.Test;

public class ShortOperationsTest {

    ShortOperations ops = new ShortOperations();

    @Test
    public void canSum() {
        Assert.assertEquals(3, ops.sum((short) 1, (short) 2).intValue());
    }

    @Test
    public void canSubtract() {
        Assert.assertEquals(2, ops.subtract((short) 3, (short) 1).intValue());
    }

    @Test
    public void canMultiply() {
        Assert.assertEquals(6, ops.multiply((short) 3, (short) 2).intValue());
    }

    @Test
    public void canDivide() {
        Assert.assertEquals(3, ops.divide((short) 6, (short) 2).intValue());
    }

    @Test
    public void canPerformModulus() {
        Assert.assertEquals(0, ops.modulus((short) 6, 2).intValue());
    }
}