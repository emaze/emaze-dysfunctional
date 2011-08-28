package net.emaze.dysfunctional.iterations;

import net.emaze.dysfunctional.iterations.ArrayIterable;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ArrayIterableTest {

    @Test
    public void canFetchIterator() {
        Iterable<Integer> iterable = new ArrayIterable<Integer>(new Integer[]{1});
        Assert.assertNotNull(iterable.iterator());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void creatingAdapterWithNullArrayYieldsException() {
        new ArrayIterable<Integer>(null);
    }

}