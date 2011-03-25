package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.IteratorPlucker;
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
        Iterator<Object> iter = new IteratorPlucker<Object,Iterable<Object>>().perform(Collections.emptyList());
        Assert.assertNotNull(iter);
    }

    @Test(expected=IllegalArgumentException.class)
    public void pluckingFromNullYieldsException() {
        new IteratorPlucker<Object,Iterable<Object>>().perform(null);
    }

}