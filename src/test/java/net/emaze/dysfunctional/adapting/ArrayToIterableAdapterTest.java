package net.emaze.dysfunctional.adapting;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ArrayToIterableAdapterTest {

    @Test
    public void canFetchIterator() {
        Iterable<Integer> iterable = new ArrayToIterableAdapter<Integer>(new Integer[]{1});
        Assert.assertNotNull(iterable.iterator());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void creatingAdapterWithNullArrayYieldsException() {
        new ArrayToIterableAdapter<Integer>(null);
    }

}