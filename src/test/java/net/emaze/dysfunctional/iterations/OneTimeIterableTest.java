package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class OneTimeIterableTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingFromNullIteratorYieldsException() {
        new OneTimeIterable<O>(null);
    }
    
    @Test(expected=IllegalStateException.class)
    public void consumingTwoTimesYieldsException() {
        Iterable<O> oneTime = new OneTimeIterable<O>(Iterations.iterator(O.ONE));
        oneTime.iterator();
        oneTime.iterator();
    }
    
    @Test
    public void consumingTheFirstTimeYieldsIterator() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE);
        Iterable<O> oneTime = new OneTimeIterable<O>(iterator);
        Iterator<O> got = oneTime.iterator();
        Assert.assertEquals(got, iterator);
    }
    
}
