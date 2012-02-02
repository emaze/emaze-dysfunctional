package net.emaze.dysfunctional.reductions;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.iterations.ArrayIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ReductorTest {

    @Test
    public void canReduceAnIterator() {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        Reductor<Long, Integer> cc = new Reductor<Long, Integer>(new Count<Integer>(), 0l);
        long count = cc.perform(new ArrayIterator<Integer>(arr));
        Assert.assertEquals(4l, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new Reductor<Long, Integer>(null, 0l);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        new Reductor<Long, Integer>(new Count<Integer>(), 0l).perform(null);
    }
    

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToErasureYieldsException() {
        Delegate c = new Reductor<Long, Integer>(new Count<Integer>(), 0l);
        c.perform(new Object());
    }    
}
