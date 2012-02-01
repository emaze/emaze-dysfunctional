package net.emaze.dysfunctional.numbers;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Reductions;
import net.emaze.dysfunctional.numbers.operations.IntegerOperations;
import org.junit.Assert;
import org.junit.Test;

public class SumTest {


    @Test//@explorative
    public void canReduceIntegersUsingSum() {
        Iterator<Integer> iterator = Iterations.iterator(1,2, 3);
        Integer reduced = Reductions.reduce(iterator, new Sum<Integer, Integer, Integer>(new IntegerOperations()), 0);
        Assert.assertEquals(6, reduced.intValue());
    }
}