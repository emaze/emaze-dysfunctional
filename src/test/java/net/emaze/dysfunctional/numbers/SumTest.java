package net.emaze.dysfunctional.numbers;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.Reductions;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.numbers.operations.IntegerOperations;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class SumTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPolicyYieldsException() {
        new Sum<O, O, O>(null);
    }

    @Test
    public void canSumUsingAPolicy() {
        BinaryDelegate<O, O, O> sum = new Sum<O, O, O>(new SumPolicy<O, O, O>() {

            @Override
            public O sum(O lhs, O rhs) {
                return O.YET_ANOTHER;
            }
        });
        final O got = sum.perform(O.ONE, O.ANOTHER);
        Assert.assertEquals(O.YET_ANOTHER, got);
    }

    @Test//@Sample
    public void canReduceIntegersUsingSum() {
        Iterator<Integer> iterator = Iterations.iterator(1, 2, 3);
        Integer reduced = Reductions.reduce(iterator, new Sum<Integer, Integer, Integer>(new IntegerOperations()), 0);
        Assert.assertEquals(6, reduced.intValue());
    }
}