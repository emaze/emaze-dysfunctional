package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class EveryTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new Every<O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new Every<O>(new Always<O>()).test(null);
    }

    @Test
    public void callingWithAnElementYieldingFalseYieldsFalse() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final boolean got = new Every<O>(new Never<O>()).test(iterator);
        Assert.assertFalse(got);
    }

    @Test
    public void callingWithEveryElementYieldingTrueYieldsTrue() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final boolean got = new Every<O>(new Always<O>()).test(iterator);
        Assert.assertTrue(got);
    }

    @Test
    public void callingWithEmptyIteratorYieldsTrue() {
        final Iterator<O> iterator = Iterations.iterator();
        final boolean got = new Every<O>(new Never<O>()).test(iterator);
        Assert.assertTrue(got);
    }
}
