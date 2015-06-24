package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.Never;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

public class AnyTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullPredicateYieldsException() {
        new Any<O>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new Any<O>(new Always<O>()).test(null);
    }

    @Test
    public void callingWithAnElementYieldingTrueYieldsTrue() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final boolean got = new Any<O>(new Always<O>()).test(iterator);
        Assert.assertTrue(got);
    }

    @Test
    public void callingWithEveryElementYieldingFalseYieldsFalse() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        final boolean got = new Any<O>(new Never<O>()).test(iterator);
        Assert.assertFalse(got);
    }

    @Test
    public void callingWithEmptyIteratorYieldsFalse() {
        final Iterator<O> iterator = Iterations.iterator();
        final boolean got = new Any<O>(new Never<O>()).test(iterator);
        Assert.assertFalse(got);
    }
}