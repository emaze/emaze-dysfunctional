package net.emaze.dysfunctional.iterations;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ConstantIteratorTest {

    @Test
    public void canCreateConstantIteratorWithNull() {
        new ConstantIterator<Object>(null);
    }

    @Test
    public void constantIteratorAlwaysHasNext() {
        Assert.assertTrue(new ConstantIterator<Object>(null).hasNext());
    }

    @Test
    public void constantIteratorAlwaysReturnsTheSameValue() {
        Iterator<Integer> iter = new ConstantIterator<Integer>(1);
        int first = iter.next();
        int second = iter.next();
        Assert.assertEquals(first, second);
    }
}
