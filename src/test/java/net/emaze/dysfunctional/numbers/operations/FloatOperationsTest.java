package net.emaze.dysfunctional.numbers.operations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class FloatOperationsTest {

    FloatOperations ops = new FloatOperations();
    final double delta = 0.0001;

    @Test
    public void canSum() {
        Assert.assertEquals(3f, ops.sum(1f, 2f).floatValue(), delta);
    }

    @Test
    public void canSubtract() {
        Assert.assertEquals(2f, ops.subtract(3f, 1f).floatValue(), delta);
    }

    @Test
    public void canMultiply() {
        Assert.assertEquals(6f, ops.multiply(3f, 2f).floatValue(), delta);
    }

    @Test
    public void canDivide() {
        Assert.assertEquals(3f, ops.divide(6f, 2f).floatValue(), delta);
    }
}