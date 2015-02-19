package net.emaze.dysfunctional.dispatching.delegates;

import java.util.Collections;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class IteratorPluckerTest {

    @Test
    public void canPluckAnIterator() {
        final Iterator<Object> iter = new IteratorPlucker<>().apply(Collections.emptyList());
        Assert.assertNotNull(iter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pluckingFromNullYieldsException() {
        new IteratorPlucker<>().apply(null);
    }
}
