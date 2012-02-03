package net.emaze.dysfunctional.numbers;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Reductions;
import net.emaze.dysfunctional.numbers.operations.IntegerOperations;
import net.emaze.dysfunctional.testing.O;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

public class AverageTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new Average<O>(null);
    }

    @Test
    public void canSumUsingAPolicy() {
        final Average<Integer> average = new Average<Integer>(new IntegerOperations());
        final Pair<Integer, Long> sumAndCount = average.perform(Pair.of(0, 0l), 6);
        Assert.assertEquals(Pair.of(6, 1L), sumAndCount);
    }

    @Test//@explorative
    public void canCalculateAverageWithReductions() {
        Iterator<Integer> iter = Iterations.iterator(1, 2, 3);
        Pair<Integer, Long> average = Reductions.reduce(iter, new Average<Integer>(new IntegerOperations()), Pair.of(0, 0l));
        Assert.assertEquals(2, average.first() / average.second());
    }
}