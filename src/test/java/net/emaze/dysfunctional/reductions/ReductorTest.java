package net.emaze.dysfunctional.reductions;

import java.util.function.Function;
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
        Reductor<Integer, Long> cc = new Reductor<>(new Count<Integer>(), 0l);
        long count = cc.apply(new ArrayIterator<>(arr));
        Assert.assertEquals(4l, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingWithNullDelegateYieldsException() {
        new Reductor<>(null, 0l);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void consumingNullIteratorYieldException() {
        new Reductor<Integer, Long>(new Count<>(), 0l).apply(null);
    }
    

    @Test(expected = ClassCastException.class)
    public void passingWrongTypeToErasureYieldsException() {
        Function c = new Reductor<>(new Count<>(), 0l);
        c.apply(new Object());
    }    
}
