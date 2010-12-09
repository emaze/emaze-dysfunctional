package net.emaze.dysfunctional.strings;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class StringToIterableAdapterTest {

    @Test(expected=IllegalArgumentException.class)
    public void creatingWithNullStringYieldsException() {
        new StringToIterableAdapter(null);
    }
    
    @Test
    public void canFetchIterator() {
        Assert.assertNotNull(new StringToIterableAdapter("").iterator());
    }

}